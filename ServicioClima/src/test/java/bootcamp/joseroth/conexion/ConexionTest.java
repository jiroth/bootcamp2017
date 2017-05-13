/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class ConexionTest {
    private static Connection h2;
    private static String url = "jdbc:h2:mem:ServicioClima;MODE=MySQL;IGNORECASE=TRUE;INIT=runscript from 'src/main/resources/BaseDeDatos.sql'";
    private static String user = "root";
    private static String pwds = "1234";
    
    public ConexionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        h2.close();
    }

    @Test
    public void testConectar() throws SQLException {
        System.out.println("conectar");
        h2 = DriverManager.getConnection(url, user, pwds);
        assertNotNull(h2);
        assertFalse(h2.isClosed());
    }

    @Test
    public void testDesconectar() {
        System.out.println("desconectar");
        try {
            h2 = DriverManager.getConnection(url, user, pwds);
            h2.close();
            assertTrue(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
