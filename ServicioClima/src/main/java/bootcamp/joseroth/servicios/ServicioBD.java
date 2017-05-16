/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

import bootcamp.joseroth.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author José Ignacio Roth
 */
public class ServicioBD {
    private static Statement st;
    
    public Date textoAfecha(String s, boolean b) {
        Date fecha = new Date();
        SimpleDateFormat formatter;
        if(b) {
            formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US);
        } else {
            formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        }
        try {
            fecha = formatter.parse(s);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return fecha;
    }
    
    public String formatoFecha(Date d) {
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(d);
        return fecha;
    }
    

    private void abrirConexion() {
        try {
            st = Conexion.getInstance().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cerrarConexion() {
        try {
            st = null;
            Conexion.delInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public boolean registrar(String sql) {
        boolean registrado = false;
        try {
            abrirConexion();
            st.executeUpdate(sql);
            registrado = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        return registrado;
    }

    public ResultSet obtener(String sql) {
        ResultSet resultado = null;
        try {
            abrirConexion();
            resultado = st.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public int getId(String sql) throws SQLException {
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
        cerrarConexion();
        return id;
    }
    
}
