/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author José Ignacio Roth
 */
public class SQLDataManipulation {
    protected ServicioBD sBD = new ServicioBD();
    protected Statement st;
    
    protected boolean registrar(String sql) {
        boolean registrado = false;
        try {
            st = sBD.abrirConexion();
            st.executeUpdate(sql);
            registrado = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            st = null;
            sBD.cerrarConexion();
        }
        return registrado;
    }

    protected ResultSet obtener(String sql) {
        ResultSet resultado = null;
        try {
            st = sBD.abrirConexion();
            resultado = st.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    protected int getId(String sql) throws SQLException {
        int id = 0;
        ResultSet resultado = obtener(sql);
        if (resultado != null) {
            if (resultado.next()) {
                id = resultado.getInt("id");
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        st = null;
        sBD.cerrarConexion();
        return id;
    }
}
