/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.modelos.Atmosfera;
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
public class AtmosferaDAOTest {
    private static Connection h2;
    private static String url = "jdbc:h2:mem:ServicioClima;MODE=MySQL;IGNORECASE=TRUE;INIT=runscript from 'src/main/resources/BaseDeDatos.sql'";
    private static String user = "root";
    private static String pwds = "1234";
    private static Statement st;
    
    public AtmosferaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        h2 = DriverManager.getConnection(url, user, pwds);
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
    public void testSelect() throws SQLException {
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
    
}
