package LOGICA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class CrearRecorridoFactoryTest {

    @Test
    public void testCrearRecorridoUnPiso() {
        CrearRecorridoFactory recorridoFactory = new CrearRecorridoFactory();
        String origen = "Santiago";
        String destino = "Valparaíso";
        int precio = 5000;
        int pisos = 1;
        LocalDateTime fechaHora = LocalDateTime.of(2024, 12, 1, 10, 0);


        Recorrido recorrido = recorridoFactory.crearRecorrido(origen, destino, precio, pisos, fechaHora);

        assertNotNull(recorrido, "El recorrido no debería ser null");
        System.out.println("Origen esperado: " + origen + ", Origen obtenido: " + recorrido.getOrigen());
        assertEquals(origen, recorrido.getOrigen(), "El origen no coincide");
        System.out.println("Destino esperado: " + destino + ", Destino obtenido: " + recorrido.getDestino());
        assertEquals(destino, recorrido.getDestino(), "El destino no coincide");
        System.out.println("Precio esperado: " + precio + ", Precio obtenido: " + recorrido.getPrecioBase());
        assertEquals(precio, recorrido.getPrecioBase(), "El precio no coincide");
        System.out.println("Fecha y hora esperada: " + fechaHora + ", Fecha y hora obtenida: " + recorrido.getFechaHora());
        assertEquals(fechaHora, recorrido.getFechaHora(), "La fecha y hora no coinciden");
        System.out.println("Bus esperado: BusUnPiso, Bus obtenido: " + recorrido.getBus().getClass().getSimpleName());
        assertTrue(recorrido.getBus() instanceof BusUnPiso, "El bus debería ser de un piso");
    }

    @Test
    public void testCrearRecorridoDosPisos() {
        CrearRecorridoFactory recorridoFactory = new CrearRecorridoFactory();
        String origen = "Concepción";
        String destino = "Temuco";
        int precio = 7000;
        int pisos = 2;
        LocalDateTime fechaHora = LocalDateTime.of(2024, 12, 2, 15, 0);

        Recorrido recorrido = recorridoFactory.crearRecorrido(origen, destino, precio, pisos, fechaHora);
        assertNotNull(recorrido, "El recorrido no debería ser null");
        System.out.println("Origen esperado: " + origen + ", Origen obtenido: " + recorrido.getOrigen());
        assertEquals(origen, recorrido.getOrigen(), "El origen no coincide");
        System.out.println("Destino esperado: " + destino + ", Destino obtenido: " + recorrido.getDestino());
        assertEquals(destino, recorrido.getDestino(), "El destino no coincide");
        System.out.println("Precio esperado: " + precio + ", Precio obtenido: " + recorrido.getPrecioBase());
        assertEquals(precio, recorrido.getPrecioBase(), "El precio no coincide");
        System.out.println("Fecha y hora esperada: " + fechaHora + ", Fecha y hora obtenida: " + recorrido.getFechaHora());
        assertEquals(fechaHora, recorrido.getFechaHora(), "La fecha y hora no coinciden");
        System.out.println("Bus esperado: BusDosPisos, Bus obtenido: " + recorrido.getBus().getClass().getSimpleName());
        assertTrue(recorrido.getBus() instanceof BusDosPisos, "El bus debería ser de dos pisos");
    }

    @Test
    public void testCrearRecorridoConPisosInvalidos() {
        CrearRecorridoFactory recorridoFactory = new CrearRecorridoFactory();
        String origen = "Arica";
        String destino = "Iquique";
        int precio = 4000;
        int pisos = 3;
        LocalDateTime fechaHora = LocalDateTime.of(2024, 12, 3, 8, 0);

        Recorrido recorrido = recorridoFactory.crearRecorrido(origen, destino, precio, pisos, fechaHora);

        System.out.println("Bus esperado: null, Bus obtenido: " + (recorrido.getBus() == null ? "null" : recorrido.getBus().getClass().getSimpleName()));
        assertNull(recorrido.getBus(), "El bus debería ser null para un número inválido de pisos");
    }
}
