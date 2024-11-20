package LOGICA;

public class CrearAsientos {
    private Asientos asientos;

    public CrearAsientos() {

    }

    public Asientos crearAsientos (String tipoAsiento, int numeroAsiento, boolean estado, String ubicacion, int precio) {
        if (tipoAsiento.equals("Normal")) {
            asientos = new AsientoNormal(numeroAsiento, estado, tipoAsiento, ubicacion, precio);
        } else if (tipoAsiento.equals("SemiCama")) {
            asientos = new AsientoSemiCama(numeroAsiento, estado, tipoAsiento, ubicacion, precio);
        } else if (tipoAsiento.equals("Cama")) {
            asientos = new AsientoCama(numeroAsiento, estado, tipoAsiento, ubicacion, precio);
        }
        return asientos;
    }
}
