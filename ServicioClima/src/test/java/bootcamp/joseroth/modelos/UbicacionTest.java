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
public class UbicacionTest {
    
    private static Ubicacion u;
    
    public UbicacionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        u = new Ubicacion("Córdoba", "Argentina");
    }
    
    @AfterClass
    public static void tearDownClass() {
        u = null;
    }

    @Test
    public void testSetGetCiudad() {
        System.out.println("set y get Ciudad");
        String expResult = "Córdoba";
        u.setCiudad(expResult);
        String result = u.getCiudad();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetPais() {
        System.out.println("set y get Pais");
        String expResult = "Argentina";
        u.setPais(expResult);
        String result = u.getPais();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdUbicacion() {
        System.out.println("set y get IdUbicacion");
        int expResult = 1;
        u.setIdUbicacion(expResult);
        int result = u.getIdUbicacion();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Córdoba, Argentina";
        String result = u.toString();
        assertEquals(expResult, result);
    }
    
}
