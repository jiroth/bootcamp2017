/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.PronosticoExtendidoBuilder;
import bootcamp.joseroth.modelos.PronosticoExtendido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author José Ignacio Roth
 */
@Repository
public class PronosticoExtendidoDAO extends OperacionesClimaDAO implements ClimaDAO {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Override
    public int insert(Object o) {
        ArrayList<PronosticoExtendido> lpe = null;
        PronosticoExtendido pe = null;
        try {
            lpe = (ArrayList<PronosticoExtendido>) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = 0;
        for (int i = 0; i < lpe.size(); i++) {
            pe = lpe.get(i);
            String sql = "insert into PronosticoExtendido (fecha, dia, estado, minima, maxima, idPronostico) values('"
                    + sdf.format(pe.getFecha()) + "', '" + pe.getDia() + "', '" + pe.getEstado() + "', " + pe.getMinima() + ", "
                    + pe.getMaxima() + ", " + pe.getIdPronostico() + ");";
            super.registrarActualizar(sql);
        }
        try {
            id = super.getId("select max(idPronosticoExtendido) as id from PronosticoExtendido;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public Object select(Object idPronostico) {
        ArrayList<PronosticoExtendido> lista = new ArrayList<>();
        String sql = "select * from PronosticoExtendido where idPronostico = " + idPronostico;
        ResultSet rs = super.obtener(sql);
        if (rs != null) {
            try {
                while (rs.next()) {
                    PronosticoExtendido pe;
                    pe = new PronosticoExtendidoBuilder().withIdPronosticoExtendido(rs.getInt("idPronosticoExtendido"))
                            .withFecha(rs.getDate("fecha")).withDia(rs.getString("dia")).withEstado(rs.getString("estado"))
                            .withMinima(rs.getInt("minima")).withMaxima(rs.getInt("maxima"))
                            .withIdPronostico(rs.getInt("idPronostico")).build();
                    lista.add(pe);
                }
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PronosticoExtendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        super.st = null;
        super.conexion.cerrarConexion();
        return lista;
    }

    @Override
    public void update(Object o) {
        ArrayList<PronosticoExtendido> lpe = null;
        PronosticoExtendido pe = null;
        try {
            lpe = (ArrayList<PronosticoExtendido>) o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < lpe.size(); i++) {
            pe = lpe.get(i);
            String sql = "update pronosticoextendido set fecha = '" + sdf.format(pe.getFecha())
                    + "', dia = '" + pe.getDia() + "', estado = '" + pe.getEstado() + "', minima = " + pe.getMinima()
                    + ", maxima = " + pe.getMaxima() + ", idPronostico = " + pe.getIdPronostico()
                    + " where idPronosticoExtendido = " + pe.getIdPronosticoExtendido();
            super.registrarActualizar(sql);
        }
    }

}
