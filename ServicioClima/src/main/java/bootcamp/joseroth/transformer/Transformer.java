/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.transformer;

import bootcamp.joseroth.builders.AtmosferaBuilder;
import bootcamp.joseroth.builders.PronosticoBuilder;
import bootcamp.joseroth.builders.DiaBuilder;
import bootcamp.joseroth.builders.UbicacionBuilder;
import bootcamp.joseroth.builders.VientoBuilder;
import bootcamp.joseroth.modelos.*;
import com.fasterxml.jackson.databind.JsonNode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public class Transformer {

    private static SimpleDateFormat simpleDateFormat;

    public static Ubicacion transformLocationJsonToUbicacion(JsonNode location) {
        Ubicacion ubicacion = new UbicacionBuilder()
                .withCiudad(location.get("city").asText())
                .withPais(location.get("country").asText())
                .build();
        return ubicacion;
    }

    public static Viento transformWindJsonToViento(JsonNode wind) {
        Viento viento = new VientoBuilder()
                .withDireccion(wind.get("direction").asInt())
                .withVelocidad(wind.get("speed").asInt())
                .build();
        return viento;
    }

    public static Atmosfera transformAtmosphereJsonToAtmosfera(JsonNode atmosphere) {
        Atmosfera atmosfera = new AtmosferaBuilder()
                .withHumedad(atmosphere.get("humidity").asInt())
                .withVisibilidad(atmosphere.get("visibility").asDouble())
                .build();
        return atmosfera;
    }

    public static ArrayList<Dia> transformForecastJsonToPronosticoExtendido(JsonNode forecast) throws ParseException {
        ArrayList<Dia> pronosticoExtendido = new ArrayList<>();
        for (int i = 0; i < forecast.size(); i++) {
            Dia dia = new DiaBuilder()
                    .withFecha(Transformer.transformStringToDate(forecast.get(i).get("date").asText(), true))
                    .withDia(forecast.get(i).get("day").asText())
                    .withEstado(forecast.get(i).get("text").asText())
                    .withMinima(forecast.get(i).get("low").asInt())
                    .withMaxima(forecast.get(i).get("high").asInt())
                    .build();
            pronosticoExtendido.add(dia);
            dia = null;
        }
        return pronosticoExtendido;
    }

    public static Pronostico transformWeatherJsonToPronostico(JsonNode weatherJson) throws ParseException {
        JsonNode raizJson = weatherJson.get("query").get("results").get("channel");
        JsonNode location = raizJson.get("location");
        JsonNode wind = raizJson.get("wind");
        JsonNode atmosphere = raizJson.get("atmosphere");
        JsonNode condition = raizJson.get("item").get("condition");
        JsonNode forecast = raizJson.get("item").get("forecast");

        Pronostico pronostico = new PronosticoBuilder()
                .withFecha(Transformer.transformStringToDate(condition.get("date").asText(), false))
                .withUbicacion(Transformer.transformLocationJsonToUbicacion(location))
                .withTemperatura(condition.get("temp").asInt())
                .withEstado(condition.get("text").asText())
                .withViento(Transformer.transformWindJsonToViento(wind))
                .withAtmosfera(Transformer.transformAtmosphereJsonToAtmosfera(atmosphere))
                .withPronosticoExtendido(Transformer.transformForecastJsonToPronosticoExtendido(forecast))
                .build();
        return pronostico;
    }

    public static String transformDateToString(Date fecha) {
        String texto = "";
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        texto = simpleDateFormat.format(fecha);
        return texto;
    }

    public static Date transformStringToDate(String texto, boolean tipo) throws ParseException {
        Date fecha = null;
        if (tipo) {
            simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        } else {
            simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US);
        }
        fecha = simpleDateFormat.parse(texto);
        return fecha;
    }

}
