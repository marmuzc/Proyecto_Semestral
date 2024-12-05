package LOGICA;

public class AsientoSemiCama extends Asientos {
    private final TipoAsiento normal = TipoAsiento.NORMAL;
    public AsientoSemiCama(int numero) {
        super(numero);
        precio = normal.getPrecio();
        tipo = normal.toString();
        ocupado = false;
    }
}