/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

import bootcamp.joseroth.conexion.Conexion;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author José Ignacio Roth
 */
public class ServicioBD {
    
    public Statement abrirConexion() {
        Statement st = null;
        try {
            st = Conexion.getInstance().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return st;
    }
    
    public void cerrarConexion() {
        try {
            Conexion.delInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
