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
public class UbicacionTest {
    
    private static Ubicacion u;
    
    public UbicacionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        u = new Ubicacion();
    }

    @Test
    public void testSetGetCiudad() {
        String expResult = "Córdoba";
        u.setCiudad(expResult);
        String result = u.getCiudad();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetPais() {
        String expResult = "Argentina";
        u.setPais(expResult);
        String result = u.getPais();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdUbicacion() {
        int expResult = 1;
        u.setIdUbicacion(expResult);
        int result = u.getIdUbicacion();
        assertEquals(expResult, result);
    }
    
}
