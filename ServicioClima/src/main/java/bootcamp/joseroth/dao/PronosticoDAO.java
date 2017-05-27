/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.PronosticoBuilder;
import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.Ubicacion;
import bootcamp.joseroth.modelos.Viento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author José Ignacio Roth
 */
@Repository
public class PronosticoDAO extends OperacionesClimaDAO implements ClimaDAO {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
    
    @Override
    public int insert(Object o) {
        Pronostico p = null;
        try {
            p = (Pronostico) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "insert into pronostico(fecha, idUbicacion, temperatura, estado, idAtmosfera, idViento) "
                + "values('" + sdf.format(p.getFecha()) + "', " + p.getUbicacion().getIdUbicacion() + ", " + p.getTemperatura() + ", '"
                + p.getEstado() + "', " + p.getAtmosfera().getIdAtmosfera() + ", " + p.getViento().getIdViento() + ");";
        int id = 0;
        try {
            super.registrarActualizar(sql);
            id = super.getId("select max(idPronostico) as id from pronostico;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public Object select(Object idUbicacion) {
        Pronostico p = new Pronostico();
        String sql = "select * from Pronostico where idUbicacion = " + idUbicacion;
        ResultSet rs = super.obtener(sql);
        if (rs != null) {
            try {
                if (rs.next()) {
                    p = new PronosticoBuilder().withIdPronostico(rs.getInt("idPronostico")).withFecha(rs.getDate("fecha"))
                            .withUbicacion(new Ubicacion(rs.getInt("idUbicacion"))).withTemperatura(rs.getInt("temperatura"))
                            .withEstado(rs.getString("estado")).withAtmosfera(new Atmosfera(rs.getInt("idAtmosfera")))
                            .withViento(new Viento(rs.getInt("idViento"))).build();
                }
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        super.st = null;
        super.conexion.cerrarConexion();
        return p;
    }
    
    @Override
    public void update(Object o) {
        Pronostico p = null;
        try {
            p = (Pronostico) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "update pronostico set fecha = '" + sdf.format(p.getFecha()) + "', temperatura = " 
                + p.getTemperatura() + ", estado = '" + p.getEstado() + "', idAtmosfera = " + p.getAtmosfera().getIdAtmosfera() 
                + ", idViento = " + p.getViento().getIdViento() + " where idUbicacion = " + p.getUbicacion().getIdUbicacion();
        super.registrarActualizar(sql);
    }

}
