/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

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
    private static SimpleDateFormat formatter;
    private static String s;
    private static Date fecha;
    
    public PronosticoExtendidoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ParseException {
        s = "21 Apr 2017";
        formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        fecha = formatter.parse(s);
        pe = new PronosticoExtendido(fecha, "Mon", "Partly Cloudy", 58, 64);
    }
    
    @AfterClass
    public static void tearDownClass() {
        pe = null;
        formatter = null;
        s = "";
        fecha = null;
    }

    @Test
    public void testSetGetFecha() throws ParseException {
        System.out.println("set y get Fecha");
        Date expResult = fecha;
        pe.setFecha(expResult);
        Date result = pe.getFecha();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetDia() {
        System.out.println("set y get Dia");
        String expResult = "Mon";
        pe.setDia(expResult);
        String result = pe.getDia();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetEstado() {
        System.out.println("set y get Estado");
        String expResult = "Partly Cloudy";
        pe.setEstado(expResult);
        String result = pe.getEstado();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetMinima() {
        System.out.println("set y get Minima");
        int expResult = 58;
        pe.setMinima(expResult);
        int result = pe.getMinima();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetMaxima() {
        System.out.println("set y get Maxima");
        int expResult = 64;
        pe.setMaxima(expResult);
        int result = pe.getMaxima();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdPronosticoExtendido() {
        System.out.println("set y get IdPronosticoExtendido");
        int expResult = 1;
        pe.setIdPronosticoExtendido(expResult);
        int result = pe.getIdPronosticoExtendido();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetIdPronostico() {
        System.out.println("set y get IdPronostico");
        int expResult = 2;
        pe.setIdPronostico(expResult);
        int result = pe.getIdPronostico();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "\nFecha: 21 abr 2017\nDía: Mon\nEstado: Partly Cloudy\nMínima: 58\nMáxima: 64";
        String result = pe.toString();
        assertEquals(expResult, result);
    }
    
}
