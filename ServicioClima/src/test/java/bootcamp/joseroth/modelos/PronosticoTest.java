/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class PronosticoTest {

    private static Pronostico p;
    private static SimpleDateFormat formatter;
    private static String s;
    private static Date fecha;
    private static Ubicacion ubicacion;
    private static Atmosfera atmosfera;
    private static Viento viento;
    private static ArrayList<PronosticoExtendido> pronosticoExtendido;

    public PronosticoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws ParseException {
        s = "Fri, 21 Apr 2017 05:00 PM ART";
        formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US);
        fecha = formatter.parse(s);
        ubicacion = new Ubicacion("Cordoba", "Argentina");
        ubicacion.setIdUbicacion(1);
        atmosfera = new Atmosfera(59, 18);
        atmosfera.setIdAtmosfera(1);
        viento = new Viento(203, 7);
        viento.setIdViento(1);
        p = new Pronostico(fecha, ubicacion, 64, "Cloudy", atmosfera, viento);
        pronosticoExtendido = new ArrayList<>();
    }

    @AfterClass
    public static void tearDownClass() {
        p = null;
        formatter = null;
        s = "";
        fecha = null;
        ubicacion = null;
        atmosfera = null;
        viento = null;
        pronosticoExtendido = null;
    }

    @Test
    public void testSetGetIdPronostico() {
        System.out.println("set y get IdPronostico");
        int expResult = 1;
        p.setIdPronostico(expResult);
        int result = p.getIdPronostico();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetFecha() {
        System.out.println("set y get Fecha");
        Date expResult = fecha;
        p.setFecha(fecha);
        Date result = p.getFecha();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetUbicacion() {
        System.out.println("set y get Ubicacion");
        Ubicacion expResult = ubicacion;
        p.setUbicacion(expResult);
        Ubicacion result = p.getUbicacion();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetTemperatura() {
        System.out.println("set y get Temperatura");
        int expResult = 64;
        p.setTemperatura(expResult);
        int result = p.getTemperatura();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetEstado() {
        System.out.println("set y get Estado");
        String expResult = "Cloudy";
        p.setEstado(expResult);
        String result = p.getEstado();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetAtmosfera() {
        System.out.println("set y get Atmosfera");
        Atmosfera expResult = atmosfera;
        p.setAtmosfera(atmosfera);
        Atmosfera result = p.getAtmosfera();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetViento() {
        System.out.println("set y get Viento");
        Viento expResult = viento;
        p.setViento(viento);
        Viento result = p.getViento();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetPronositicoExtendido() throws ParseException {
        System.out.println("set y get PronositicoExtendido");
        ArrayList<PronosticoExtendido> expResult = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        Date dia1 = sdf.parse("21 Apr 2017");
        Date dia2 = sdf.parse("22 Apr 2017");
        PronosticoExtendido pe1 = new PronosticoExtendido(dia1, "Fri", "Partly Cloudy", 58, 64);
        PronosticoExtendido pe2 = new PronosticoExtendido(dia2, "Sat", "Partly Cloudy", 53, 67);
        expResult.add(pe1);
        expResult.add(pe2);              
        p.setPronositicoExtendido(expResult);
        ArrayList<PronosticoExtendido> result = p.getPronositicoExtendido();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() throws ParseException {
        System.out.println("toString");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        Date dia1 = sdf.parse("21 Apr 2017");
        Date dia2 = sdf.parse("22 Apr 2017");
        PronosticoExtendido pe1 = new PronosticoExtendido(dia1, "Fri", "Partly Cloudy", 58, 64);
        PronosticoExtendido pe2 = new PronosticoExtendido(dia2, "Sat", "Partly Cloudy", 53, 67);
        pronosticoExtendido.add(pe1);
        pronosticoExtendido.add(pe2);              
        p.setPronositicoExtendido(pronosticoExtendido);
        String expResult = "Pronostico: \nFecha: Fri Apr 21 05:00:00 ART 2017\nUbicacion: Cordoba, Argentina\nTemperatura: 64\n"
                + "Estado: Cloudy\nAtmósfera: Humedad: 59, Visibilidad: 18.0\nViento: Direccion: 203, Velocidad: 7\n"
                + "Pronósitico Extendido: [\nFecha: 21 abr 2017\nDía: Fri\nEstado: Partly Cloudy\nMínima: 58\nMáxima: 64, "
                + "\nFecha: 22 abr 2017\nDía: Sat\nEstado: Partly Cloudy\nMínima: 53\nMáxima: 67]";
        String result = p.toString();
        assertEquals(expResult, result);
    }

}
