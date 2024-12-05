package LOGICA;

public class CrearAsientosFactory {
    private Asientos asiento;

    public CrearAsientosFactory() {
    }

    public Asientos crearAsiento(TipoAsiento tipo, int numero) {
        switch (tipo) {
            case NORMAL -> asiento = new AsientoNormal(numero);
            case SEMICAMA -> asiento = new AsientoSemiCama(numero);
            case CAMA -> asiento = new AsientoCama(numero);
        }
        return asiento;
    }
}