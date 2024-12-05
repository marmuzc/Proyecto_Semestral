package LOGICA;

public class BusUnPiso extends Bus{
    public BusUnPiso() {
        super();
        AsientoNormal = 30;
        AsientoSemiCama = 10;
        AsientoCama = 0;
        crearAsientos();
    }
    @Override
    public String getTipo() {
        return "Un Piso";
    }

    public int getCantidadPisos() {
        return 1;
    }

    public String toString() {
        return "Un Pisos. Costo: " + this.getCosto();
    }

}