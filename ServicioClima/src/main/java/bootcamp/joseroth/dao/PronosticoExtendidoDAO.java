/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import bootcamp.joseroth.builders.PronosticoExtendidoBuilder;
import bootcamp.joseroth.modelos.PronosticoExtendido;
import bootcamp.joseroth.servicios.OperacionesClimaDAO;
import bootcamp.joseroth.servicios.Utils;
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
public class PronosticoExtendidoDAO extends OperacionesClimaDAO implements ClimaDAO {
    Utils utils = new Utils();

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
                + utils.formatoFecha(pe.getFecha()) + "', '" + pe.getDia() + "', '" + pe.getEstado() + "', " + pe.getMinima() + ", "
                + pe.getMaxima() + ", " + pe.getIdPronostico() + ");";
        if (super.registrar(sql)) {
            try {
                id = super.getId("select max(idPronosticoExtendido) as id from PronosticoExtendido;");
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
    public void update(int i) {
    }

    @Override
    public void delete(int i) {
    }

}
