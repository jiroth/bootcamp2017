/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.servicios.SQLDataManipulation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ignacio Roth
 */
public class AtmosferaDAO extends SQLDataManipulation implements ClimaDAO {
    
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
        if (super.registrar(sql)) {
            try {
                id = super.getId("select max(idAtmosfera) as id from atmosfera;");
            } catch (SQLException ex) {
                Logger.getLogger(AtmosferaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public Atmosfera select(int i) {
        Atmosfera a = new Atmosfera();
        String sql = "select * from Atmosfera where idAtmosfera = " + i;
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
        super.sBD.cerrarConexion();
        return a;
    }
    
}
