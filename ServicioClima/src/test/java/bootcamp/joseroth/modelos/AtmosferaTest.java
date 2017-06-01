/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class AtmosferaTest {
    
    private static Atmosfera a;
    
    public AtmosferaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        a = new Atmosfera();
    }

    @Test
    public void testSetGetHumedad() {
        int expResult = 57;
        a.setHumedad(expResult);
        int result = a.getHumedad();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetVisibilidad() {
        double expResult = 17.1;
        a.setVisibilidad(expResult);
        double result = a.getVisibilidad();
        assertEquals(expResult, result, 0.1);
    }

    @Test
    public void testSetGetIdAtmosfera() {
        int expResult = 1;
        a.setIdAtmosfera(expResult);
        int result = a.getIdAtmosfera();
        assertEquals(expResult, result);
    }
    
}
