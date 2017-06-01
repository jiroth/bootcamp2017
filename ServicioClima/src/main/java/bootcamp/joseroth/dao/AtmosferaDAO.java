/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.AtmosferaBuilder;
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
public class AtmosferaDAO extends BaseClimaDAO implements ClimaDAO {

    @Override
    public int insert(Object o) {
        int id = 0;
        Atmosfera a = (Atmosfera) o;
        String sql = "insert into Atmosfera (humedad, visibilidad) values (" + a.getHumedad() + ", " + a.getVisibilidad() + ");";
        try {
            super.registrarActualizar(sql);
            id = super.getId("select max(idAtmosfera) as id from atmosfera;");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtmosferaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return id;
        }
    }

    @Override
    public Object select(Object idAtmosfera) {
        Atmosfera a = null;
        String sql = "select * from Atmosfera where idAtmosfera = " + idAtmosfera;
        try {
            ResultSet resultado = super.obtener(sql);
            if (resultado != null) {
                if (resultado.next()) {
                    a = new AtmosferaBuilder()
                            .withIdAtmosfera(resultado.getInt("idAtmosfera"))
                            .withHumedad(resultado.getInt("humedad"))
                            .withVisibilidad(resultado.getDouble("visibilidad"))
                            .build();
                }
                if (!resultado.isClosed()) {
                    resultado.close();
                }
            }
            super.st = null;
            super.conexion.cerrarConexion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtmosferaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return a;
        }
    }

    @Override
    public void update(Object o) {
        Atmosfera a = (Atmosfera) o;
        String sql = "update atmosfera set humedad = " + a.getHumedad() + ", visibilidad = "
                + a.getVisibilidad() + " where idAtmosfera = " + a.getIdAtmosfera();
        try {
            super.registrarActualizar(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtmosferaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
