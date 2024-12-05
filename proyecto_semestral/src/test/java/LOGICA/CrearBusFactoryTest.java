package LOGICA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CrearBusFactoryTest {

    @Test
    public void testCrearBusUnPiso() {
        CrearBusFactory factory = new CrearBusFactory();
        Bus bus = factory.crearBus(1);  // Creamos un bus de 1 piso
        assertNotNull(bus, "El bus no debe ser nulo.");
        System.out.println("Esperado: BusUnPiso\nObtenido: " + bus.getClass().getSimpleName());
        assertTrue(bus instanceof BusUnPiso, "El bus creado debería ser una instancia de BusUnPiso.");
    }

    @Test
    public void testCrearBusDosPisos() {
        CrearBusFactory factory = new CrearBusFactory();
        Bus bus = factory.crearBus(2);  // Creamos un bus de 2 pisos
        assertNotNull(bus, "El bus no debe ser nulo.");
        System.out.println("Esperado: BusDosPisos\nObtenido: " + bus.getClass().getSimpleName());
        assertTrue(bus instanceof BusDosPisos, "El bus creado debería ser una instancia de BusDosPisos.");
    }

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

    @Test
    public void testCrearBusConValorPorDefecto() {
        CrearBusFactory factory = new CrearBusFactory();
        Bus bus = factory.crearBus(3);
        System.out.println("Esperado: null (no debe crearse un bus con 3 pisos)\nObtenido: " + bus);
        assertNull(bus, "El bus no debería haberse creado con un número de pisos no válido.");
    }
}
