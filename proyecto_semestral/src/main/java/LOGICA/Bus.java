package LOGICA;

import java.util.ArrayList;

/**
 * Clase Bus que representa un bus.
 */
public abstract class Bus {
    protected int AsientoNormal;
    protected int AsientoSemiCama;
    protected int AsientoCama;
    private int totalAsientos;
    private int costo;
    private final ArrayList<Asientos> asientos;
    private final CrearAsientosFactory crearAsientos;

    /**
     * Constructor de la clase Bus.
     */
    public Bus() {
        crearAsientos = new CrearAsientosFactory();
        asientos = new ArrayList<>();
    }

    /**
     * Crea los asientos del bus y los agrega al ArrayList.
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
     * @param tipo     Tipo de asiento (NORMAL, SEMICAMA, CAMA).
     * @param cantidad Cantidad de asientos a crear.
     * @param inicio   Índice inicial para los números de asiento.
     * @return El índice actualizado después de agregar los asientos.
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

    /**
     * Obtiene el ArrayList de asientos del bus.
     *
     * @return El ArrayList de asientos.
     */
    public ArrayList<Asientos> getAsientosArray() {
        return asientos;
    }

    /**
     * Obtiene el tipo de bus.
     *
     * @return El tipo de bus.
     */
    public abstract String getTipo();

    /**
     * Obtiene la cantidad de pisos del bus.
     *
     * @return La cantidad de pisos del bus.
     */
    public abstract int getCantidadPisos();

    /**
     * Obtiene el total de asientos del bus.
     *
     * @return El total de asientos del bus.
     */
    public int getAsientosTotalInt() {
        return totalAsientos;
    }

    /**
     * Obtiene la cantidad de asientos cama del bus.
     *
     * @return La cantidad de asientos cama del bus.
     */
    public int getAsientosCamaInt() {
        return AsientoCama;
    }

    /**
     * Obtiene la cantidad de asientos semi-cama del bus.
     *
     * @return La cantidad de asientos semi-cama del bus.
     */
    public int getAsientosSemiInt() {
        return AsientoSemiCama;
    }

    /**
     * Obtiene la cantidad de asientos normales del bus.
     *
     * @return La cantidad de asientos normales del bus.
     */
    public int getAsientosNormalInt() {
        return AsientoNormal;
    }

    /**
     * Obtiene el costo del bus.
     *
     * @return El costo del bus.
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Establece el costo del bus.
     *
     * @param precio El nuevo costo del bus.
     */
    public void setCosto(int precio) {
        this.costo = precio;
    }

    /**
     * Devuelve una representación en cadena del bus.
     *
     * @return Una cadena que representa el bus.
     */
    @Override
    public abstract String toString();
}