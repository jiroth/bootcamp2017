/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.recursos;

import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.Dia;
import bootcamp.joseroth.transformer.Transformer;
import bootcamp.joseroth.validaciones.Validacion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import bootcamp.joseroth.adapters.Ingles;
import bootcamp.joseroth.adapters.Espanol;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public class ProxyClima implements ClienteYahooWeather {

    @Autowired
    private ClienteYahooWeather clienteYahooWeather;
//    @Resource
//    private ClienteYahooWeather clienteYahooWeather;
    
    @Autowired
    private Validacion validacion;
    
    //ADAPTERS
    @Autowired
    @Qualifier("adapterDiaInglesEspanol")
    private Espanol adapterDiaInglesEspanol;
    @Autowired
    @Qualifier("diaInglesImpl")
    private Ingles diaInglesImpl;
    @Autowired
    @Qualifier("adapterEstadoClimaInglesEspanol")
    private Espanol adapterEstadoClimaInglesEspanol;
    @Autowired
    @Qualifier("estadoClimaInglesImpl")
    private Ingles estadoClimaInglesImpl;
    
    
    
    @Override
    public String getPronostico(String ciudad, String pais) {
        String consultaYQL = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + ciudad + ", " + pais + "\") and u=\"c\"";
        ObjectMapper objectMapper = new ObjectMapper();
        Pronostico pronostico = null;
        
        try {
            JsonNode weatherJson = objectMapper.readTree(clienteYahooWeather.getPronostico(consultaYQL, "json"));
            pronostico = Transformer.transformWeatherToPronostico(weatherJson);
        } catch (IOException | ParseException e) {
            //manejar error
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
        
        //ADAPTER DIAS EN INGLES A ESPEÑOL
        estadoClimaInglesImpl.set(pronostico.getEstado());
        adapterEstadoClimaInglesEspanol.set(estadoClimaInglesImpl);
        pronostico.setEstado(adapterEstadoClimaInglesEspanol.get());
        for(Dia dia : pronostico.getPronositicoExtendido()) {
            diaInglesImpl.set(dia.getDia());
            estadoClimaInglesImpl.set(dia.getEstado());
            adapterDiaInglesEspanol.set(diaInglesImpl);
            adapterEstadoClimaInglesEspanol.set(estadoClimaInglesImpl);
            dia.setEstado(adapterEstadoClimaInglesEspanol.get());
            dia.setDia(adapterDiaInglesEspanol.get());
        }
        
        
//        if(validacion.validarRegistroBaseDatos(pronostico)) {
//            
//        }
        

        return pronostico.toString();
    }
    
}
