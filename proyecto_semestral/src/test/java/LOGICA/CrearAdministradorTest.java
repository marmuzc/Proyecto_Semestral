package LOGICA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;

public class CrearAdministradorTest {

    Administrador administrador = Administrador.getInstance();

    @DisplayName("Test Crear Recorrido Válido")
    @Test
    public void testCrearRecorridoValido() {
        Recorrido recorrido = administrador.crearRecorrido(
                "Santiago", "Valparaíso", 5000, 1, "01/12", "10:00"
        );

        assertNotNull(recorrido, "El recorrido debería ser creado correctamente.");
        assertEquals("Santiago", recorrido.getOrigen());
        assertEquals("Valparaíso", recorrido.getDestino());
        assertEquals(5000, recorrido.getPrecioBase());
        assertEquals("01/12", recorrido.getFecha());
        assertEquals("10:00", recorrido.getHora());

        ArrayList<Recorrido> recorridos = administrador.getRecorridos();
        assertEquals(1, recorridos.size(), "Debería haber 1 recorrido en la lista.");
        assertSame(recorrido, recorridos.get(0), "El recorrido creado debería estar en la lista.");
    }

    @DisplayName("Test Crear Recorrido con Fecha Inválida")
    @Test
    public void testCrearRecorridoFechaInvalida() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                administrador.crearRecorrido("Santiago", "Valparaíso", 5000, 1, "01-12", "10:00"));
        assertEquals("La fecha debe tener el formato DD/MM.", exception.getMessage());
    }

    @DisplayName("Test Crear Recorrido con Hora Inválida")
    @Test
    public void testCrearRecorridoHoraInvalida() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                administrador.crearRecorrido("Santiago", "Valparaíso", 5000, 1, "01/12", "10-00"));
        assertEquals("La hora debe tener el formato HH:mm.", exception.getMessage());
    }

    @DisplayName("Test Crear Recorrido con Bus Inválido")
    @Test
    public void testCrearRecorridoBusInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                administrador.crearRecorrido("Santiago", "Valparaíso", 5000, 3, "01/12", "10:00"));
        assertEquals("El bus no puede ser null.", exception.getMessage());
    }

    @DisplayName("Test Lista de Recorridos Inicialmente Vacía")
    @Test
    public void testListaRecorridosInicialVacia() {
        ArrayList<Recorrido> recorridos = administrador.getRecorridos();
        assertNotNull(recorridos, "La lista de recorridos no debería ser null.");
        assertTrue(recorridos.isEmpty(), "La lista de recorridos debería estar inicialmente vacía.");
    }

    @DisplayName("Test Múltiples Recorridos")
    @Test
    public void testMultiplesRecorridos() {
        administrador.crearRecorrido("Santiago", "Valparaíso", 5000, 1, "01/12", "10:00");
        administrador.crearRecorrido("Viña del Mar", "Concepción", 8000, 2, "02/12", "12:30");

        ArrayList<Recorrido> recorridos = administrador.getRecorridos();
        assertEquals(2, recorridos.size(), "Deberían haberse creado 2 recorridos.");
        assertEquals("Santiago", recorridos.get(0).getOrigen());
        assertEquals("Viña del Mar", recorridos.get(1).getOrigen());
    }
}
