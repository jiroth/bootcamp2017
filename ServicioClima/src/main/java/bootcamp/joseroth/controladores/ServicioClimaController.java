/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.controladores;

import bootcamp.joseroth.dao.ClimaDAO;
import bootcamp.joseroth.recursos.ClienteYahooWeather;
import bootcamp.joseroth.modelos.*;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    
//    @Autowired
//    @Qualifier("clienteYahooWeather")
    @Resource
    ClienteYahooWeather clienteYahooWeather;
    
    @Autowired
    @Qualifier("ubicacionDAO")
    private ClimaDAO<Ubicacion, String> ubicacionDAO;
    
    @RequestMapping("/")
    public String bienvenida () {
        return "¡Bienvenido a Servicio Meteorológico Web!";
    }
    
    //GET
    @RequestMapping(value = "/ciudad/{ciudad}", method=RequestMethod.GET)
    public ResponseEntity<Ubicacion> getPronostico (@PathVariable("ciudad") String ciudad) {
        return new ResponseEntity<Ubicacion>(ubicacionDAO.select(ciudad), HttpStatus.OK);
    }
    
    //POST
    @RequestMapping(value = "/save", method=RequestMethod.POST, headers = {"content-type=application/json"})
    public int addPronostico(@RequestBody Ubicacion ubicacion) {
        return ubicacionDAO.insert(ubicacion);
    }
    
    //PUT
    @RequestMapping(value="/update", method = RequestMethod.PUT,  headers = {"content-type=application/json"})
    public ResponseEntity<Ubicacion> putLocation(@RequestBody Ubicacion ubicacion){
        ubicacionDAO.update(ubicacion);
        return new ResponseEntity<Ubicacion>(ubicacionDAO.select(ubicacion.getCiudad()), HttpStatus.OK);
    }
    
    //TRAE EL JSON DE YAHOO
    @RequestMapping(value = "/ciudad/{ciudad}/pais/{pais}", method = RequestMethod.GET)
    public String get(@PathVariable("ciudad") String ciudad, @PathVariable("pais") String pais) {
        String consultaYQL = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + ciudad + ", " + pais + "\") and u=\"c\"";
        return clienteYahooWeather.getPronostico(consultaYQL, "json");
    }

}
