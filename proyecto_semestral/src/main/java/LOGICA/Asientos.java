package LOGICA;

/**
 * Clase Asientos que representa un asiento en un bus.
 */
public abstract class Asientos {
    protected int precio;
    private int numero;
    protected String tipo;
    public boolean ocupado;
    protected boolean auxSeleccionado;

    /**
     * Constructor de la clase Asientos.
     *
     * @param numero El número del asiento.
     */
    public Asientos(int numero) {
        this.ocupado = false; // empieza con todos los asientos desocupados
        this.auxSeleccionado = false; // empieza con todos los asientos sin seleccionar
        this.numero = numero;
    }

    /**
     * Obtiene el precio del asiento.
     *
     * @return El precio del asiento.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Obtiene el tipo de asiento.
     *
     * @return El tipo de asiento.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el número del asiento.
     *
     * @return El número del asiento.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establece el estado de ocupación del asiento.
     *
     * @param estado El nuevo estado de ocupación del asiento.
     */
    public void setOcupado(boolean estado) {
        ocupado = estado;
    }

    /**
     * Verifica si el asiento está ocupado.
     *
     * @return true si el asiento está ocupado, false en caso contrario.
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Obtiene el estado de selección auxiliar del asiento.
     *
     * @return true si el asiento está seleccionado, false en caso contrario.
     */
    public boolean getAuxSeleccionado() {
        return auxSeleccionado;
    }

    /**
     * Establece el estado de selección auxiliar del asiento.
     *
     * @param auxSeleccionado El nuevo estado de selección auxiliar del asiento.
     */
    public void setAuxSeleccionado(boolean auxSeleccionado) {
        this.auxSeleccionado = auxSeleccionado;
    }

    /**
     * Devuelve una representación en cadena del asiento.
     *
     * @return Una cadena que representa el asiento.
     */
    @Override
    public String toString() {
        return "Asiento " + numero + " (" + tipo + ")";
    }
}