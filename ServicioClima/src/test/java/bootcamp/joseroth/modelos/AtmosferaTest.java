/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import org.junit.AfterClass;
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
        a = new Atmosfera(57, 17.1);
    }
    
    @AfterClass
    public static void tearDownClass() {
        a = null;
    }

    @Test
    public void testSetGetHumedad() {
        System.out.println("set y get Humedad");
        int expResult = 57;
        a.setHumedad(expResult);
        int result = a.getHumedad();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetVisibilidad() {
        System.out.println("set y get Visibilidad");
        double expResult = 17.1;
        a.setVisibilidad(expResult);
        double result = a.getVisibilidad();
        assertEquals(expResult, result, 0.1);
    }

    @Test
    public void testSetGetIdAtmosfera() {
        System.out.println("set y get IdAtmosfera");
        int expResult = 1;
        a.setIdAtmosfera(expResult);
        int result = a.getIdAtmosfera();
        assertEquals(expResult, result);
    }
    
}
