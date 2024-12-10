package LOGICA;

/**
 * Clase CrearBusFactory que se encarga de crear buses de uno o dos pisos.
 */
public class CrearBusFactory {
    private Bus bus;

    /**
     * Constructor de la clase CrearBusFactory.
     */
    public CrearBusFactory() {
    }

    /**
     * Crea un bus de un piso o dos pisos dependiendo del parámetro.
     *
     * @param pisos La cantidad de pisos del bus (1 o 2).
     * @return El bus creado, o null si el parámetro es inválido.
     */
    public Bus crearBus(int pisos) {
        switch (pisos) {
            case 1 -> bus = new BusUnPiso();
            case 2 -> bus = new BusDosPisos();
            default -> bus = null; // Caso inválido
        }
        return bus;
    }
}