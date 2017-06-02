/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.controladores;

import bootcamp.joseroth.conexion.Conexion;
import bootcamp.joseroth.dao.ClimaDAO;
import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.modelos.Dia;
import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.Ubicacion;
import bootcamp.joseroth.modelos.Viento;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author José Ignacio Roth
 */

@Controller
public class ServicioBaseController {
    
    @Autowired
    private Conexion conexion;

    @Autowired
    private ClimaDAO<Ubicacion, String> ubicacionDAO;
    @Autowired
    private ClimaDAO<Atmosfera, Integer> atmosferaDAO;
    @Autowired
    private ClimaDAO<Viento, Integer> vientoDAO;
    @Autowired
    private ClimaDAO<Pronostico, Integer> pronosticoDAO;
    @Autowired
    private ClimaDAO<ArrayList<Dia>, Integer> pronosticoExtendidoDAO;
    
    
    public boolean comprobarConexionBaseDatos() {
        try {
            conexion.abrirConexion();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean comprobarExistenciaEnBaseDatos(Ubicacion u) {
        try {
            Ubicacion ubicacion = ubicacionDAO.select(u.getCiudad());
            if (ubicacion != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public Pronostico getPronosticoBaseDatos(String ciudad) {
        Ubicacion ubicacion = ubicacionDAO.select(ciudad);
        Pronostico pronostico = pronosticoDAO.select(ubicacion.getIdUbicacion());
        Viento viento = vientoDAO.select(pronostico.getViento().getIdViento());
        Atmosfera atmosfera = atmosferaDAO.select(pronostico.getAtmosfera().getIdAtmosfera());
        ArrayList<Dia> pronosticoExtendido = pronosticoExtendidoDAO.select(pronostico.getIdPronostico());
        pronostico.setUbicacion(ubicacion);
        pronostico.setViento(viento);
        pronostico.setAtmosfera(atmosfera);
        pronostico.setPronositicoExtendido(pronosticoExtendido);
        return pronostico;
    }

    public void actualizarEnBaseDatos(Pronostico pronostico) {
        Ubicacion u = ubicacionDAO.select(pronostico.getUbicacion().getCiudad());
        Pronostico p = pronosticoDAO.select(u.getIdUbicacion());
        pronostico.setIdPronostico(p.getIdPronostico());
        pronostico.getAtmosfera().setIdAtmosfera(p.getAtmosfera().getIdAtmosfera());
        pronostico.getViento().setIdViento(p.getViento().getIdViento());
        pronostico.getUbicacion().setIdUbicacion(p.getUbicacion().getIdUbicacion());
        ArrayList<Dia> pe = pronosticoExtendidoDAO.select(pronostico.getIdPronostico());
        int i = 0;
        for (Dia d : pronostico.getPronositicoExtendido()) {
            d.setIdDia(pe.get(i).getIdDia());
            d.setIdPronostico(pronostico.getIdPronostico());
            i++;
        }
        ubicacionDAO.update(pronostico.getUbicacion());
        vientoDAO.update(pronostico.getViento());
        atmosferaDAO.update(pronostico.getAtmosfera());
        pronosticoDAO.update(pronostico);
        pronosticoExtendidoDAO.update(pronostico.getPronositicoExtendido());
    }

    public void registrarEnBaseDatos(Pronostico pronostico) {
        int idUbicacion = ubicacionDAO.insert(pronostico.getUbicacion());
        int idViento = vientoDAO.insert(pronostico.getViento());
        int idAtmosfera = atmosferaDAO.insert(pronostico.getAtmosfera());
        pronostico.getUbicacion().setIdUbicacion(idUbicacion);
        pronostico.getViento().setIdViento(idViento);
        pronostico.getAtmosfera().setIdAtmosfera(idAtmosfera);
        int idPronostico = pronosticoDAO.insert(pronostico);
        for (Dia dia : pronostico.getPronositicoExtendido()) {
            dia.setIdPronostico(idPronostico);
        }
        pronosticoExtendidoDAO.insert(pronostico.getPronositicoExtendido());
    }

}
