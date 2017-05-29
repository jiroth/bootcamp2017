/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapters;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class AdapterEstadoClimaInglesEspanolTest {
    
    public static Ingles ingles;
    public static AdapterEstadoClimaInglesEspanol adapterEstadoClimaInglesEspanol;
    
    public AdapterEstadoClimaInglesEspanolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ingles = new EstadoClimaInglesImpl();
        ingles.set("Sunny");
        adapterEstadoClimaInglesEspanol = new AdapterEstadoClimaInglesEspanol();
    }

    @Test
    public void testSetEstadoClimaIngles() {
        adapterEstadoClimaInglesEspanol.set(ingles);
        assertTrue(true);
    }

    @Test
    public void testGetEstado() {
        Espanol estadoClimaEspanol = new AdapterEstadoClimaInglesEspanol();
        estadoClimaEspanol.set(ingles);
        assertEquals("Soleado", estadoClimaEspanol.get());
    }
    
}
