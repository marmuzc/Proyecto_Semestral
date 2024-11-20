package LOGICA;

public class AsientoNormal extends Asientos{
    private String tipoAsiento;
    private String ubicacion;
    private String estado;
    private int numeroAsiento;

    public AsientoNormal(int numeroAsiento, boolean estado, String tipoAsiento, String ubicacion, int precio) {
        super(numeroAsiento, estado, tipoAsiento, ubicacion, precio);
        this.tipoAsiento = tipoAsiento;
        this.ubicacion = ubicacion;
       // this.estado = estado;
        this.numeroAsiento = numeroAsiento;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    @Override
    public void reservarAsiento() {
        this.estado = "Reservado";
    }

    @Override
    public void liberarAsiento() {
        this.estado = "Libre";
    }

    @Override
    public String toString() {
        return "AsientoNormal{" +
                "tipoAsiento='" + tipoAsiento + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", estado='" + estado + '\'' +
                ", numeroAsiento=" + numeroAsiento +
                '}';
    }
}
