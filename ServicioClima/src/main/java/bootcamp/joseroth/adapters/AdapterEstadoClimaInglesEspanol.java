/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public class AdapterEstadoClimaInglesEspanol implements Espanol {
    
    @Autowired
    private Ingles ingles;
    
    @Override
    public void set(Ingles ingles) {
        this.ingles = ingles;
    }

    @Override
    public String get() {
        String estado = "";
        switch(ingles.get()) {
            case "Tornado": estado = "Tornado"; break;
            case "Tropical Storm": estado = "Tormenta tropical"; break;
            case "Hurricane": estado = "Huracán"; break;
            case "Severe Thunderstorms": estado = "Tormentas eléctricas severas"; break;
            case "Thunderstorms": estado = "Tormentas eléctricas"; break;
            case "Mixed Rain and Snow": estado = "Lluvia y nieve"; break;
            case "Mixed Rain and Sleet": estado = "Lluvia y aguanieve"; break;
            case "Mixed Snow and Sleet": estado = "Nieve y aguanieve"; break;
            case "Freezing Drizzle": estado = "Llovizna helada"; break;
            case "Drizzle": estado = "Llovizna"; break;
            case "Freezing Rain": estado = "Lluvia helada"; break;
            case "Showers": estado = "Lluvia"; break;
            case "Snow Flurries": estado = "Copos de nieve"; break;
            case "Light Snow Showers": estado = "Nevada liviana"; break;
            case "Blowing Snow": estado = "Viento y nieve"; break;
            case "Snow": estado = "Nieve"; break;
            case "Hail": estado = "Granizo"; break;
            case "Sleet": estado = "Aguanieve"; break;
            case "Dust": estado = "Viento y tierra"; break;
            case "Foggy": estado = "Cielo cubierto"; break;
            case "Haze": estado = "Neblina"; break;
            case "Smoky": estado = "Nublado"; break;
            case "Blustery": estado = "Tormentoso"; break;
            case "Windy": estado = "Ventoso"; break;
            case "Cold": estado = "Frío"; break;
            case "Cloudy": estado = "Nublado"; break;
            case "Mostly Cloudy": estado = "Mayormente nublado"; break;
            case "Partly Cloudy": estado = "Parcialmente nublado"; break;
            case "Clear": estado = "Despejado"; break;
            case "Sunny": estado = "Soleado"; break;
            case "Mostly Clear": estado = "Mayormente despejado"; break;
            case "Mostly Sunny": estado = "Mayormente soleado"; break;
            case "Mixed Rain and Hail": estado = "Lluvia y granizo"; break;
            case "Hot": estado = "Caluroso"; break;
            case "Isolated Thunderstorms": estado = "Tormentas aisladas"; break;
            case "Scattered Thunderstorms": estado = "Tormentas eléctricas aisladas"; break;
            case "Scattered Showers": estado = "Lluvias aisladas"; break;
            case "Heavy Snow": estado = "Fuertes nevadas"; break;
            case "Scattered Snow Showers": estado = "Lluvias de nieve dispersas"; break;
            case "Thundershowers": estado = "Tormentas eléctricas"; break;
            case "Snow Showers": estado = "Nevadas"; break;
            case "Isolated Shundershowers": estado = "Lluvias eléctricas aisladas"; break;
            default: estado = "No disponible"; break;
        }
        return estado;
    }
    
}
