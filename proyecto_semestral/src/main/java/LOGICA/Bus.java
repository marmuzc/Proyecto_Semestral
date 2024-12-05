package LOGICA;

import java.util.ArrayList;

public abstract class Bus {
    protected int AsientoNormal;
    protected int AsientoSemiCama;
    protected int AsientoCama;
    private int totalAsientos;
    private int costo;
    private final ArrayList<Asientos> asientos;
    private final CrearAsientosFactory crearAsientos;

    public Bus() {
        crearAsientos = new CrearAsientosFactory();
        asientos = new ArrayList<>();
    }

    /**
     * Crea los asientos del bus y los agrega al ArrayList
     */
    protected void crearAsientos() {
        int asientoActual = 0;

        asientoActual = agregarAsientos(TipoAsiento.NORMAL, AsientoNormal, asientoActual);
        asientoActual = agregarAsientos(TipoAsiento.SEMICAMA, AsientoSemiCama, asientoActual);
        asientoActual = agregarAsientos(TipoAsiento.CAMA, AsientoCama, asientoActual);

        totalAsientos = AsientoNormal + AsientoSemiCama + AsientoCama;
    }

    /**
     * Metodo auxiliar para agregar asientos de un tipo específico.
     *
     * @param tipo      Tipo de asiento (NORMAL, SEMICAMA, CAMA)
     * @param cantidad  Cantidad de asientos a crear
     * @param inicio    Índice inicial para los números de asiento
     * @return El índice actualizado después de agregar los asientos
     */
    private int agregarAsientos(TipoAsiento tipo, int cantidad, int inicio) {
        int contador = 0;

        while (contador < cantidad) {
            Asientos aux = crearAsientos.crearAsiento(tipo, inicio + contador);
            asientos.add(aux);
            contador++;
        }

        return inicio + cantidad;
    }

    public ArrayList<Asientos> getAsientosArray() {
        return asientos;
    }

    public abstract String getTipo();

    public abstract int getCantidadPisos();

    public int getAsientosTotalInt() {
        return totalAsientos;
    }

    public int getAsientosCamaInt() {
        return AsientoCama;
    }

    public int getAsientosSemiInt() {
        return AsientoSemiCama;
    }

    public int getAsientosNormalInt() {
        return AsientoNormal;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int precio) {
        this.costo = precio;
    }

    @Override
    public abstract String toString();
}
