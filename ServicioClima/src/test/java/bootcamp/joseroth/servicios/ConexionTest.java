/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

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
    
    private static Conexion conexion;
    private static Connection mySQL;
    private static Connection h2;
    
    public ConexionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, SQLException {
        conexion = Conexion.getInstance();
        
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:mem:ServicioClima;MODE=MySQL;IGNORECASE=TRUE;INIT=runscript from 'src/main/resources/BaseDeDatos.sql'";
//        String user = "root";
//        String pwds = "1234";
        h2 = DriverManager.getConnection(url);//, user, pwds
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        Conexion.delInstance();
        mySQL.close();
        h2.close();
    }

    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Conexion expResult = Conexion.getInstance();
        assertNotNull(expResult);
    }

    @Test
    public void testDelInstance() {
        System.out.println("delInstance");
        try {
            Conexion.delInstance();
            assertTrue(true);
        } catch (Exception e) {
            fail("Fallo en borrar la instancia");
        }       
    }

    @Test
    public void testGetConn() throws SQLException {
        System.out.println("getConn");
        mySQL = conexion.getConn();
        assertNotNull(mySQL);
        assertNotNull(h2);
        //assertEquals(h2.getMetaData(), mySQL.getMetaData());
    }
    
}
