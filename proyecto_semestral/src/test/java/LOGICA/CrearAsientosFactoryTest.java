package LOGICA;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para la clase CrearAsientosFactory.
 */
public class CrearAsientosFactoryTest {

    /**
     * Prueba para crear un asiento de tipo NORMAL.
     * Se verifica que el asiento se crea correctamente y sus atributos son los esperados.
     */
    @Test
    public void testCrearAsientoNormal() {
        CrearAsientosFactory factory = new CrearAsientosFactory();
        int numeroAsiento = 1;

        Asientos asiento = factory.crearAsiento(TipoAsiento.NORMAL, numeroAsiento);

        assertNotNull(asiento, "El asiento creado no debe ser nulo.");
        assertEquals("NORMAL", asiento.getTipo(), "El tipo del asiento debería ser NORMAL.");
        assertEquals(numeroAsiento, asiento.getNumero(), "El número del asiento no coincide.");
        System.out.println("Asiento creado: " + asiento);
    }

    /**
     * Prueba para crear un asiento de tipo SEMICAMA.
     * Se verifica que el asiento se crea correctamente y sus atributos son los esperados.
     */
    @Test
    public void testCrearAsientoSemiCama() {
        CrearAsientosFactory factory = new CrearAsientosFactory();
        int numeroAsiento = 2;

        Asientos asiento = factory.crearAsiento(TipoAsiento.SEMICAMA, numeroAsiento);

        assertNotNull(asiento, "El asiento creado no debe ser nulo.");
        assertEquals("SEMICAMA", asiento.getTipo(), "El tipo del asiento debería ser SEMICAMA.");
        assertEquals(numeroAsiento, asiento.getNumero(), "El número del asiento no coincide.");
        System.out.println("Asiento creado: " + asiento);
    }

    /**
     * Prueba para crear un asiento de tipo CAMA.
     * Se verifica que el asiento se crea correctamente y sus atributos son los esperados.
     */
    @Test
    public void testCrearAsientoCama() {
        CrearAsientosFactory factory = new CrearAsientosFactory();
        int numeroAsiento = 3;

        Asientos asiento = factory.crearAsiento(TipoAsiento.CAMA, numeroAsiento);

        assertNotNull(asiento, "El asiento creado no debe ser nulo.");
        assertEquals("CAMA", asiento.getTipo(), "El tipo del asiento debería ser CAMA.");
        assertEquals(numeroAsiento, asiento.getNumero(), "El número del asiento no coincide.");
        System.out.println("Asiento creado: " + asiento);
    }

    /**
     * Prueba para crear un asiento con un tipo de asiento nulo.
     * Se verifica que se lanza una excepción NullPointerException.
     */
    @Test
    public void testCrearAsientoTipoInvalido() {
        CrearAsientosFactory factory = new CrearAsientosFactory();
        int numeroAsiento = 4;

        try {
            factory.crearAsiento(null, numeroAsiento);
            fail("Se esperaba una excepción al usar un tipo de asiento nulo.");
        } catch (NullPointerException e) {
            System.out.println("Excepción capturada correctamente: " + e.getMessage());
        }
    }
}