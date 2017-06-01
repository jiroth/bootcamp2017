/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.VientoBuilder;
import bootcamp.joseroth.modelos.Viento;
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
public class VientoDAO extends BaseClimaDAO implements ClimaDAO {

    @Override
    public int insert(Object o) {
        int id = 0;
        Viento v = (Viento) o;
        String sql = "insert into Viento (direccion, velocidad) values (" + v.getDireccion() + ", " + v.getVelocidad() + ");";
        try {
            super.registrarActualizar(sql);
            id = super.getId("select max(idViento) as id from viento;");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return id;
        }
    }

    @Override
    public Object select(Object idViento) {
        Viento v = null;
        String sql = "select * from Viento where idViento = " + idViento;
        try {
            ResultSet resultado = super.obtener(sql);
            if (resultado != null) {
                if (resultado.next()) {
                    v = new VientoBuilder()
                            .withIdViento(resultado.getInt("idViento"))
                            .withDireccion(resultado.getInt("direccion"))
                            .withVelocidad(resultado.getInt("velocidad"))
                            .build();
                }
                if (!resultado.isClosed()) {
                    resultado.close();
                }
            }
            super.st = null;
            super.conexion.cerrarConexion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return v;
        }
    }

    @Override
    public void update(Object o) {
        Viento v = (Viento) o;
        String sql = "update viento set direccion = " + v.getDireccion() + ", velocidad = "
                + v.getVelocidad() + " where idViento = " + v.getIdViento();
        try {
            super.registrarActualizar(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
