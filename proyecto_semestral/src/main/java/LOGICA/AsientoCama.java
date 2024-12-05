package LOGICA;

public class AsientoCama extends Asientos {
    private final TipoAsiento cama = TipoAsiento.CAMA;
    public AsientoCama(int numero) {
        super(numero);
        precio = cama.getPrecio();
        tipo = cama.toString();
        ocupado = false;
    }
}