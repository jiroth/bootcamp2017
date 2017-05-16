/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.modelos.Ubicacion;
import bootcamp.joseroth.servicios.ServicioBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ignacio Roth
 */
public class UbicacionDAO implements ClimaDAO {
    ServicioBD sBD = new ServicioBD();

    @Override
    public int insertar(Object o) {
        int id = 0;
        Ubicacion u = null;
        try {
            u = (Ubicacion)o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "insert into Ubicacion (ciudad, pais) values ('" + u.getCiudad() + "', '" + u.getPais() + "');";
        if (sBD.registrar(sql)) {
            try {
                id = sBD.getId("select max(idUbicacion) as id from ubicacion;");
            } catch (SQLException ex) {
                Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public Object select(int i) {
        Ubicacion u = new Ubicacion();
        String sql = "select * from Ubicacion where idUbicacion = " + i;
        ResultSet resultado = sBD.obtener(sql);
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
        sBD.cerrarConexion();
        return u;
    }
    
}
