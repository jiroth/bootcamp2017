/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Atmosfera;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class AtmosferaBuilderTest {
    
    public AtmosferaBuilderTest() {
    }

    @Test
    public void testWithIdAtmosfera() {
        int idAtmosfera = 1;
        Atmosfera a = new AtmosferaBuilder().withIdAtmosfera(idAtmosfera).build();
        assertEquals(idAtmosfera, a.getIdAtmosfera());
    }

    @Test
    public void testWithHumedad() {
        int humedad = 1;
        Atmosfera a = new AtmosferaBuilder().withHumedad(humedad).build();
        assertEquals(humedad, a.getHumedad());
    }

    @Test
    public void testWithVisibilidad() {
        int visibilidad = 1;
        Atmosfera a = new AtmosferaBuilder().withVisibilidad(visibilidad).build();
        assertEquals(visibilidad, a.getVisibilidad(), 0.0);
    }
    
}
