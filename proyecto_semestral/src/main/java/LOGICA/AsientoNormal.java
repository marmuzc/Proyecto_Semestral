package LOGICA;

/**
 * Clase AsientoNormal que representa un asiento normal en un bus.
 */
public class AsientoNormal extends Asientos {
    private final TipoAsiento normal = TipoAsiento.NORMAL;

    /**
     * Constructor de la clase AsientoNormal.
     *
     * @param numero El número del asiento.
     */
    public AsientoNormal(int numero) {
        super(numero);
        precio = normal.getPrecio();
        tipo = normal.toString();
        ocupado = false;
    }
}