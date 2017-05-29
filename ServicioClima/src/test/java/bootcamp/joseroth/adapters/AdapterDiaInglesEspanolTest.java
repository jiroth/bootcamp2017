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
public class AdapterDiaInglesEspanolTest {
    
    private static Ingles ingles;
    private static AdapterDiaInglesEspanol adapterDiaInglesEspanol;
    
    public AdapterDiaInglesEspanolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ingles = new DiaInglesImpl();
        ingles.set("Mon");
        adapterDiaInglesEspanol = new AdapterDiaInglesEspanol();
    }

    @Test
    public void testSetDiasEnIngles() {
        adapterDiaInglesEspanol.set(ingles);
        assertTrue(true);
    }

    @Test
    public void testGetDia() {
        Espanol diaEnEspanol = new AdapterDiaInglesEspanol();
        diaEnEspanol.set(ingles);
        assertEquals("Lunes", diaEnEspanol.get());
    }
    
}
