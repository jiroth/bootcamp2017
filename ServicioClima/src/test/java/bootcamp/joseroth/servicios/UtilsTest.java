/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

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
public class UtilsTest {
    private static Utils utils;
    
    public UtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        utils = new Utils();
    }

    @Test
    public void test01TextoAfechaTrue() throws ParseException {
        String texto = "Fri, 21 Apr 2017 05:00 PM ART";
        Date expResult = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US).parse(texto);
        Date result = utils.textoAfecha(texto, true);
        assertEquals(expResult, result);
    }
    
    public void test02TextoAfechaFalse() throws ParseException {
        String texto = "21 Apr 2017";
        Date expResult = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(texto);
        Date result = utils.textoAfecha(texto, false);
        assertEquals(expResult, result);
    }

    @Test
    public void test03FormatoFecha() throws ParseException {
        String texto = "21 Apr 2017";
        Date fecha = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(texto);
        String result = utils.formatoFecha(fecha);
        String expResult = "2017-04-21 00:00";
        assertEquals(expResult, result);
    }
    
}
