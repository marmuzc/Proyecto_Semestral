package LOGICA;

public class AsientoSemiCama extends Asientos {
    private final TipoAsiento normal = TipoAsiento.SEMICAMA;
    public AsientoSemiCama(int numero) {
        super(numero);
        precio = normal.getPrecio();
        tipo = normal.toString();
        ocupado = false;
    }
}