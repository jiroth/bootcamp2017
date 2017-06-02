/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapter;

import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.transformer.Transformer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */

@Component
public class AdapterJsonWeatherPronostico implements ObjectYahooWeather {

    @Autowired
    ClienteYahooWeather proxyClima;

    @Override
    public Pronostico getPronostico(String ciudad, String pais) {
        ObjectMapper objectMapper = new ObjectMapper();
        Pronostico pronostico = null;
        try {
            JsonNode weatherJson = objectMapper.readTree(proxyClima.getJsonWeather(ciudad, pais));
            pronostico = Transformer.transformWeatherJsonToPronostico(weatherJson);
        } catch (Exception e) {
        }
        return pronostico;
    }

    public AdapterJsonWeatherPronostico(ClienteYahooWeather proxyClima) {
        this.proxyClima = proxyClima;
    }

    public AdapterJsonWeatherPronostico() {
    }
    
}
