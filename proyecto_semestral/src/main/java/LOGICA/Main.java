package LOGICA;

public class Main {
    public static void main(String[] args) {
        Administrador admin = Administrador.getInstance();

        // Fecha y hora como cadenas en formato DD/MM y HH:mm
        String fecha = "10/12"; // Día/Mes
        String hora = "15:30"; // Hora:Minuto

        // Crear un recorrido
        Recorrido recorrido1 = admin.crearRecorrido("Santiago", "Valparaíso", 5000, 2, fecha, hora);

        // Mostrar información del recorrido
        System.out.println(recorrido1);

        // Comprar un asiento
        int precioBoleto = recorrido1.comprarAsiento(1);
        if (precioBoleto != -1) {
            System.out.println("Asiento comprado. Precio total: " + precioBoleto);
        }

        // Intentar comprar el mismo asiento nuevamente
        precioBoleto = recorrido1.comprarAsiento(1);
        if (precioBoleto == -1) {
            System.out.println("No se pudo comprar el asiento, ya está ocupado.");
        }
    }
}
