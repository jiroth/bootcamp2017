/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.modelos.Atmosfera;
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
public class AtmosferaDAO extends OperacionesClimaDAO implements ClimaDAO {
    
    @Override
    public int insert(Object o) {
        Atmosfera a = null;
        try {
            a = (Atmosfera)o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "insert into Atmosfera (humedad, visibilidad) values (" + a.getHumedad() + ", " + a.getVisibilidad() + ");";
        int id = 0;
        try {
            super.registrarActualizar(sql);
            id = super.getId("select max(idAtmosfera) as id from atmosfera;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public Object select(Object idAtmosfera) {
        Atmosfera a = new Atmosfera();
        String sql = "select * from Atmosfera where idAtmosfera = " + idAtmosfera;
        ResultSet resultado = super.obtener(sql);
        if (resultado != null) {
            try {
                if (resultado.next()) {
                    a.setIdAtmosfera(resultado.getInt("idAtmosfera"));
                    a.setHumedad(resultado.getInt("humedad"));
                    a.setVisibilidad(resultado.getDouble("visibilidad"));
                }
                if (!resultado.isClosed()) {
                    resultado.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AtmosferaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        super.st = null;
        super.conexion.cerrarConexion();
        return a;
    }

    @Override
    public void update(Object o) {
        Atmosfera a = null;
        try {
            a = (Atmosfera)o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "update atmosfera set humedad = " + a.getHumedad() + ", visibilidad = " + a.getVisibilidad() + " where idAtmosfera = " + a.getIdAtmosfera();
        super.registrarActualizar(sql);
    }
    
}
