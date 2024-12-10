package LOGICA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para la clase CrearBusFactory.
 */
public class CrearBusFactoryTest {

    /**
     * Prueba para crear un bus de un piso.
     * Se verifica que el bus se crea correctamente y es una instancia de BusUnPiso.
     */
    @Test
    public void testCrearBusUnPiso() {
        CrearBusFactory factory = new CrearBusFactory();
        Bus bus = factory.crearBus(1);  // Creamos un bus de 1 piso
        assertNotNull(bus, "El bus no debe ser nulo.");
        System.out.println("Esperado: BusUnPiso\nObtenido: " + bus.getClass().getSimpleName());
        assertTrue(bus instanceof BusUnPiso, "El bus creado debería ser una instancia de BusUnPiso.");
    }

    /**
     * Prueba para crear un bus de dos pisos.
     * Se verifica que el bus se crea correctamente y es una instancia de BusDosPisos.
     */
    @Test
    public void testCrearBusDosPisos() {
        CrearBusFactory factory = new CrearBusFactory();
        Bus bus = factory.crearBus(2);  // Creamos un bus de 2 pisos
        assertNotNull(bus, "El bus no debe ser nulo.");
        System.out.println("Esperado: BusDosPisos\nObtenido: " + bus.getClass().getSimpleName());
        assertTrue(bus instanceof BusDosPisos, "El bus creado debería ser una instancia de BusDosPisos.");
    }

    /**
     * Prueba para crear un bus con un número de pisos inválido.
     * Se verifica que no se crea un bus con 0 o un número negativo de pisos.
     */
    @Test
    public void testCrearBusConPisosInvalidos() {
        CrearBusFactory factory = new CrearBusFactory();
        Bus bus = factory.crearBus(0);
        System.out.println("Esperado: null (no debe crearse un bus con 0 pisos)\nObtenido: " + bus);
        assertNull(bus, "El bus no debería haberse creado con un número de pisos no válido.");
        bus = factory.crearBus(-1);
        System.out.println("Esperado: null (no debe crearse un bus con -1 pisos)\nObtenido: " + bus);
        assertNull(bus, "El bus no debería haberse creado con un número de pisos negativo.");
    }

    /**
     * Prueba para crear un bus con un número de pisos no válido.
     * Se verifica que no se crea un bus con un número de pisos no válido.
     */
    @Test
    public void testCrearBusConValorPorDefecto() {
        CrearBusFactory factory = new CrearBusFactory();
        Bus bus = factory.crearBus(3);
        System.out.println("Esperado: null (no debe crearse un bus con 3 pisos)\nObtenido: " + bus);
        assertNull(bus, "El bus no debería haberse creado con un número de pisos no válido.");
    }
}