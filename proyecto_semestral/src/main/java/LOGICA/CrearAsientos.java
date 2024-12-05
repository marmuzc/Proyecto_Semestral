package LOGICA;

public class CrearAsientos {
    private Asientos asiento;

    public CrearAsientos() {
    }

    public Asientos crear_Asiento(TipoAsiento tipo, int numero) {
        switch (tipo) {
            case NORMAL -> asiento = new AsientoNormal(numero);
            case SEMICAMA -> asiento = new AsientoSemiCama(numero);
            case CAMA -> asiento = new AsientoCama(numero);
        }
        return asiento;
    }
}