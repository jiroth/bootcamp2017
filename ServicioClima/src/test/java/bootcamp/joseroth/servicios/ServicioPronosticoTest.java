/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

import bootcamp.joseroth.modelos.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author José Ignacio Roth
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServicioPronosticoTest {
    private static Connection h2;
    private static String url = "jdbc:h2:mem:ServicioClima;MODE=MySQL;IGNORECASE=TRUE;INIT=runscript from 'src/main/resources/BaseDeDatos.sql'";
    private static String user = "root";
    private static String pwds = "1234";
    private static Statement st;
    private static ServicioPronostico sp;
    
    public ServicioPronosticoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        h2 = DriverManager.getConnection(url, user, pwds);
        sp = new ServicioPronostico();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        st = null;
        h2.close();
    }
    
    @Before
    public void setUp() throws SQLException {
        st = h2.createStatement();
    }
    
    @After
    public void tearDown() {
        st = null;
    }

    @Test
    public void testTextoAfechaTrue() throws ParseException {
        String texto = "Fri, 21 Apr 2017 05:00 PM ART";
        Date expResult = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US).parse(texto);
        Date result = sp.textoAfecha(texto, true);
        assertEquals(expResult, result);
    }
    
    public void testTextoAfechaFalse() throws ParseException {
        String texto = "21 Apr 2017";
        Date expResult = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(texto);
        Date result = sp.textoAfecha(texto, false);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatoFecha() throws ParseException {
        String texto = "21 Apr 2017";
        Date fecha = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(texto);
        String result = sp.formatoFecha(fecha);
        String expResult = "2017-04-21 00:00";
        assertEquals(expResult, result);
    }

    @Test
    public void test1RegistrarViento() {
        String sql = "insert into Viento (idViento, direccion, velocidad) values (1, 170, 10);";
        try {
            st.executeUpdate(sql);
            assertTrue(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("No se registró...");
        }
    }
    
    @Test
    public void testGetViento() throws Exception {
        String sql = "select * from Viento";
        ResultSet resultado = st.executeQuery(sql);
        Viento v = new Viento();
        if (resultado != null) {
            if (resultado.next()) {
                v.setIdViento(resultado.getInt("idViento"));
                v.setDireccion(resultado.getInt("direccion"));
                v.setVelocidad(resultado.getInt("velocidad"));
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        assertEquals(1, v.getIdViento());
        assertEquals(170, v.getDireccion());
        assertEquals(10, v.getVelocidad());
    }

    @Test
    public void test2RegistrarAtmosfera() {
        String sql = "insert into Atmosfera (idAtmosfera, humedad, visibilidad) values (1, 20, 10);";
        try {
            st.executeUpdate(sql);
            assertTrue(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("No se registró...");
        }
    }
    
    @Test
    public void testGetAtmosfera() throws Exception {
        String sql = "select * from Atmosfera";
        ResultSet resultado = st.executeQuery(sql);
        Atmosfera a = new Atmosfera();
        if (resultado != null) {
            if (resultado.next()) {
                a.setIdAtmosfera(resultado.getInt("idAtmosfera"));
                a.setHumedad(resultado.getInt("humedad"));
                a.setVisibilidad(resultado.getDouble("visibilidad"));
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        assertEquals(1, a.getIdAtmosfera());
        assertEquals(20, a.getHumedad());
        assertEquals(10, a.getVisibilidad(), 0.0);
    }

    @Test
    public void test3RegistrarUbicacion() {
        String sql = "insert into Ubicacion (idUbicacion, ciudad, pais) values (1, 'Córdoba', 'Argentina');";
        try {
            st.executeUpdate(sql);
            assertTrue(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("No se registró...");
        }
    }
    
    @Test
    public void testGetUbicacion() throws Exception {
        String sql = "select * from Ubicacion";
        ResultSet resultado = st.executeQuery(sql);
        Ubicacion u = new Ubicacion();
        if (resultado != null) {
            if (resultado.next()) {
                u.setIdUbicacion(resultado.getInt("idUbicacion"));
                u.setCiudad(resultado.getString("ciudad"));
                u.setPais(resultado.getString("pais"));
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        assertEquals(1, u.getIdUbicacion());
        assertEquals("Córdoba", u.getCiudad());
        assertEquals("Argentina", u.getPais());
    }

    @Test
    public void test4RegistrarPronostico() {        
        String sql = "insert into Pronostico(idPronostico, fecha, idUbicacion, temperatura, estado, idAtmosfera, idViento) "
                + "values(1, '2017-04-21', 1, 70, 'Nublado', 1, 1);";
        try {
            st.executeUpdate(sql);
            assertTrue(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("No se registró...");
        }
    }
    
    @Test
    public void testGetPronostico() throws Exception {
        String sql = "select * from Pronostico";
        ResultSet resultado = st.executeQuery(sql);
        Pronostico p = new Pronostico();
        if (resultado != null) {
            if (resultado.next()) {
                p.setIdPronostico(resultado.getInt("idPronostico"));
                p.setFecha(resultado.getDate("fecha"));
                p.setUbicacion(new Ubicacion(resultado.getInt("idUbicacion")));
                p.setTemperatura(resultado.getInt("temperatura"));
                p.setEstado(resultado.getString("estado"));
                p.setAtmosfera(new Atmosfera(resultado.getInt("idAtmosfera")));
                p.setViento(new Viento(resultado.getInt("idViento")));
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        assertEquals(1, p.getIdPronostico());
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("21 apr 2017"), p.getFecha());
        assertEquals(1, p.getUbicacion().getIdUbicacion());
        assertEquals(70, p.getTemperatura());
        assertEquals("Nublado", p.getEstado());
        assertEquals(1, p.getAtmosfera().getIdAtmosfera());
        assertEquals(1, p.getViento().getIdViento());
    }

    @Test
    public void test5RegistrarPronosticoExtendido() throws ParseException {
        String sql = "insert into PronosticoExtendido (idPronosticoExtendido, fecha, dia, estado, minima, maxima, idPronostico) "
                + "values(1, '2017-04-21', 'Fri', 'Partly Cloudy', 58, 64, 1);";
        try {
            st.executeUpdate(sql);
            assertTrue(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("No se registró...");
        }
    }
    
    @Test
    public void testGetPronosticoExtendido() throws Exception {
        String sql = "select * from PronosticoExtendido";
        PronosticoExtendido pe = new PronosticoExtendido();
        ResultSet resultado = st.executeQuery(sql);
        if (resultado != null) {
            if (resultado.next()) {
                pe.setIdPronosticoExtendido(resultado.getInt("idPronosticoExtendido"));
                pe.setFecha(resultado.getDate("fecha"));
                pe.setDia(resultado.getString("dia"));
                pe.setEstado(resultado.getString("estado"));
                pe.setMinima(resultado.getInt("minima"));
                pe.setMaxima(resultado.getInt("maxima"));
                pe.setIdPronostico(resultado.getInt("idPronostico"));
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        assertEquals(1, pe.getIdPronosticoExtendido());
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("21 apr 2017"), pe.getFecha());
        assertEquals("Fri", pe.getDia());
        assertEquals("Partly Cloudy", pe.getEstado());
        assertEquals(58, pe.getMinima());
        assertEquals(64, pe.getMaxima());
        assertEquals(1, pe.getIdPronostico());
    }
    
}
