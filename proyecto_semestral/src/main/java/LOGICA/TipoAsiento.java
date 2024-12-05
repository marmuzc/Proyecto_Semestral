package LOGICA;

public enum TipoAsiento {
    NORMAL(2500),
    SEMICAMA(3000),
    CAMA(4500);

    private int precio;

    TipoAsiento(int precio) {
        this.precio = precio;
    }

    public int getPrecio() {
        return this.precio;
    }
    public String toString() {
        return this.name();
    }
}