/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import bootcamp.joseroth.builders.DiaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class DiaTest {
    
    private static Dia dia;
    
    public DiaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ParseException {
        dia = new Dia();
    }

    @Test
    public void testSetGetFecha() throws ParseException {
        Date expResult = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("21 Apr 2017");
        dia.setFecha(expResult);
        Date result = dia.getFecha();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetDia() {
        String expResult = "Mon";
        dia.setDia(expResult);
        String result = dia.getDia();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetEstado() {
        String expResult = "Partly Cloudy";
        dia.setEstado(expResult);
        String result = dia.getEstado();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetMinima() {
        int expResult = 58;
        dia.setMinima(expResult);
        int result = dia.getMinima();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetMaxima() {
        int expResult = 64;
        dia.setMaxima(expResult);
        int result = dia.getMaxima();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdPronosticoExtendido() {
        int expResult = 1;
        dia.setIdDia(expResult);
        int result = dia.getIdDia();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdPronostico() {
        int expResult = 2;
        dia.setIdPronostico(expResult);
        int result = dia.getIdPronostico();
        assertEquals(expResult, result);
    }
    
}
