/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public abstract class OperacionesClimaDAO {
    
    @Autowired
    protected Conexion conexion;

    protected Statement st;

    protected void registrarActualizar(String sql) {
        try {
            conexion.abrirConexion();
            st = conexion.getInstance().createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st = null;
            conexion.cerrarConexion();
        }
    }

    protected ResultSet obtener(String sql) {
        ResultSet resultado = null;
        try {
            conexion.abrirConexion();
            st = conexion.getInstance().createStatement();
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
        conexion.cerrarConexion();
        return id;
    }
}