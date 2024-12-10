package LOGICA;

/**
 * Clase CrearAsientosFactory que se encarga de crear asientos de diferentes tipos.
 */
public class CrearAsientosFactory {
    private Asientos asiento;

    /**
     * Constructor de la clase CrearAsientosFactory.
     */
    public CrearAsientosFactory() {
    }

    /**
     * Crea un asiento del tipo especificado.
     *
     * @param tipo   El tipo de asiento (NORMAL, SEMICAMA, CAMA).
     * @param numero El número del asiento.
     * @return El asiento creado.
     */
    public Asientos crearAsiento(TipoAsiento tipo, int numero) {
        switch (tipo) {
            case NORMAL -> asiento = new AsientoNormal(numero);
            case SEMICAMA -> asiento = new AsientoSemiCama(numero);
            case CAMA -> asiento = new AsientoCama(numero);
        }
        return asiento;
    }
}