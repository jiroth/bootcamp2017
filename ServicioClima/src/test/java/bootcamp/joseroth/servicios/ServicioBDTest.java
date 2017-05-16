/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class ServicioBDTest {
    private static Connection h2;
    private static String url = "jdbc:h2:mem:ServicioClima;MODE=MySQL;IGNORECASE=TRUE;INIT=runscript from 'src/main/resources/BaseDeDatos.sql'";
    private static String user = "root";
    private static String pwds = "1234";
    private static Statement st;
    private static ServicioBD sBD;
    
    public ServicioBDTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        h2 = DriverManager.getConnection(url, user, pwds);
        sBD = new ServicioBD();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
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
    public void test01TextoAfechaTrue() throws ParseException {
        String texto = "Fri, 21 Apr 2017 05:00 PM ART";
        Date expResult = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US).parse(texto);
        Date result = sBD.textoAfecha(texto, true);
        assertEquals(expResult, result);
    }
    
    public void test02TextoAfechaFalse() throws ParseException {
        String texto = "21 Apr 2017";
        Date expResult = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(texto);
        Date result = sBD.textoAfecha(texto, false);
        assertEquals(expResult, result);
    }

    @Test
    public void test03FormatoFecha() throws ParseException {
        String texto = "21 Apr 2017";
        Date fecha = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(texto);
        String result = sBD.formatoFecha(fecha);
        String expResult = "2017-04-21 00:00";
        assertEquals(expResult, result);
    }

    @Test
    public void test04Registrar() throws SQLException {
        boolean result = false;
        String sql = "insert into Ubicacion (idUbicacion, ciudad, pais) values (1, 'Córdoba', 'Argentina');";
        try {
            st.executeUpdate(sql);
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("No se registró...");
        }
        assertTrue(result);
    }

    @Test
    public void test05Obtener() {
        ResultSet rs;
        boolean result = false;
        String sql = "select * from Ubicacion where idUbicacion = 1";
        try {
            rs = st.executeQuery(sql);
            result = true;
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("No se obtuvo la información.");
        }
        assertTrue(result);
    }

    @Test
    public void test06GetId() throws Exception {
        int id = 0;
        String sql = "select max(idUbicacion) as id from ubicacion;";
        ResultSet resultado = st.executeQuery(sql);
        if (resultado != null) {
            if (resultado.next()) {
                id = resultado.getInt("id");
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        assertEquals(1, id);
    }
    
    @Test
    public void test07CerrarConexion() {
        try {
            h2.close();
            assertTrue(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("No se pudo cerrar la conexión.");
        }
    }
    
}
