/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.PronosticoExtendido;
import bootcamp.joseroth.modelos.Ubicacion;
import bootcamp.joseroth.modelos.Viento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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
public class ServicioPronosticoTest {

    private static ServicioPronostico instance;
    private static SimpleDateFormat formatter;


    public ServicioPronosticoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        instance = new ServicioPronostico();

    }

    @AfterClass
    public static void tearDownClass() {
        instance = null;
        formatter = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTextoAfechaTrue() throws ParseException {
        System.out.println("textoAfecha");
        String s = "Fri, 21 Apr 2017 05:00 PM ART";
        boolean b = true;
        formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US);
        Date expResult = formatter.parse(s);
        Date result = instance.textoAfecha(s, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
    }

    @Test
    public void testRegistrarViento() throws Exception {
        System.out.println("registrarViento");
        int expResult = instance.getId("select max(idViento) as id from viento;") + 1;
        Viento v = new Viento(180,5);
        int result = instance.registrarViento(v);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegistrarAtmosfera() throws Exception {
//        System.out.println("registrarAtmosfera");
//        Atmosfera a = null;
//        ServicioPronostico instance = new ServicioPronostico();
//        int expResult = 0;
//        int result = instance.registrarAtmosfera(a);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testRegistrarUbicacion() throws Exception {
//        System.out.println("registrarUbicacion");
//        Ubicacion u = null;
//        ServicioPronostico instance = new ServicioPronostico();
//        int expResult = 0;
//        int result = instance.registrarUbicacion(u);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testRegistrarPronostico() throws Exception {
//        System.out.println("registrarPronostico");
//        Pronostico p = null;
//        ServicioPronostico instance = new ServicioPronostico();
//        int expResult = 0;
//        int result = instance.registrarPronostico(p);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testRegistrarPronosticoExtendido() {
//        System.out.println("registrarPronosticoExtendido");
//        PronosticoExtendido pe = null;
//        ServicioPronostico instance = new ServicioPronostico();
//        instance.registrarPronosticoExtendido(pe);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAtmosfera() throws Exception {
        System.out.println("getAtmosfera");
        int idAtmosfera = 1;
        ServicioPronostico instance = new ServicioPronostico();
        Atmosfera expResult = null;
        Atmosfera result = instance.getAtmosfera(idAtmosfera);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetViento() throws Exception {
//        System.out.println("getViento");
//        int idViento = 0;
//        ServicioPronostico instance = new ServicioPronostico();
//        Viento expResult = null;
//        Viento result = instance.getViento(idViento);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUbicacion() throws Exception {
//        System.out.println("getUbicacion");
//        int idUbicacion = 0;
//        ServicioPronostico instance = new ServicioPronostico();
//        Ubicacion expResult = null;
//        Ubicacion result = instance.getUbicacion(idUbicacion);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPronostico() throws Exception {
//        System.out.println("getPronostico");
//        int idPronostico = 0;
//        ServicioPronostico instance = new ServicioPronostico();
//        Pronostico expResult = null;
//        Pronostico result = instance.getPronostico(idPronostico);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPronosticoExtendido() throws Exception {
//        System.out.println("getPronosticoExtendido");
//        int idPronostico = 0;
//        ServicioPronostico instance = new ServicioPronostico();
//        ArrayList<PronosticoExtendido> expResult = null;
//        ArrayList<PronosticoExtendido> result = instance.getPronosticoExtendido(idPronostico);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
