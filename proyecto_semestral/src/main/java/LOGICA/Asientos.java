package LOGICA;

import javax.swing.plaf.PanelUI;

public  abstract class Asientos {
    protected int numeroAsiento;
    protected boolean estado; //que sea ocupado o libre,  reservado o no
    protected String tipoAsiento; //semicama, cama, normal
    protected String ubicacion; //ventana, pasillo
    protected int precio; //no se si es necesario


    public Asientos(int numeroAsiento, boolean estado, String tipoAsiento, String ubicacion, int precio) {
        this.numeroAsiento = numeroAsiento;
        this.estado = estado;
        this.tipoAsiento = tipoAsiento;
        this.ubicacion = ubicacion;
        this.precio = precio;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(String tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public abstract void reservarAsiento();
    public abstract void liberarAsiento();

    @Override
    public String toString() {
        return "Asientos{" +
                "numeroAsiento=" + numeroAsiento +
                ", estado=" + estado +
                ", tipoAsiento='" + tipoAsiento + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
