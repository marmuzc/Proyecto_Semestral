package LOGICA;

import java.util.ArrayList;

public class Administrador {
    private static Administrador instancia; // Única instancia
    private final CrearBusFactory creadorDeBuses;
    private final ArrayList<Recorrido> recorridos;
//singleton para administrador
    private Administrador() {
        this.creadorDeBuses = new CrearBusFactory();
        this.recorridos = new ArrayList<>();
    }

    public static Administrador getInstance() {
        if (instancia == null) {
            instancia = new Administrador();
        }
        return instancia;
    }

    /**
     * Crea un nuevo recorrido con un bus asignado, una fecha y una hora.
     *
     * @param origen     El punto de partida del recorrido.
     * @param destino    El punto de llegada del recorrido.
     * @param precioBase El precio base del recorrido.
     * @param pisos      Número de pisos del bus.
     * @param fecha      La fecha del recorrido en formato DD/MM.
     * @param hora       La hora del recorrido en formato HH:mm.
     * @return El recorrido creado.
     */
    public Recorrido crearRecorrido(String origen, String destino, int precioBase, int pisos, String fecha, String hora) {
        if (!fecha.matches("\\d{2}/\\d{2}")) {
            throw new IllegalArgumentException("La fecha debe tener el formato DD/MM.");
        }
        if (!hora.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("La hora debe tener el formato HH:mm.");
        }

        Bus bus = creadorDeBuses.crearBus(pisos);
        Recorrido recorrido = new Recorrido(origen, destino, precioBase, bus, fecha, hora);
        recorridos.add(recorrido);
        return recorrido;
    }

    public ArrayList<Recorrido> getRecorridos() {
        return recorridos;
    }
}
