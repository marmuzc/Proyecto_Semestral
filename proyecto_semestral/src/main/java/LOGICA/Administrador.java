package LOGICA;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Administrador {
    private final CrearBus creadorDeBuses;
    private final ArrayList<Recorrido> recorridos;

    public Administrador() {
        this.creadorDeBuses = new CrearBus();
        this.recorridos = new ArrayList<>();
    }

    /**
     * Crea un nuevo recorrido con un bus asignado y una fecha y hora.
     *
     * @param origen    El punto de partida del recorrido.
     * @param destino   El punto de llegada del recorrido.
     * @param precioBase El precio base del recorrido.
     * @param pisos     Número de pisos del bus.
     * @param fechaHora La fecha y hora del recorrido.
     * @return El recorrido creado.
     */
    public Recorrido crearRecorrido(String origen, String destino, int precioBase, int pisos, LocalDateTime fechaHora) {
        Bus bus = creadorDeBuses.crearBus(pisos);
        Recorrido recorrido = new Recorrido(origen, destino, precioBase, bus, fechaHora);
        recorridos.add(recorrido);
        return recorrido;
    }

    public ArrayList<Recorrido> getRecorridos() {
        return recorridos;
    }
}
