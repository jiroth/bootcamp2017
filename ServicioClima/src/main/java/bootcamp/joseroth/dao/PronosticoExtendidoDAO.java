/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.PronosticoExtendidoBuilder;
import bootcamp.joseroth.modelos.PronosticoExtendido;
import bootcamp.joseroth.servicios.ServicioBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ignacio Roth
 */
public class PronosticoExtendidoDAO implements ClimaDAO {

    ServicioBD sBD = new ServicioBD();

    @Override
    public int insertar(Object o) {
        int id = 0;
        PronosticoExtendido pe = null;
        try {
            pe = (PronosticoExtendido) o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "insert into PronosticoExtendido (fecha, dia, estado, minima, maxima, idPronostico) values('"
                + sBD.formatoFecha(pe.getFecha()) + "', '" + pe.getDia() + "', '" + pe.getEstado() + "', " + pe.getMinima() + ", "
                + pe.getMaxima() + ", " + pe.getIdPronostico() + ");";
        if (sBD.registrar(sql)) {
            try {
                id = sBD.getId("select max(idPronosticoExtendido) as id from PronosticoExtendido;");
            } catch (SQLException ex) {
                Logger.getLogger(PronosticoExtendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public Object select(int i) {
        ArrayList<PronosticoExtendido> lista = new ArrayList<>();
        String sql = "select * from PronosticoExtendido where idPronostico = " + i;
        ResultSet rs = sBD.obtener(sql);
        if (rs != null) {
            try {
                while (rs.next()) {
                    PronosticoExtendido pe;
                    pe = new PronosticoExtendidoBuilder().withIdPronosticoExtendido(rs.getInt("idPronosticoExtendido"))
                            .withDia(rs.getString("dia")).withEstado(rs.getString("estado")).withMinima(rs.getInt("minima"))
                            .withMaxima(rs.getInt("maxima")).withIdPronostico(rs.getInt("idPronostico")).build();
                    pe.setFecha(rs.getDate("fecha"));
                    lista.add(pe);
                }
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PronosticoExtendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sBD.cerrarConexion();
        return lista;
    }

}
