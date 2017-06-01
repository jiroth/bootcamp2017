/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    
    @Test 
    public void test01AbrirConexion() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        h2 = DriverManager.getConnection(url, user, pwds);
        assertNotNull(h2);
        assertFalse(h2.isClosed());
    }

    @Test
    public void test02CerrarConexion() {
        try {
            h2.close();
            assertTrue(true);
        } catch (SQLException e) {
            fail("No se pudo cerrar la conexión.");
        }
    }
}
