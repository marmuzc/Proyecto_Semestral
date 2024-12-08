package LOGICA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CrearRecorridoFactoryTest {
    CrearRecorridoFactory recorridoFactory = new CrearRecorridoFactory();
    String origen = "Santiago";
    String destino = "Valparaíso";
    int precio = 5000;
    String fecha = "01/12";
    String hora = "10:00";


    @DisplayName("Test Crear Recorrido piso valido")
    @Test
    public void testCrearRecorridoPisoValido() {
        int pisos = 1; //o 2
        Recorrido recorrido = recorridoFactory.crearRecorrido(origen, destino, precio, pisos, fecha, hora);
        assertNotNull(recorrido, "El recorrido no debería ser null");
        assertTrue(recorrido.getBus() instanceof BusUnPiso, "El bus debería ser de un piso");
        System.out.println("El recorrido: " + recorrido.getOrigen() + " a " +
                recorrido.getDestino() + ", Precio: " + recorrido.getPrecioBase() +
                ", Fecha: " + recorrido.getFecha() + ", Hora: " + recorrido.getHora() +
                ", Bus: " + (recorrido.getBus() instanceof BusUnPiso ? "Un Piso" : "Desconocido"));
    }

    @DisplayName("Test Crear Pisos invalidos")
    @Test
    public void testCrearRecorridoConPisosInvalidos() {
        int pisos = 3;
        Recorrido recorrido = recorridoFactory.crearRecorrido(origen, destino, precio, pisos, fecha, hora);
        assertNull(recorrido, "El recorrido debería ser null para un número inválido de pisos");
        if (recorrido == null) {
            System.out.println("El recorrido no fue creado porque el número de pisos es inválido.");
        }
    }

    @DisplayName("Test Crear Recorrido con Fecha y Hora válidas")
    @Test
    public void testCrearRecorridoValido() {
        Recorrido recorrido = recorridoFactory.crearRecorrido(
                "Santiago", "Valparaíso", 5000, 1, "01/12", "10:00"
        );
        assertNotNull(recorrido, "El recorrido no debería ser null");
        assertEquals("Santiago", recorrido.getOrigen());
        assertEquals("Valparaíso", recorrido.getDestino());
        assertEquals(5000, recorrido.getPrecioBase());
        assertEquals("01/12", recorrido.getFecha());
        assertEquals("10:00", recorrido.getHora());
    }

    @DisplayName("Test Crear Recorrido con Fecha inválida")
    @Test
    public void testCrearRecorridoFechaInvalida() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                recorridoFactory.crearRecorrido("Santiago", "Valparaíso", 5000, 1, "01-12", "10:00"));
        assertEquals("La fecha debe tener el formato DD/MM.", exception.getMessage());
        System.out.println("Excepción lanzada: " + exception.getMessage());
    }

    @DisplayName("Test Crear Recorrido con Hora inválida")
    @Test
    public void testCrearRecorridoHoraInvalida() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                recorridoFactory.crearRecorrido("Santiago", "Valparaíso", 5000, 1, "01/12", "10-00"));
        assertEquals("La hora debe tener el formato HH:mm.", exception.getMessage());
        System.out.println("Excepción lanzada: " + exception.getMessage());
    }

    @DisplayName("Test Crear Recorrido con Precio Negativo")
    @Test
    public void testCrearRecorridoPrecioNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                recorridoFactory.crearRecorrido("Santiago", "Valparaíso", -1, 1, "01/12", "10:00"));
        assertEquals("El precio base no puede ser negativo.", exception.getMessage());
        System.out.println("Excepción lanzada: " + exception.getMessage());
    }

    @DisplayName("Test Crear Recorrido con Origen y Destino nulos")
    @Test
    public void testCrearRecorridoOrigenDestinoNulos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                recorridoFactory.crearRecorrido(null, "Valparaíso", 5000, 1, "01/12", "10:00"));
        assertEquals("El origen y destino no pueden ser null.", exception.getMessage());
        System.out.println("Excepción lanzada: " + exception.getMessage());
    }

    @DisplayName("Test Crear Recorrido con Origen igual a Destino")
    @Test
    public void testCrearRecorridoOrigenIgualDestino() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                recorridoFactory.crearRecorrido("Santiago", "Santiago", 5000, 1, "01/12", "10:00"));
        assertEquals("El origen y destino no pueden ser iguales.", exception.getMessage());
        System.out.println("Excepción lanzada: " + exception.getMessage());
    }

}
