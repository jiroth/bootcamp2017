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
import bootcamp.joseroth.servicios.ServicioBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ignacio Roth
 */
public class PronosticoDAO implements ClimaDAO {

    ServicioBD sBD = new ServicioBD();

    @Override
    public int insertar(Object o) {
        int id = 0;
        Pronostico p = null;
        try {
            p = (Pronostico) o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "insert into pronostico(fecha, idUbicacion, temperatura, estado, idAtmosfera, idViento) "
                + "values('" + sBD.formatoFecha(p.getFecha()) + "', " + p.getUbicacion().getIdUbicacion() + ", " + p.getTemperatura() + ", '"
                + p.getEstado() + "', " + p.getAtmosfera().getIdAtmosfera() + ", " + p.getViento().getIdViento() + ");";
        if (sBD.registrar(sql)) {
            try {
                id = sBD.getId("select max(idPronostico) as id from pronostico;");
            } catch (SQLException ex) {
                Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public Object select(int i) {
        Pronostico p = new Pronostico();
        String sql = "select * from Pronostico where idPronostico = " + i;
        ResultSet rs = sBD.obtener(sql);
        if (rs != null) {
            try {
                if (rs.next()) {
                    p = new PronosticoBuilder().withIdPronostico(rs.getInt("idPronostico"))
                            .withUbicacion(new Ubicacion(rs.getInt("idUbicacion"))).withTemperatura(rs.getInt("temperatura"))
                            .withEstado(rs.getString("estado")).withAtmosfera(new Atmosfera(rs.getInt("idAtmosfera")))
                            .withViento(new Viento(rs.getInt("idViento"))).build();
                    p.setFecha(rs.getDate("fecha"));
                }
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sBD.cerrarConexion();
        return p;
    }

}
