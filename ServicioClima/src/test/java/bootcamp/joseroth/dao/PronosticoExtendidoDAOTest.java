/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.PronosticoExtendidoBuilder;
import bootcamp.joseroth.modelos.PronosticoExtendido;
import bootcamp.joseroth.servicios.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class PronosticoExtendidoDAOTest {
    private static Connection h2;
    private static String url = "jdbc:h2:mem:ServicioClima;MODE=MySQL;IGNORECASE=TRUE;INIT=runscript from 'src/main/resources/BaseDeDatos.sql'";
    private static String user = "root";
    private static String pwds = "1234";
    private static Statement st;
    
    public PronosticoExtendidoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        h2 = DriverManager.getConnection(url, user, pwds);
        Statement a = h2.createStatement();
        Statement v = h2.createStatement();
        Statement u = h2.createStatement();
        Statement p = h2.createStatement();
        try {
            a.executeUpdate("insert into Atmosfera (idAtmosfera, humedad, visibilidad) values (1, 20, 10);");
            v.executeUpdate("insert into Viento (idViento, direccion, velocidad) values (1, 170, 10);");
            u.executeUpdate("insert into Ubicacion (idUbicacion, ciudad, pais) values (1, 'Córdoba', 'Argentina');");
            p.executeUpdate("insert into Pronostico(idPronostico, fecha, idUbicacion, temperatura, estado, idAtmosfera, idViento) values(1, '2017-04-21', 1, 70, 'Nublado', 1, 1);");
            a.close();
            v.close();
            u.close();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
    public void testInsertar() {
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
    public void testSelect() throws SQLException {
        String sql = "select * from PronosticoExtendido";
        Utils utils = new Utils();
        PronosticoExtendido pe = new PronosticoExtendido();
        ResultSet rs = st.executeQuery(sql);;
        if (rs != null) {
            if (rs.next()) {
                pe = new PronosticoExtendidoBuilder().withIdPronosticoExtendido(rs.getInt("idPronosticoExtendido"))
                        .withFecha(rs.getDate("fecha")).withDia(rs.getString("dia")).withEstado(rs.getString("estado"))
                        .withMinima(rs.getInt("minima")).withMaxima(rs.getInt("maxima"))
                        .withIdPronostico(rs.getInt("idPronostico")).build();
            }
            if (!rs.isClosed()) {
                rs.close();
            }
        }
        assertEquals(1, pe.getIdPronosticoExtendido());
        assertEquals(utils.textoAfecha("21 Apr 2017", false), pe.getFecha());
        assertEquals("Fri", pe.getDia());
        assertEquals("Partly Cloudy", pe.getEstado());
        assertEquals(58, pe.getMinima());
        assertEquals(64, pe.getMaxima());
        assertEquals(1, pe.getIdPronostico());
    }
    
}
