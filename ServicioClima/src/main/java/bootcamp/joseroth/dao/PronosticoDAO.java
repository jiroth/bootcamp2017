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
import bootcamp.joseroth.transformer.Transformer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author José Ignacio Roth
 */
@Repository
public class PronosticoDAO extends BaseClimaDAO implements ClimaDAO {

    @Override
    public int insert(Object o) {
        int id = 0;
        Pronostico p = (Pronostico) o;
        String sql = "insert into pronostico(fecha, idUbicacion, temperatura, estado, idAtmosfera, idViento) "
                + "values('" + Transformer.transformDateToString(p.getFecha()) + "', " + p.getUbicacion().getIdUbicacion() + ", "
                + p.getTemperatura() + ", '" + p.getEstado() + "', " + p.getAtmosfera().getIdAtmosfera() + ", "
                + p.getViento().getIdViento() + ");";
        try {
            super.registrarActualizar(sql);
            id = super.getId("select max(idPronostico) as id from pronostico;");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return id;
        }
    }

    @Override
    public Object select(Object idUbicacion) {
        Pronostico p = null;
        String sql = "select * from Pronostico where idUbicacion = " + idUbicacion;
        try {
            ResultSet rs = super.obtener(sql);
            if (rs != null) {
                if (rs.next()) {
                    p = new PronosticoBuilder()
                            .withIdPronostico(rs.getInt("idPronostico"))
                            .withFecha(rs.getDate("fecha"))
                            .withUbicacion(new Ubicacion(rs.getInt("idUbicacion")))
                            .withTemperatura(rs.getInt("temperatura"))
                            .withEstado(rs.getString("estado"))
                            .withAtmosfera(new Atmosfera(rs.getInt("idAtmosfera")))
                            .withViento(new Viento(rs.getInt("idViento")))
                            .build();
                }
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
            super.st = null;
            super.conexion.cerrarConexion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return p;
        }
    }

    @Override
    public void update(Object o) {
        Pronostico p = (Pronostico) o;
        String sql = "update pronostico set fecha = '" + Transformer.transformDateToString(p.getFecha()) + "', temperatura = "
                + p.getTemperatura() + ", estado = '" + p.getEstado() + "', idAtmosfera = " + p.getAtmosfera().getIdAtmosfera()
                + ", idViento = " + p.getViento().getIdViento() + " where idUbicacion = " + p.getUbicacion().getIdUbicacion();
        try {
            super.registrarActualizar(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
