package LOGICA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

/**
 * Clase de pruebas para la clase Administrador.
 */
public class CrearAdministradorTest {
    Administrador administrador = Administrador.getInstance();

    /**
     * Configuración inicial antes de cada prueba.
     * Se obtiene la instancia del administrador y se limpia la lista de recorridos.
     */
    @BeforeEach
    public void setUp() {
        administrador = Administrador.getInstance();
        administrador.getRecorridos().clear(); // Limpiar la lista de recorridos
    }

    /**
     * Prueba para crear un recorrido válido.
     * Se verifica que el recorrido se crea correctamente y se añade a la lista de recorridos.
     */
    @Test
    @DisplayName("Test Crear Recorrido valido")
    public void testCrearRecorridoValido() {
        Recorrido recorrido = administrador.crearRecorrido(
                "Santiago", "Valparaíso", 5000, 1, "01/12", "10:00"
        );
        assertNotNull(recorrido, "El recorrido deberia ser creado correctamente.");
        assertEquals("Santiago", recorrido.getOrigen());
        assertEquals("Valparaíso", recorrido.getDestino());
        assertEquals(5000, recorrido.getPrecioBase());
        assertEquals("01/12", recorrido.getFecha());
        assertEquals("10:00", recorrido.getHora());
        ArrayList<Recorrido> recorridos = administrador.getRecorridos();
        assertEquals(1, recorridos.size(), "Debería haber 1 recorrido en la lista.");
        assertSame(recorrido, recorridos.get(0), "El recorrido creado debe estar en la lista.");
        System.out.println("Recorrido creado: " + recorrido);
    }

    /**
     * Prueba para crear un recorrido con una fecha inválida.
     * Se verifica que se lanza una excepción con el mensaje adecuado.
     */
    @Test
    @DisplayName("Test Crear Recorrido con Fecha invalida")
    public void testCrearRecorridoFechaInvalida() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                administrador.crearRecorrido("Santiago", "Valparaíso", 5000, 1, "01-12", "10:00"));
        assertEquals("La fecha debe tener el formato DD/MM.", exception.getMessage());
        System.out.println("Excepción lanzada: " + exception.getMessage());
    }

    /**
     * Prueba para crear un recorrido con una hora inválida.
     * Se verifica que se lanza una excepción con el mensaje adecuado.
     */
    @Test
    @DisplayName("Test Crear Recorrido con Hora invalida")
    public void testCrearRecorridoHoraInvalida() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                administrador.crearRecorrido("Santiago", "Valparaíso", 5000, 1, "01/12", "10-00"));
        assertEquals("La hora debe tener el formato HH:mm.", exception.getMessage());
        System.out.println("Excepción lanzada: " + exception.getMessage());
    }

    /**
     * Prueba para crear un recorrido con un bus inválido.
     * Se verifica que se lanza una excepción con el mensaje adecuado.
     */
    @Test
    @DisplayName("Test Crear Recorrido con Bus invalido")
    public void testCrearRecorridoBusInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                administrador.crearRecorrido("Santiago", "Valparaíso", 5000, 3, "01/12", "10:00"));
        assertEquals("El bus no puede ser null.", exception.getMessage());
        System.out.println("Excepción lanzada: " + exception.getMessage());
    }

    /**
     * Prueba para verificar que la lista de recorridos está inicialmente vacía.
     */
    @Test
    @DisplayName("Test Lista de Recorridos inicialmente vacia")
    public void testListaRecorridosInicialVacia() {
        ArrayList<Recorrido> recorridos = administrador.getRecorridos();
        assertNotNull(recorridos, "La lista de recorridos no debería ser null.");
        assertTrue(recorridos.isEmpty(), "La lista de recorridos debería estar inicialmente vacia.");
        System.out.println("Recorridos: " + recorridos);
    }

    /**
     * Prueba para crear múltiples recorridos.
     * Se verifica que se crean correctamente y se añaden a la lista de recorridos.
     */
    @Test
    @DisplayName("Test Múltiples Recorridos")
    public void testMultiplesRecorridos() {
        administrador.crearRecorrido("Santiago", "Valparaíso", 5000, 1, "01/12", "10:00");
        administrador.crearRecorrido("Viña del Mar", "Concepción", 8000, 2, "02/12", "12:30");
        ArrayList<Recorrido> recorridos = administrador.getRecorridos();
        assertEquals(2, recorridos.size(), "Deberían haberse creado 2 recorridos.");
        assertEquals("Santiago", recorridos.get(0).getOrigen());
        assertEquals("Viña del Mar", recorridos.get(1).getOrigen());
        System.out.println("Recorridos: " + recorridos);
    }
}