package LOGICA;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Recorrido {
    private final String origen;
    private final String destino;
    private final int precioBase;
    private final Bus bus;
    private final LocalDateTime fechaHora;

    public Recorrido(String origen, String destino, int precioBase, Bus bus, LocalDateTime fechaHora) {
        this.origen = origen;
        this.destino = destino;
        this.precioBase = precioBase;
        this.bus = bus;
        this.fechaHora = fechaHora;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getPrecioBase() {
        return precioBase;
    }

    public Bus getBus() {
        return bus;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Permite a un usuario comprar un asiento específico.
     *
     * @param numeroAsiento Número del asiento a comprar.
     * @return El precio total del boleto (precio del recorrido + precio del asiento).
     */
    public int comprarAsiento(int numeroAsiento) {
        ArrayList<Asientos> asientos = bus.getAsientosArray(); // Usamos el método correcto

        for (Asientos asiento : asientos) {
            if (asiento.getNumero() == numeroAsiento) {
                if (asiento.isOcupado()) {
                    System.out.println("El asiento ya está ocupado.");
                    return -1;
                }
                asiento.setOcupado(true);
                return precioBase + asiento.getPrecio();
            }
        }

        System.out.println("Asiento no encontrado.");
        return -1;
    }

    @Override
    public String toString() {
        return "Recorrido{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", precioBase=" + precioBase +
                ", bus=" + bus.getTipo() +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
