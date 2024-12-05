package LOGICA;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Administrador admin = new Administrador();

        LocalDateTime fechaHora = LocalDateTime.of(2024, 12, 10, 15, 30); // Año, mes, día, hora, minuto
        Recorrido recorrido1 = admin.crearRecorrido("Santiago", "Valparaíso", 5000, 2, fechaHora);

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
