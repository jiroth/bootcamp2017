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
public class VientoTest {
    
    private static Viento v;
    
    public VientoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        v = new Viento(200, 5);
    }
    
    @AfterClass
    public static void tearDownClass() {
        v = null;
    }

    @Test
    public void testSetGetDireccion() {
        System.out.println("set y get Direccion");
        int expResult = 200;
        v.setDireccion(expResult);
        int result = v.getDireccion();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetVelocidad() {
        System.out.println("set y get Velocidad");
        int expResult = 5;
        v.setVelocidad(expResult);
        int result = v.getVelocidad();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdViento() {
        System.out.println("set y get IdViento");
        int expResult = 1;
        v.setIdViento(expResult);
        int result = v.getIdViento();
        assertEquals(expResult, result);
    }
    
}
