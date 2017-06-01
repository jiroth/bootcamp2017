/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.controladores;

import bootcamp.joseroth.adapter.ObjectYahooWeather;
import bootcamp.joseroth.builders.UbicacionBuilder;
import bootcamp.joseroth.conexion.Conexion;
import bootcamp.joseroth.dao.ClimaDAO;
import bootcamp.joseroth.modelos.*;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author José Ignacio Roth
 */
@RestController()
public class ServicioClimaController {

    @Autowired
    private ObjectYahooWeather adapterJsonWeatherPronostico;

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

    @RequestMapping("/")
    public String bienvenida() {
        return "¡Bienvenido a Servicio Meteorológico Web!";
    }

    @RequestMapping(value = "/ciudad/{ciudad}", method = RequestMethod.GET)
    public ResponseEntity getPronostico(@PathVariable("ciudad") String ciudad) {
        Pronostico pronostico = null;
        Ubicacion ubicacion = ubicacionDAO.select(ciudad);
        if (ubicacion != null) {
            pronostico = getPronosticoBaseDatos(ciudad);
            return new ResponseEntity<>(pronostico, HttpStatus.OK);
        }
        return new ResponseEntity<>("El pronostico actualmente no está diaponible para la localidad especificada.", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity addPronostico(@RequestBody Pronostico pronostico) {
        registrarEnBaseDatos(pronostico);
        Pronostico p = getPronosticoBaseDatos(pronostico.getUbicacion().getCiudad());
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>("Se ha producido un error en el registro del pronostico.", HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = {"content-type=application/json"})
    public ResponseEntity putLocation(@RequestBody Pronostico pronostico) {
        actualizarEnBaseDatos(pronostico);
        Pronostico p = getPronosticoBaseDatos(pronostico.getUbicacion().getCiudad());
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>("Se ha producido un error en la actualizacion del pronostico.", HttpStatus.OK);
    }

    @RequestMapping(value = "/ciudad/{ciudad}/pais/{pais}", method = RequestMethod.GET)
    public ResponseEntity getYahooWeather(@PathVariable("ciudad") String ciudad, @PathVariable("pais") String pais) {
        ResponseEntity responseEntity;
        Pronostico pronostico = adapterJsonWeatherPronostico.getPronostico(ciudad, pais);
        if (pronostico != null) {
            if (comprobarConexionBaseDatos()) {
                if (comprobarExistenciaEnBaseDatos(pronostico.getUbicacion())) {
                    actualizarEnBaseDatos(pronostico);
                    pronostico = getPronosticoBaseDatos(ciudad);
                } else {
                    registrarEnBaseDatos(pronostico);
                    pronostico = getPronosticoBaseDatos(ciudad);
                }
            }
            responseEntity = new ResponseEntity<>(pronostico, HttpStatus.OK);
        } else {
            if (comprobarConexionBaseDatos()) {
                if (comprobarExistenciaEnBaseDatos(new UbicacionBuilder().withCiudad(ciudad).withPais(pais).build())) {
                    pronostico = getPronosticoBaseDatos(ciudad);
                    responseEntity = new ResponseEntity<>(pronostico, HttpStatus.OK);
                } else {
                    responseEntity = new ResponseEntity<>("El pronostico no esta disponible para la ubicación que desea. Intente nuevamente en unos minutos.", HttpStatus.NOT_FOUND);
                }
            } else {
                responseEntity = new ResponseEntity<>("El servicio está temporalmente caído. Intente nuevamente en unos minutos.", HttpStatus.NOT_FOUND);
            }
        }
        return responseEntity;
    }

    private boolean comprobarConexionBaseDatos() {
        try {
            conexion.abrirConexion();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    private boolean comprobarExistenciaEnBaseDatos(Ubicacion u) {
        try {
            Ubicacion ubicacion = ubicacionDAO.select(u.getCiudad());
            if (ubicacion != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private Pronostico getPronosticoBaseDatos(String ciudad) {
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

    private void actualizarEnBaseDatos(Pronostico pronostico) {
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

    private void registrarEnBaseDatos(Pronostico pronostico) {
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
