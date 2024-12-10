package LOGICA;

/**
 * Clase BusDosPisos que representa un bus de dos pisos.
 */
public class BusDosPisos extends Bus {

    /**
     * Constructor de la clase BusDosPisos.
     * Inicializa los asientos normales, semi-cama y cama, y crea los asientos.
     */
    public BusDosPisos() {
        super();
        AsientoNormal = 30;
        AsientoSemiCama = 10;
        AsientoCama = 10;
        crearAsientos();
    }

    /**
     * Obtiene el tipo de bus.
     *
     * @return El tipo de bus ("Dos Pisos").
     */
    @Override
    public String getTipo() {
        return "Dos Pisos";
    }

    /**
     * Obtiene la cantidad de pisos del bus.
     *
     * @return La cantidad de pisos del bus (2).
     */
    public int getCantidadPisos() {
        return 2;
    }

    /**
     * Devuelve una representación en cadena del bus.
     *
     * @return Una cadena que representa el bus.
     */
    @Override
    public String toString() {
        return "Dos Pisos. Costo: " + this.getCosto();
    }
}