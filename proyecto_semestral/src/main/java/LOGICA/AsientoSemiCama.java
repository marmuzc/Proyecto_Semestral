package LOGICA;

/**
 * Clase AsientoSemiCama que representa un asiento tipo semi-cama en un bus.
 */
public class AsientoSemiCama extends Asientos {
    private final TipoAsiento semiCama = TipoAsiento.SEMICAMA;

    /**
     * Constructor de la clase AsientoSemiCama.
     *
     * @param numero El número del asiento.
     */
    public AsientoSemiCama(int numero) {
        super(numero);
        precio = semiCama.getPrecio();
        tipo = semiCama.toString();
        ocupado = false;
    }
}