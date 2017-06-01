/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Dia;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class DiaBuilderTest {
    
    public DiaBuilderTest() {
    }

    @Test
    public void testWithIdPronosticoExtendido() {
        int id = 1;
        Dia d = new DiaBuilder().withIdPronosticoExtendido(id).build();
        assertEquals(id, d.getIdDia());
    }

    @Test
    public void testWithFecha() {
        Date fecha = new Date();
        Dia d = new DiaBuilder().withFecha(fecha).build();
        assertEquals(fecha, d.getFecha());
    }

    @Test
    public void testWithDia() {
        String dia = "Mon";
        Dia d = new DiaBuilder().withDia(dia).build();
        assertEquals(dia, d.getDia());
    }

    @Test
    public void testWithEstado() {
        String estado = "Sunny";
        Dia d = new DiaBuilder().withEstado(estado).build();
        assertEquals(estado, d.getEstado());
    }

    @Test
    public void testWithMinima() {
        int temp = 10;
        Dia d = new DiaBuilder().withMinima(temp).build();
        assertEquals(temp, d.getMinima());
    }

    @Test
    public void testWithMaxima() {
        int temp = 10;
        Dia d = new DiaBuilder().withMaxima(temp).build();
        assertEquals(temp, d.getMaxima());
    }

    @Test
    public void testWithIdPronostico() {
        int id = 10;
        Dia d = new DiaBuilder().withIdPronostico(id).build();
        assertEquals(id, d.getIdPronostico());
    }
    
}
