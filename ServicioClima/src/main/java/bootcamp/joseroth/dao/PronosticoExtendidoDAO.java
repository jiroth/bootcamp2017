/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.DiaBuilder;
import bootcamp.joseroth.modelos.Dia;
import bootcamp.joseroth.transformer.Transformer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author José Ignacio Roth
 */
@Repository
public class PronosticoExtendidoDAO extends BaseClimaDAO implements ClimaDAO {

    @Override
    public int insert(Object o) {
        int id = 0;
        ArrayList<Dia> pronosticoExtendido = (ArrayList<Dia>) o;
        Dia dia = null;
        try {
            for (int i = 0; i < pronosticoExtendido.size(); i++) {
                dia = pronosticoExtendido.get(i);
                String sql = "insert into PronosticoExtendido (fecha, dia, estado, minima, maxima, idPronostico) values('"
                        + Transformer.transformDateToString(dia.getFecha()) + "', '" + dia.getDia() + "', '" + dia.getEstado() + "', " + dia.getMinima() + ", "
                        + dia.getMaxima() + ", " + dia.getIdPronostico() + ");";
                super.registrarActualizar(sql);
            }
            id = super.getId("select max(idPronosticoExtendido) as id from PronosticoExtendido;");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PronosticoExtendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return id;
        }
    }

    @Override
    public Object select(Object idPronostico) {
        ArrayList<Dia> pronosticoExtendido = new ArrayList<>();
        String sql = "select * from PronosticoExtendido where idPronostico = " + idPronostico;
        try {
            ResultSet rs = super.obtener(sql);
            if (rs != null) {
                while (rs.next()) {
                    Dia dia = new DiaBuilder()
                            .withIdPronosticoExtendido(rs.getInt("idPronosticoExtendido"))
                            .withFecha(rs.getDate("fecha"))
                            .withDia(rs.getString("dia"))
                            .withEstado(rs.getString("estado"))
                            .withMinima(rs.getInt("minima"))
                            .withMaxima(rs.getInt("maxima"))
                            .withIdPronostico(rs.getInt("idPronostico"))
                            .build();
                    pronosticoExtendido.add(dia);
                }
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
            super.st = null;
            super.conexion.cerrarConexion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PronosticoExtendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return pronosticoExtendido;
        }
    }

    @Override
    public void update(Object o) {
        ArrayList<Dia> pronosticoExtendido = (ArrayList<Dia>) o;
        Dia dia = null;
        try {
            for (int i = 0; i < pronosticoExtendido.size(); i++) {
                dia = pronosticoExtendido.get(i);
                String sql = "update pronosticoextendido set fecha = '" + Transformer.transformDateToString(dia.getFecha())
                        + "', dia = '" + dia.getDia() + "', estado = '" + dia.getEstado() + "', minima = " + dia.getMinima()
                        + ", maxima = " + dia.getMaxima() + ", idPronostico = " + dia.getIdPronostico()
                        + " where idPronosticoExtendido = " + dia.getIdDia();
                super.registrarActualizar(sql);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PronosticoExtendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
