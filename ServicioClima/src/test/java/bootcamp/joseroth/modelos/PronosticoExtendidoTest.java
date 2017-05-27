/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import bootcamp.joseroth.builders.PronosticoExtendidoBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class PronosticoExtendidoTest {
    
    private static PronosticoExtendido pe;
    
    public PronosticoExtendidoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ParseException {
        pe = new PronosticoExtendidoBuilder().withFecha(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("21 Apr 2017")).withDia("Mon").withEstado("Partly Cloudy").withMinima(58).withMaxima(64).build();
    }
    
    @AfterClass
    public static void tearDownClass() {
        pe = null;
    }

    @Test
    public void testSetGetFecha() throws ParseException {
        Date expResult = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("21 Apr 2017");
        pe.setFecha(expResult);
        Date result = pe.getFecha();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetDia() {
        String expResult = "Mon";
        pe.setDia(expResult);
        String result = pe.getDia();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetEstado() {
        String expResult = "Partly Cloudy";
        pe.setEstado(expResult);
        String result = pe.getEstado();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetMinima() {
        int expResult = 58;
        pe.setMinima(expResult);
        int result = pe.getMinima();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetMaxima() {
        int expResult = 64;
        pe.setMaxima(expResult);
        int result = pe.getMaxima();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdPronosticoExtendido() {
        int expResult = 1;
        pe.setIdPronosticoExtendido(expResult);
        int result = pe.getIdPronosticoExtendido();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdPronostico() {
        int expResult = 2;
        pe.setIdPronostico(expResult);
        int result = pe.getIdPronostico();
        assertEquals(expResult, result);
    }
    
}
