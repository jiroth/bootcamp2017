/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

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
public class VientoDAO extends OperacionesClimaDAO implements ClimaDAO {

    @Override
    public int insert(Object o) {
        Viento v = null;
        try {
            v = (Viento)o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "insert into Viento (direccion, velocidad) values (" + v.getDireccion() + ", " + v.getVelocidad() + ");";
        int id = 0;
        try {
            super.registrarActualizar(sql);
            id = super.getId("select max(idViento) as id from viento;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public Object select(Object idViento) {
        Viento v = new Viento();
        String sql = "select * from Viento where idViento = " + idViento;
        ResultSet resultado = super.obtener(sql);
        if (resultado != null) {
            try {
                if (resultado.next()) {
                    v.setIdViento(resultado.getInt("idViento"));
                    v.setDireccion(resultado.getInt("direccion"));
                    v.setVelocidad(resultado.getInt("velocidad"));
                }
                if (!resultado.isClosed()) {
                    resultado.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        super.st = null;
        super.conexion.cerrarConexion();
        return v;
    }
    
    @Override
    public void update(Object o) {
        Viento v = null;
        try {
            v = (Viento)o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "update viento set direccion = " + v.getDireccion() + ", velocidad = " + v.getVelocidad() + " where idViento = " + v.getIdViento();
        super.registrarActualizar(sql);
    }

}
