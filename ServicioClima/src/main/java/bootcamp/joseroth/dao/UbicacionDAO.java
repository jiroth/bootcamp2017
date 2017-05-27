/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.modelos.Ubicacion;
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
public class UbicacionDAO extends OperacionesClimaDAO implements ClimaDAO {

    @Override
    public int insert(Object o) {
        Ubicacion u = null;
        try {
            u = (Ubicacion)o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "insert into Ubicacion (ciudad, pais) values ('" + u.getCiudad() + "', '" + u.getPais() + "');";
        int id = 0;
        try {
            super.registrarActualizar(sql);
            id = super.getId("select max(idUbicacion) as id from ubicacion;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public Ubicacion select(Object ciudad) {
        Ubicacion u = new Ubicacion();
        String sql = "select * from Ubicacion where ciudad = '" + ciudad + "';";
        ResultSet resultado = super.obtener(sql);
        if (resultado != null) {
            try {
                if (resultado.next()) {
                    u.setIdUbicacion(resultado.getInt("idUbicacion"));
                    u.setCiudad(resultado.getString("ciudad"));
                    u.setPais(resultado.getString("pais"));
                }
                if (!resultado.isClosed()) {
                    resultado.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        super.st = null;
        super.conexion.cerrarConexion();
        return u;
    }
    
    @Override
    public void update(Object o) {
        Ubicacion u = null;
        try {
            u = (Ubicacion)o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "update ubicacion set pais = '" + u.getPais() + "' where ciudad = '" + u.getCiudad() + "';";
        super.registrarActualizar(sql);
    }
    
}
