package LOGICA;

/**
 * Clase AsientoCama que representa un asiento tipo cama en un bus.
 */
public class AsientoCama extends Asientos {
    private final TipoAsiento cama = TipoAsiento.CAMA;

    /**
     * Constructor de la clase AsientoCama.
     *
     * @param numero El número del asiento.
     */
    public AsientoCama(int numero) {
        super(numero);
        precio = cama.getPrecio();
        tipo = cama.toString();
        ocupado = false;
    }
}