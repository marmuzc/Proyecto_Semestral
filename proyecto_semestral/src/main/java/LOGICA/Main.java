package LOGICA;

public class Main {
    public static void main(String[] args) {
        Administrador admin = Administrador.getInstance();
        String fecha = "10/12";
        String hora = "15:30";
        Recorrido recorrido1 = admin.crearRecorrido("Santiago", "Valparaíso", 5000, 2, fecha, hora);

        System.out.println(recorrido1);

        int precioBoleto = recorrido1.comprarAsiento(1);
        if (precioBoleto != -1) {
            System.out.println("Asiento comprado. Precio total: " + precioBoleto);
        }

        precioBoleto = recorrido1.comprarAsiento(1);
        if (precioBoleto == -1) {
            System.out.println("No se pudo comprar el asiento, ya está ocupado.");
        }
    }
}
