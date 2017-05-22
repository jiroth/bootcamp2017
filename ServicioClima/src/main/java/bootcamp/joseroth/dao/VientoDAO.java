/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.modelos.Viento;
import bootcamp.joseroth.servicios.OperacionesClimaDAO;
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
    public int insertar(Object o) {
        int id = 0;
        Viento v = null;
        try {
            v = (Viento)o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "insert into Viento (direccion, velocidad) values (" + v.getDireccion() + ", " + v.getVelocidad() + ");";
        if (super.registrar(sql)) {
            try {
                id = super.getId("select max(idViento) as id from viento;");
            } catch (SQLException ex) {
                Logger.getLogger(VientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public Object select(int i) {
        Viento v = new Viento();
        String sql = "select * from Viento where idViento = " + i;
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
    public void update(int i) {
    }

    @Override
    public void delete(int i) {
    }

}
