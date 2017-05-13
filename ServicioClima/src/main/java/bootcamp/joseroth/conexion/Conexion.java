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

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB = "ServicioClima";
    private static final String USESSL = "?&useSSL=false";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";
    private static Connection instance = null;
    
    private Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            instance = DriverManager.getConnection(URL + DB + USESSL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getInstance() {
        if (instance == null) {
            new Conexion();
        }
        return instance;
    }
    
    private static void closeConnection() {
        try {
            instance.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void delInstance() {
        closeConnection();
        instance = null;
    }
}