/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Ubicacion;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class UbicacionBuilderTest {
    
    public UbicacionBuilderTest() {
    }

    @Test
    public void testWithIdUbicacion() {
        int idUbicacion = 1;
        Ubicacion u = new UbicacionBuilder().withIdUbicacion(idUbicacion).build();
        assertEquals(idUbicacion, u.getIdUbicacion());
    }

    @Test
    public void testWithCiudad() {
        String ciudad = "Córdoba";
        Ubicacion u = new UbicacionBuilder().withCiudad(ciudad).build();
        assertEquals(ciudad, u.getCiudad());
    }

    @Test
    public void testWithPais() {
        String pais = "Argentina";
        Ubicacion u = new UbicacionBuilder().withPais(pais).build();
        assertEquals(pais, u.getPais());
    }
    
}
