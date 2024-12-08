package LOGICA;

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
     * @param origen  El punto de partida del recorrido.
     * @param destino El punto de llegada del recorrido.
     * @param precio  El costo del recorrido.
     * @param pisos   Número de pisos del bus asignado.
     * @param fecha   La fecha del recorrido en formato DD/MM.
     * @param hora    La hora del recorrido en formato HH:mm.
     * @return Un objeto de tipo Recorrido, o null si el número de pisos es inválido.
     */
    public Recorrido crearRecorrido(String origen, String destino, int precio, int pisos, String fecha, String hora) {
        Bus bus = creadorDeBuses.crearBus(pisos);

        // Manejar caso de número de pisos inválido
        if (bus == null) {
            return null;
        }

        return new Recorrido(origen, destino, precio, bus, fecha, hora);
    }
}
