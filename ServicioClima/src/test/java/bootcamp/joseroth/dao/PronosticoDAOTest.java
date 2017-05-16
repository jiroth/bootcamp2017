/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.PronosticoBuilder;
import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.Ubicacion;
import bootcamp.joseroth.modelos.Viento;
import bootcamp.joseroth.servicios.ServicioBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class PronosticoDAOTest {
    private static Connection h2;
    private static String url = "jdbc:h2:mem:ServicioClima;MODE=MySQL;IGNORECASE=TRUE;INIT=runscript from 'src/main/resources/BaseDeDatos.sql'";
    private static String user = "root";
    private static String pwds = "1234";
    private static Statement st;
    private static ServicioBD sBD;
    
    public PronosticoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        h2 = DriverManager.getConnection(url, user, pwds);
        sBD = new ServicioBD();
        Statement a = h2.createStatement();
        Statement v = h2.createStatement();
        Statement u = h2.createStatement();
        try {
            a.executeUpdate("insert into Atmosfera (idAtmosfera, humedad, visibilidad) values (1, 20, 10);");
            v.executeUpdate("insert into Viento (idViento, direccion, velocidad) values (1, 170, 10);");
            u.executeUpdate("insert into Ubicacion (idUbicacion, ciudad, pais) values (1, 'Córdoba', 'Argentina');");
            a.close();
            v.close();
            u.close();
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
    public void testSelect() throws SQLException, ParseException {
        String sql = "select * from Pronostico"; 
        ResultSet rs = st.executeQuery(sql);
        Pronostico p = null;
        if (rs != null) {
            if (rs.next()) {
                p = new PronosticoBuilder().withIdPronostico(rs.getInt("idPronostico")).withUbicacion(new Ubicacion(rs.getInt("idUbicacion")))
                        .withTemperatura(rs.getInt("temperatura")).withEstado(rs.getString("estado"))
                        .withAtmosfera(new Atmosfera(rs.getInt("idAtmosfera"))).withViento(new Viento(rs.getInt("idViento"))).build();
                p.setFecha(rs.getDate("fecha"));
            }
            if (!rs.isClosed()) {
                rs.close();
            }
        }
        assertEquals(1, p.getIdPronostico());
        assertEquals(sBD.textoAfecha("21 Apr 2017", false), p.getFecha());
        assertEquals(1, p.getUbicacion().getIdUbicacion());
        assertEquals(70, p.getTemperatura());
        assertEquals("Nublado", p.getEstado());
        assertEquals(1, p.getAtmosfera().getIdAtmosfera());
        assertEquals(1, p.getViento().getIdViento());
    }
    
}
