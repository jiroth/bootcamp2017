/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.modelos.Ubicacion;
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
public class UbicacionDAOTest {
    private static Connection h2;
    private static String url = "jdbc:h2:mem:ServicioClima;MODE=MySQL;IGNORECASE=TRUE;INIT=runscript from 'src/main/resources/BaseDeDatos.sql'";
    private static String user = "root";
    private static String pwds = "1234";
    private static Statement st;
    
    public UbicacionDAOTest() {
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
    public void testSelect() throws SQLException {
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
    
}
