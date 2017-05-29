/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.validaciones;

import bootcamp.joseroth.dao.ClimaDAO;
import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.Dia;
import bootcamp.joseroth.modelos.Ubicacion;
import bootcamp.joseroth.modelos.Viento;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public class Validacion {

    @Autowired
    @Qualifier("ubicacionDAO")
    private ClimaDAO<Ubicacion, String> ubicacionDAO;
    @Autowired
    @Qualifier("atmosferaDAO")
    private ClimaDAO<Atmosfera, Integer> atmosferaDAO;
    @Autowired
    @Qualifier("vientoDAO")
    private ClimaDAO<Viento, Integer> vientoDAO;
    @Autowired
    @Qualifier("pronosticoDAO")
    private ClimaDAO<Pronostico, Integer> pronosticoDAO;
    @Autowired
    @Qualifier("pronosticoExtendidoDAO")
    private ClimaDAO<ArrayList<Dia>, Integer> pronosticoExtendidoDAO;

    public boolean validarRegistroBaseDatos(Pronostico pronostico) {
        boolean b = false;
        try {
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
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
