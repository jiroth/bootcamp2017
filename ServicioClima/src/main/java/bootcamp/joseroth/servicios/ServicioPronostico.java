/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

import bootcamp.joseroth.conexion.Conexion;
import bootcamp.joseroth.modelos.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author José Ignacio Roth
 */
public class ServicioPronostico {
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
            e.printStackTrace();
        }
        return fecha;
    }
    
    public String formatoFecha(Date d) {
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(d);
        return fecha;
    }
    

    private void abrirConexion() {
        try {
            st = Conexion.getInstance().createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cerrarConexion() {
        try {
            st = null;
            Conexion.delInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private boolean registrar(String sql) {
        boolean registrado = false;
        try {
            abrirConexion();
            st.executeUpdate(sql);
            registrado = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return registrado;
    }

    private ResultSet obtener(String sql) {
        ResultSet resultado = null;
        try {
            abrirConexion();
            resultado = st.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    private int getId(String sql) throws SQLException {
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
    

    public int registrarViento(Viento v) throws SQLException {
        int id = 0;
        String sql = "insert into Viento (direccion, velocidad) values (" + v.getDireccion() + ", " + v.getVelocidad() + ");";
        if (registrar(sql)) {
            id = getId("select max(idViento) as id from viento;");
        }
        return id;
    }

    public int registrarAtmosfera(Atmosfera a) throws SQLException {
        int id = 0;
        String sql = "insert into Atmosfera (humedad, visibilidad) values (" + a.getHumedad() + ", " + a.getVisibilidad() + ");";
        if (registrar(sql)) {
            id = getId("select max(idAtmosfera) as id from atmosfera;");
        }
        return id;
    }

    public int registrarUbicacion(Ubicacion u) throws SQLException {
        int id = 0;
        String sql = "insert into Ubicacion (ciudad, pais) values ('" + u.getCiudad() + "', '" + u.getPais() + "');";
        if (registrar(sql)) {
            id = getId("select max(idUbicacion) as id from ubicacion;");
        }
        return id;
    }

    public int registrarPronostico(Pronostico p) throws SQLException {
        int id = 0;
        String sql = "insert into pronostico(fecha, idUbicacion, temperatura, estado, idAtmosfera, idViento) "
                + "values('" + formatoFecha(p.getFecha()) + "', " + p.getUbicacion().getIdUbicacion() + ", " + p.getTemperatura() + ", '" 
                + p.getEstado() + "', " + p.getAtmosfera().getIdAtmosfera() + ", " + p.getViento().getIdViento() + ");";
        if (registrar(sql)) {
            id = getId("select max(idPronostico) as id from pronostico;");
        }
        return id;
    }

    public void registrarPronosticoExtendido(PronosticoExtendido pe) {
        String sql = "insert into PronosticoExtendido (fecha, dia, estado, minima, maxima, idPronostico) values('" 
                + formatoFecha(pe.getFecha()) + "', '" + pe.getDia() + "', '" + pe.getEstado() + "', " + pe.getMinima() + ", " 
                + pe.getMaxima() + ", " + pe.getIdPronostico() + ");";
        registrar(sql);
    }
    
    
    public Atmosfera getAtmosfera(int idAtmosfera) throws SQLException {
        Atmosfera atmosfera = null;
        String sql = "select * from Atmosfera where idAtmosfera = " + idAtmosfera;
        ResultSet resultado = obtener(sql);
        if (resultado != null) {
            if (resultado.next()) {
                Atmosfera a = new Atmosfera();
                a.setIdAtmosfera(resultado.getInt("idAtmosfera"));
                a.setHumedad(resultado.getInt("humedad"));
                a.setVisibilidad(resultado.getDouble("visibilidad"));
                atmosfera = a;
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        cerrarConexion();
        return atmosfera;
    }

    public Viento getViento(int idViento) throws SQLException {
        Viento viento = null;
        String sql = "select * from Viento where idViento = " + idViento;
        ResultSet resultado = obtener(sql);
        if (resultado != null) {
            if (resultado.next()) {
                Viento v = new Viento();
                v.setIdViento(resultado.getInt("idViento"));
                v.setDireccion(resultado.getInt("direccion"));
                v.setVelocidad(resultado.getInt("velocidad"));
                viento = v;
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        cerrarConexion();
        return viento;
    }

    public Ubicacion getUbicacion(int idUbicacion) throws SQLException {
        Ubicacion ubicacion = null;
        String sql = "select * from Ubicacion where idUbicacion = " + idUbicacion;
        ResultSet resultado = obtener(sql);
        if (resultado != null) {
            if (resultado.next()) {
                Ubicacion u = new Ubicacion();
                u.setIdUbicacion(resultado.getInt("idUbicacion"));
                u.setCiudad(resultado.getString("ciudad"));
                u.setPais(resultado.getString("pais"));
                ubicacion = u;
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        cerrarConexion();
        return ubicacion;
    }

    public Pronostico getPronostico(int idPronostico) throws SQLException {
        Pronostico pronostico = null;
        String sql = "select * from Pronostico where idPronostico = " + idPronostico;
        ResultSet resultado = obtener(sql);
        if (resultado != null) {
            if (resultado.next()) {
                Pronostico p = new Pronostico();
                p.setIdPronostico(resultado.getInt("idPronostico"));
                p.setFecha(resultado.getDate("fecha"));
                p.setUbicacion(new Ubicacion(resultado.getInt("idUbicacion")));
                p.setTemperatura(resultado.getInt("temperatura"));
                p.setEstado(resultado.getString("estado"));
                p.setAtmosfera(new Atmosfera(resultado.getInt("idAtmosfera")));
                p.setViento(new Viento(resultado.getInt("idViento")));
                pronostico = p;
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        cerrarConexion();
        return pronostico;
    }

    public ArrayList<PronosticoExtendido> getPronosticoExtendido(int idPronostico) throws SQLException {
        ArrayList<PronosticoExtendido> lista = new ArrayList<>();
        String sql = "select * from PronosticoExtendido where idPronostico = " + idPronostico;
        ResultSet resultado = obtener(sql);
        if (resultado != null) {
            while (resultado.next()) {
                PronosticoExtendido pe = new PronosticoExtendido();
                pe.setIdPronosticoExtendido(resultado.getInt("idPronosticoExtendido"));
                pe.setFecha(resultado.getDate("fecha"));
                pe.setDia(resultado.getString("dia"));
                pe.setEstado(resultado.getString("estado"));
                pe.setMinima(resultado.getInt("minima"));
                pe.setMaxima(resultado.getInt("maxima"));
                pe.setIdPronostico(resultado.getInt("idPronostico"));
                lista.add(pe);
            }
            if (!resultado.isClosed()) {
                resultado.close();
            }
        }
        cerrarConexion();
        return lista;
    }
    
}
