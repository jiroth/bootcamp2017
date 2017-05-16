/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.servicios.ServicioBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ignacio Roth
 */
public class AtmosferaDAO implements ClimaDAO {
    ServicioBD sBD = new ServicioBD();

    @Override
    public int insertar(Object o) {
        int id = 0;
        Atmosfera a = null;
        try {
            a = (Atmosfera)o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "insert into Atmosfera (humedad, visibilidad) values (" + a.getHumedad() + ", " + a.getVisibilidad() + ");";
        if (sBD.registrar(sql)) {
            try {
                id = sBD.getId("select max(idAtmosfera) as id from atmosfera;");
            } catch (SQLException ex) {
                Logger.getLogger(AtmosferaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public Object select(int i) {
        Atmosfera a = new Atmosfera();
        String sql = "select * from Atmosfera where idAtmosfera = " + i;
        ResultSet resultado = sBD.obtener(sql);
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
        sBD.cerrarConexion();
        return a;
    }
    
}
