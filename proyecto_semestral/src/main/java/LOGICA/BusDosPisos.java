package LOGICA;

public class BusDosPisos extends Bus{
    public BusDosPisos() {
        super();
        AsientoNormal = 30;
        AsientoSemiCama = 10;
        AsientoCama = 10;
        crearAsientos();
    }
    @Override
    public String getTipo() {
        return "Dos Pisos";
    }

    public int getCantidadPisos() {
        return 2;
    }

    public String toString() {
        return "Dos Pisos. Costo: " + this.getCosto();
    }

}