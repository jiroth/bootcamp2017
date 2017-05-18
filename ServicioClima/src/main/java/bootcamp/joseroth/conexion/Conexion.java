/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author José Ignacio Roth
 */
public class Conexion {

    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB = "ServicioClima";
    private final String USESSL = "?&useSSL=false";
    private final String USUARIO = "root";
    private final String PASSWORD = "1234";
    private Connection instance;
    
    
    public Conexion() {
    }

    public Connection getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            instance = DriverManager.getConnection(URL + DB + USESSL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
       
    public void closeConnection() {
        try {
            instance.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}