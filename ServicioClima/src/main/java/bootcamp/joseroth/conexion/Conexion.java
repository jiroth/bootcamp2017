/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB = "ServicioClima";
    private static final String USESSL = "?&useSSL=false";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";
    private Connection conexion;

    public Connection getInstance() {
        return conexion;
    }

    public void abrirConexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL + DB + USESSL, USUARIO, PASSWORD);
    }

    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
}
