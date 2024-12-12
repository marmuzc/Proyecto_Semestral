package LOGICA;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Clase Recorrido que representa el recorrido de un bus.
 */
public class Recorrido {
    private final String origen;
    private final String destino;
    private final int precioBase;
    private final Bus bus;
    private final String fecha; // Formato: DD/MM
    private final String hora;  // Formato: HH:mm

    /**
     * Constructor de la clase Recorrido.
     *
     * @param origen     El punto de origen del recorrido.
     * @param destino    El punto de destino del recorrido.
     * @param precioBase El costo base del recorrido (pues asientos tienen precio añadido).
     * @param bus        El bus asignado al recorrido.
     * @param fecha      La fecha del recorrido en formato DD/MM.
     * @param hora       La hora del recorrido en formato HH:mm.
     * @throws IllegalArgumentException Si alguno de los parámetros es inválido.
     */
    public Recorrido(String origen, String destino, int precioBase, Bus bus, String fecha, String hora) {
        if (!isFechaValida(fecha)) {
            throw new IllegalArgumentException("La fecha \"" + fecha + "\" no es válida. Asegúrate de que tenga el formato DD/MM.");
        }
        if (!isHoraValida(hora)) {
            throw new IllegalArgumentException("La hora \"" + hora + "\" no es válida. Asegúrate de que tenga el formato HH:mm.");
        }

        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo.");
        }
        if (bus == null) {
            throw new IllegalArgumentException("El bus no puede ser null.");
        }
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("El origen y destino no pueden ser null.");
        }
        if (origen.equals(destino)) {
            throw new IllegalArgumentException("El origen y destino no pueden ser iguales.");
        }

        this.origen = origen;
        this.destino = destino;
        this.precioBase = precioBase;
        this.bus = bus;
        this.fecha = fecha;
        this.hora = hora;
    }

    public static boolean isFechaValida(String fecha) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM")
                    .withResolverStyle(java.time.format.ResolverStyle.LENIENT);
            formatter.parse(fecha); // Solo valida el formato
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public static boolean isHoraValida(String hora) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
            LocalTime.parse(hora, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    /**
     * Permite a un usuario comprar un asiento específico.
     *
     * @param numeroAsiento Número del asiento a comprar.
     * @return El precio total del boleto (precio del recorrido + precio del asiento).
     */
    public int comprarAsiento(int numeroAsiento) {
        ArrayList<Asientos> asientos = bus.getAsientosArray();

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

    /**
     * Obtiene un asiento específico por su número.
     *
     * @param numeroAsiento Número del asiento a buscar.
     * @return El objeto Asientos si se encuentra; null si no existe.
     */
    public Asientos obtenerAsiento(int numeroAsiento) {
        ArrayList<Asientos> asientos = bus.getAsientosArray();

        for (Asientos asiento : asientos) {
            if (asiento.getNumero() == numeroAsiento) {
                return asiento;
            }
        }

        return null; // Si no encuentra el asiento
    }

    @Override
    public String toString() {
        return "Recorrido{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", precioBase=" + precioBase +
                ", bus=" + bus +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
