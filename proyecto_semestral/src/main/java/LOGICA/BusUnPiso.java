package LOGICA;

/**
 * Clase BusUnPiso que representa un bus de un piso.
 */
public class BusUnPiso extends Bus {

    /**
     * Constructor de la clase BusUnPiso.
     * Inicializa los asientos normales, semi-cama y cama, y crea los asientos.
     */
    public BusUnPiso() {
        super();
        AsientoNormal = 30;
        AsientoSemiCama = 10;
        AsientoCama = 0;
        crearAsientos();
    }

    /**
     * Obtiene el tipo de bus.
     *
     * @return El tipo de bus ("Un Piso").
     */
    @Override
    public String getTipo() {
        return "Un Piso";
    }

    /**
     * Obtiene la cantidad de pisos del bus.
     *
     * @return La cantidad de pisos del bus (1).
     */
    public int getCantidadPisos() {
        return 1;
    }

    /**
     * Devuelve una representación en cadena del bus.
     *
     * @return Una cadena que representa el bus.
     */
    @Override
    public String toString() {
        return "Un Piso. Costo: " + this.getCosto();
    }
}