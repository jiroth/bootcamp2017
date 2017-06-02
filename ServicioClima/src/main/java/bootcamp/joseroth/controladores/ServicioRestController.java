/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.controladores;

import bootcamp.joseroth.adapter.ObjectYahooWeather;
import bootcamp.joseroth.builders.UbicacionBuilder;
import bootcamp.joseroth.modelos.*;
import java.text.ParseException;
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
public class ServicioRestController {

    @Autowired
    private ObjectYahooWeather adapterJsonWeatherPronostico;

    @Autowired
    private ServicioBaseController servicioBaseController;

    @RequestMapping("/")
    public ResponseEntity index() {
        return new ResponseEntity<>("Servicio Clima", HttpStatus.OK);
    }

    @RequestMapping(value = "/ciudad/{ciudad}", method = RequestMethod.GET)
    public ResponseEntity getPronostico(@PathVariable("ciudad") String ciudad) {
        Pronostico pronostico = null;
        if (servicioBaseController.comprobarExistenciaEnBaseDatos(new UbicacionBuilder().withCiudad(ciudad).build())) {
            pronostico = servicioBaseController.getPronosticoBaseDatos(ciudad);
            return new ResponseEntity<>(pronostico, HttpStatus.OK);
        }
        return new ResponseEntity<>("El pronostico no esta disponible para la ubicación que desea.", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity addPronostico(@RequestBody Pronostico pronostico) {
        Pronostico p = null;
        if (!servicioBaseController.comprobarExistenciaEnBaseDatos(pronostico.getUbicacion())) {
            servicioBaseController.registrarEnBaseDatos(pronostico);
            p = servicioBaseController.getPronosticoBaseDatos(pronostico.getUbicacion().getCiudad());
        }
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>("Se ha producido un error en el registro del pronostico.", HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = {"content-type=application/json"})
    public ResponseEntity updatePronostico(@RequestBody Pronostico pronostico) {
        Pronostico p = null;
        if (servicioBaseController.comprobarExistenciaEnBaseDatos(pronostico.getUbicacion())) {
            servicioBaseController.actualizarEnBaseDatos(pronostico);
            p = servicioBaseController.getPronosticoBaseDatos(pronostico.getUbicacion().getCiudad());
        }
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>("Se ha producido un error en la actualizacion del pronostico.", HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/ciudad/{ciudad}/pais/{pais}", method = RequestMethod.GET)
    public ResponseEntity getYahooWeather(@PathVariable("ciudad") String ciudad, @PathVariable("pais") String pais) throws ParseException {
        ResponseEntity responseEntity;
        Pronostico pronostico = adapterJsonWeatherPronostico.getPronostico(ciudad, pais);
        if (pronostico != null) {
            if (servicioBaseController.comprobarConexionBaseDatos()) {
                if (servicioBaseController.comprobarExistenciaEnBaseDatos(pronostico.getUbicacion())) {
                    servicioBaseController.actualizarEnBaseDatos(pronostico);
                } else {
                    servicioBaseController.registrarEnBaseDatos(pronostico);
                }
            }
            responseEntity = new ResponseEntity<>(pronostico, HttpStatus.OK);
        } else {
            if (servicioBaseController.comprobarConexionBaseDatos()) {
                if (servicioBaseController.comprobarExistenciaEnBaseDatos(new UbicacionBuilder().withCiudad(ciudad).withPais(pais).build())) {
                    pronostico = servicioBaseController.getPronosticoBaseDatos(ciudad);
                    responseEntity = new ResponseEntity<>(pronostico, HttpStatus.OK);
                } else {
                    responseEntity = new ResponseEntity<>("El pronostico no esta disponible para la ubicación que desea.", HttpStatus.NOT_FOUND);
                }
            } else {
                responseEntity = new ResponseEntity<>("El servicio está temporalmente caído. Intente nuevamente en unos minutos.", HttpStatus.NOT_FOUND);
            }
        }
        return responseEntity;
    }

    public ServicioRestController(ObjectYahooWeather adapterJsonWeatherPronostico, ServicioBaseController servicioBaseController) {
        this.adapterJsonWeatherPronostico = adapterJsonWeatherPronostico;
        this.servicioBaseController = servicioBaseController;
    }

    public ServicioRestController() {
    }

}
