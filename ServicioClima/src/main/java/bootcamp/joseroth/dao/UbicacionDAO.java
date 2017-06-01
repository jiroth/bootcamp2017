/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.UbicacionBuilder;
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
public class UbicacionDAO extends BaseClimaDAO implements ClimaDAO {

    @Override
    public int insert(Object o) {
        int id = 0;
        Ubicacion u = (Ubicacion) o;
        String sql = "insert into Ubicacion (ciudad, pais) values ('" + u.getCiudad() + "', '" + u.getPais() + "');";
        try {
            super.registrarActualizar(sql);
            id = super.getId("select max(idUbicacion) as id from ubicacion;");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return id;
        }
    }

    @Override
    public Ubicacion select(Object ciudad) {
        Ubicacion u = null;
        String sql = "select * from Ubicacion where ciudad = '" + ciudad + "';";
        try {
            ResultSet resultado = super.obtener(sql);
            if (resultado != null) {
                if (resultado.next()) {
                    u = new UbicacionBuilder()
                            .withIdUbicacion(resultado.getInt("idUbicacion"))
                            .withCiudad(resultado.getString("ciudad"))
                            .withPais(resultado.getString("pais"))
                            .build();
                }
                if (!resultado.isClosed()) {
                    resultado.close();
                }
            }
            super.st = null;
            super.conexion.cerrarConexion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return u;
        }
    }

    @Override
    public void update(Object o) {
        Ubicacion u = (Ubicacion) o;
        String sql = "update ubicacion set pais = '" + u.getPais() + "' where ciudad = '" + u.getCiudad() + "';";
        try {
            super.registrarActualizar(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
