package LOGICA;

/**
 * Enumeración TipoAsiento que representa los diferentes tipos de asientos disponibles en un bus.
 * Cada tipo de asiento tiene un precio asociado.
 */
public enum TipoAsiento {
    NORMAL(2500),
    SEMICAMA(3000),
    CAMA(4500);

    private int precio;

    /**
     * Constructor de la enumeración TipoAsiento.
     *
     * @param precio El precio del tipo de asiento.
     */
    TipoAsiento(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el precio del tipo de asiento.
     *
     * @return El precio del tipo de asiento.
     */
    public int getPrecio() {
        return this.precio;
    }

    /**
     * Devuelve una representación en cadena del tipo de asiento.
     *
     * @return El nombre del tipo de asiento.
     */
    @Override
    public String toString() {
        return this.name();
    }
}