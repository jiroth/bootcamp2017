/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Viento;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class VientoBuilderTest {
    
    public VientoBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWithIdViento() {
        int idViento = 1;
        Viento v = new VientoBuilder().withIdViento(idViento).build();
        assertEquals(idViento, v.getIdViento());
    }

    @Test
    public void testWithDireccion() {
        int direccion = 150;
        Viento v = new VientoBuilder().withDireccion(direccion).build();
        assertEquals(direccion, v.getDireccion());
    }

    @Test
    public void testWithVelocidad() {
        int velocidad = 10;
        Viento v = new VientoBuilder().withVelocidad(velocidad).build();
        assertEquals(velocidad, v.getVelocidad());
    }
    
}
