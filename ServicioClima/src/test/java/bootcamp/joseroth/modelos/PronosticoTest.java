/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import bootcamp.joseroth.builders.AtmosferaBuilder;
import bootcamp.joseroth.builders.PronosticoBuilder;
import bootcamp.joseroth.builders.DiaBuilder;
import bootcamp.joseroth.builders.UbicacionBuilder;
import bootcamp.joseroth.builders.VientoBuilder;
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
    private static Ubicacion ubicacion;
    private static Atmosfera atmosfera;
    private static Viento viento;
    private static ArrayList<Dia> pronosticoExtendido;

    public PronosticoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws ParseException {
        ubicacion = new UbicacionBuilder().withIdUbicacion(1).withCiudad("Córdoba").withPais("Argentina").build();
        atmosfera = new AtmosferaBuilder().withIdAtmosfera(1).withHumedad(59).withVisibilidad(18).build();
        viento = new VientoBuilder().withIdViento(1).withDireccion(203).withVelocidad(7).build();
        p = new PronosticoBuilder().withFecha(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US).parse("Fri, 26 May 2017 10:00 AM ART")).withEstado("Cloudy").withTemperatura(64).withAtmosfera(atmosfera).withViento(viento).withUbicacion(ubicacion).build();
        pronosticoExtendido = new ArrayList<>();
    }

    @AfterClass
    public static void tearDownClass() {
        p = null;
        ubicacion = null;
        atmosfera = null;
        viento = null;
        pronosticoExtendido = null;
    }

    @Test
    public void testSetGetIdPronostico() {
        int expResult = 1;
        p.setIdPronostico(expResult);
        int result = p.getIdPronostico();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetFecha() throws ParseException {
        Date expResult = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US).parse("Fri, 26 May 2017 10:00 AM ART");
        p.setFecha(expResult);
        Date result = p.getFecha();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetUbicacion() {
        Ubicacion expResult = ubicacion;
        p.setUbicacion(expResult);
        Ubicacion result = p.getUbicacion();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetTemperatura() {
        int expResult = 64;
        p.setTemperatura(expResult);
        int result = p.getTemperatura();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetEstado() {
        String expResult = "Cloudy";
        p.setEstado(expResult);
        String result = p.getEstado();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetAtmosfera() {
        Atmosfera expResult = atmosfera;
        p.setAtmosfera(atmosfera);
        Atmosfera result = p.getAtmosfera();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetViento() {
        Viento expResult = viento;
        p.setViento(viento);
        Viento result = p.getViento();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGetPronositicoExtendido() throws ParseException {
        ArrayList<Dia> expResult = new ArrayList<>();
        Dia pe1 = new DiaBuilder().withFecha(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("21 Apr 2017")).withDia("Fri").withEstado("Partly Cloudy").withMinima(58).withMaxima(64).build();
        Dia pe2 = new DiaBuilder().withFecha(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("22 Apr 2017")).withDia("Sat").withEstado("Partly Cloudy").withMinima(53).withMaxima(67).build();
        expResult.add(pe1);
        expResult.add(pe2);              
        p.setPronositicoExtendido(expResult);
        ArrayList<Dia> result = p.getPronositicoExtendido();
        assertEquals(expResult, result);
    }

}
