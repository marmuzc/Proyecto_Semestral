package LOGICA;

public abstract class Asientos {
    protected int precio;
    private int numero;
    protected String tipo;
    public boolean ocupado;
    protected boolean auxSeleccionado;
    public Asientos(int numero) {
        this.ocupado = false;//empieza con todos los asientos desocupados
        this.auxSeleccionado = false;//empieza con todos los asientos sin seleccionar
        this.numero = numero;
    }

    public int getPrecio() {
        return precio;
    }
    public String getTipo() {
        return tipo;
    }
    public int getNumero() {
        return numero;
    }
    public void setOcupado(boolean estado) {
        ocupado = estado;
    }
    public boolean isOcupado() {
        return ocupado;
    }
    public boolean getAuxSeleccionado() {
        return auxSeleccionado;
    }
    public void setAuxSeleccionado(boolean auxSeleccionado) {
        this.auxSeleccionado = auxSeleccionado;
    }

    public String toString() {
        return "Asiento "+ numero + "(" + tipo + ")";
    }
}