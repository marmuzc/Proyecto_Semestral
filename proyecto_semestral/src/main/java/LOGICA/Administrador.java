package LOGICA;

import java.util.ArrayList;

/**
 * Clase Administrador que implementa el patrón Singleton para gestionar la creación de recorridos.
 */
public class Administrador {
    private static Administrador instancia;
    private final CrearBusFactory creadorDeBuses;
    private final ArrayList<Recorrido> recorridos;

    /**
     * Constructor privado para evitar la creación de múltiples instancias.
     * Inicializa la fábrica de buses y la lista de recorridos.
     */
    private Administrador() {
        this.creadorDeBuses = new CrearBusFactory();
        this.recorridos = new ArrayList<>();
    }

    /**
     * Obtiene la única instancia de la clase Administrador.
     *
     * @return La instancia única de Administrador.
     */
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
     * @throws IllegalArgumentException Si la fecha o la hora no tienen el formato correcto.
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

    /**
     * Obtiene la lista de recorridos creados.
     *
     * @return La lista de recorridos.
     */
    public ArrayList<Recorrido> getRecorridos() {
        return recorridos;
    }
}