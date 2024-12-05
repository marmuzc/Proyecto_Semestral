package LOGICA;

import java.time.LocalDateTime;

public class CrearRecorridoFactory {
    private final CrearBusFactory creadorDeBuses;

    /**
     * Constructor de CrearRecorridoFactory.
     * Inicializa el factory para crear buses.
     */
    public CrearRecorridoFactory() {
        this.creadorDeBuses = new CrearBusFactory();
    }

    /**
     * Crea un nuevo recorrido con un bus asignado.
     *
     * @param origen    El punto de partida del recorrido.
     * @param destino   El punto de llegada del recorrido.
     * @param precio    El costo del recorrido.
     * @param pisos     Número de pisos del bus asignado.
     * @param fechaHora La fecha y hora del recorrido.
     * @return Un objeto de tipo Recorrido.
     */
    public Recorrido crearRecorrido(String origen, String destino, int precio, int pisos, LocalDateTime fechaHora) {
        Bus bus = creadorDeBuses.crearBus(pisos);
        return new Recorrido(origen, destino, precio, bus, fechaHora);
    }
}
