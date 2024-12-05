package LOGICA;

public class AsientoNormal extends Asientos {
    private final TipoAsiento normal = TipoAsiento.NORMAL;
    public AsientoNormal(int numero) {
        super(numero);
        precio = normal.getPrecio();
        tipo = normal.toString();
        ocupado = false;
    }
}