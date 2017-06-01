/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.transformer;

import bootcamp.joseroth.modelos.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author José Ignacio Roth
 */
public class TransformerTest {
    
    private static JsonNode weatherJson;
    private static JsonNode location;
    private static JsonNode wind;
    private static JsonNode atmosphere;
    private static JsonNode forecast;
    private static JsonNode condition;
    private static SimpleDateFormat simpleDateFormat;
    
    public TransformerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        weatherJson = objectMapper.readTree("{\"query\":{\"count\":1,\"created\":\"2017-05-27T20:45:14Z\",\"lang\":\"es\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Nome, AK, US\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"description\":\"Yahoo! Weather for Nome, AK, US\",\"language\":\"en-us\",\"lastBuildDate\":\"Sat, 27 May 2017 12:45 PM AKDT\",\"ttl\":\"60\","
                + "\"location\":{\"city\":\"Nome\",\"country\":\"United States\",\"region\":\" AK\"},"
                + "\"wind\":{\"chill\":\"30\",\"direction\":\"158\",\"speed\":\"14\"},"
                + "\"atmosphere\":{\"humidity\":\"85\",\"pressure\":\"1010.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"0:55 am\",\"sunset\":\"5:4 am\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Nome, AK, US at 12:00 PM AKDT\",\"lat\":\"64.499474\",\"long\":\"-165.405792\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"pubDate\":\"Sat, 27 May 2017 12:00 PM AKDT\","
                + "\"condition\":{\"code\":\"27\",\"date\":\"Sat, 27 May 2017 12:00 PM AKDT\",\"temp\":\"38\",\"text\":\"Mostly Cloudy\"},"
                + "\"forecast\":["
                + "{\"code\":\"28\",\"date\":\"27 May 2017\",\"day\":\"Sat\",\"high\":\"40\",\"low\":\"36\",\"text\":\"Mostly Cloudy\"},"
                + "{\"code\":\"30\",\"date\":\"28 May 2017\",\"day\":\"Sun\",\"high\":\"46\",\"low\":\"31\",\"text\":\"Partly Cloudy\"},"
                + "{\"code\":\"32\",\"date\":\"29 May 2017\",\"day\":\"Mon\",\"high\":\"51\",\"low\":\"37\",\"text\":\"Sunny\"},"
                + "{\"code\":\"32\",\"date\":\"30 May 2017\",\"day\":\"Tue\",\"high\":\"50\",\"low\":\"37\",\"text\":\"Sunny\"},"
                + "{\"code\":\"32\",\"date\":\"31 May 2017\",\"day\":\"Wed\",\"high\":\"48\",\"low\":\"33\",\"text\":\"Sunny\"},"
                + "{\"code\":\"32\",\"date\":\"01 Jun 2017\",\"day\":\"Thu\",\"high\":\"47\",\"low\":\"34\",\"text\":\"Sunny\"},"
                + "{\"code\":\"32\",\"date\":\"02 Jun 2017\",\"day\":\"Fri\",\"high\":\"50\",\"low\":\"35\",\"text\":\"Sunny\"},"
                + "{\"code\":\"32\",\"date\":\"03 Jun 2017\",\"day\":\"Sat\",\"high\":\"47\",\"low\":\"38\",\"text\":\"Sunny\"},"
                + "{\"code\":\"30\",\"date\":\"04 Jun 2017\",\"day\":\"Sun\",\"high\":\"52\",\"low\":\"43\",\"text\":\"Partly Cloudy\"},"
                + "{\"code\":\"30\",\"date\":\"05 Jun 2017\",\"day\":\"Mon\",\"high\":\"46\",\"low\":\"39\",\"text\":\"Partly Cloudy\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/27.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Mostly Cloudy\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Sat - Mostly Cloudy. High: 40Low: 36\\n<BR /> Sun - Partly Cloudy. High: 46Low: 31\\n<BR /> Mon - Sunny. High: 51Low: 37\\n<BR /> Tue - Sunny. High: 50Low: 37\\n<BR /> Wed - Sunny. High: 48Low: 33\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}");
        JsonNode raizJson = weatherJson.get("query").get("results").get("channel");
        location = raizJson.get("location");
        wind = raizJson.get("wind");
        atmosphere = raizJson.get("atmosphere");
        condition = raizJson.get("item").get("condition");
        forecast = raizJson.get("item").get("forecast");
    }
    
    @Test
    public void testTransformLocationJsonToUbicacion() {
        Ubicacion u = Transformer.transformLocationJsonToUbicacion(location);
        assertEquals("Nome", u.getCiudad());
        assertEquals("United States", u.getPais());
    }
    
    @Test
    public void testTransformWindJsonToViento() {
        Viento v = Transformer.transformWindJsonToViento(wind);
        assertEquals(158, v.getDireccion());
        assertEquals(14, v.getVelocidad());
    }
    
    @Test
    public void testTransformAtmosphereJsonToAtmosfera() {
        Atmosfera a = Transformer.transformAtmosphereJsonToAtmosfera(atmosphere);
        assertEquals(85, a.getHumedad());
        assertEquals(16.1, a.getVisibilidad(), 0.0);
    }
    
    @Test
    public void testTransformForecastJsonToPronosticoExtendido() throws ParseException {
        ArrayList<Dia> pronosticoExtendido = Transformer.transformForecastJsonToPronosticoExtendido(forecast);
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("27 May 2017"), pronosticoExtendido.get(0).getFecha());
        assertEquals("Sat", pronosticoExtendido.get(0).getDia());
        assertEquals(40, pronosticoExtendido.get(0).getMaxima());
        assertEquals(36, pronosticoExtendido.get(0).getMinima());
        assertEquals("Mostly Cloudy", pronosticoExtendido.get(0).getEstado());
        
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("28 May 2017"), pronosticoExtendido.get(1).getFecha());
        assertEquals("Sun", pronosticoExtendido.get(1).getDia());
        assertEquals(46, pronosticoExtendido.get(1).getMaxima());
        assertEquals(31, pronosticoExtendido.get(1).getMinima());
        assertEquals("Partly Cloudy", pronosticoExtendido.get(1).getEstado());

        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("29 May 2017"), pronosticoExtendido.get(2).getFecha());
        assertEquals("Mon", pronosticoExtendido.get(2).getDia());
        assertEquals(51, pronosticoExtendido.get(2).getMaxima());
        assertEquals(37, pronosticoExtendido.get(2).getMinima());
        assertEquals("Sunny", pronosticoExtendido.get(2).getEstado());

        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("30 May 2017"), pronosticoExtendido.get(3).getFecha());
        assertEquals("Tue", pronosticoExtendido.get(3).getDia());
        assertEquals(50, pronosticoExtendido.get(3).getMaxima());
        assertEquals(37, pronosticoExtendido.get(3).getMinima());
        assertEquals("Sunny", pronosticoExtendido.get(3).getEstado());
        
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("31 May 2017"), pronosticoExtendido.get(4).getFecha());
        assertEquals("Wed", pronosticoExtendido.get(4).getDia());
        assertEquals(48, pronosticoExtendido.get(4).getMaxima());
        assertEquals(33, pronosticoExtendido.get(4).getMinima());
        assertEquals("Sunny", pronosticoExtendido.get(4).getEstado());
        
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("01 Jun 2017"), pronosticoExtendido.get(5).getFecha());
        assertEquals("Thu", pronosticoExtendido.get(5).getDia());
        assertEquals(47, pronosticoExtendido.get(5).getMaxima());
        assertEquals(34, pronosticoExtendido.get(5).getMinima());
        assertEquals("Sunny", pronosticoExtendido.get(5).getEstado());
        
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("02 Jun 2017"), pronosticoExtendido.get(6).getFecha());
        assertEquals("Fri", pronosticoExtendido.get(6).getDia());
        assertEquals(50, pronosticoExtendido.get(6).getMaxima());
        assertEquals(35, pronosticoExtendido.get(6).getMinima());
        assertEquals("Sunny", pronosticoExtendido.get(6).getEstado());

        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("03 Jun 2017"), pronosticoExtendido.get(7).getFecha());
        assertEquals("Sat", pronosticoExtendido.get(7).getDia());
        assertEquals(47, pronosticoExtendido.get(7).getMaxima());
        assertEquals(38, pronosticoExtendido.get(7).getMinima());
        assertEquals("Sunny", pronosticoExtendido.get(7).getEstado());
        
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("04 Jun 2017"), pronosticoExtendido.get(8).getFecha());
        assertEquals("Sun", pronosticoExtendido.get(8).getDia());
        assertEquals(52, pronosticoExtendido.get(8).getMaxima());
        assertEquals(43, pronosticoExtendido.get(8).getMinima());
        assertEquals("Partly Cloudy", pronosticoExtendido.get(8).getEstado());

        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("05 Jun 2017"), pronosticoExtendido.get(9).getFecha());
        assertEquals("Mon", pronosticoExtendido.get(9).getDia());
        assertEquals(46, pronosticoExtendido.get(9).getMaxima());
        assertEquals(39, pronosticoExtendido.get(9).getMinima());
        assertEquals("Partly Cloudy", pronosticoExtendido.get(9).getEstado());
    }
    
    
    @Test
    public void testTransformWeatherJsonToPronostico() throws Exception {
        Pronostico pronostico = Transformer.transformWeatherJsonToPronostico(weatherJson);
        assertEquals(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US).parse("Sat, 27 May 2017 12:00 PM AKDT"), pronostico.getFecha());
        assertEquals(38, pronostico.getTemperatura());
        assertEquals("Mostly Cloudy", pronostico.getEstado());
    }
    
    @Test
    public void testTransformDateToString() throws ParseException {
        Date fecha = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse("28 May 2017");
        String texto = Transformer.transformDateToString(fecha);
        assertEquals("2017-05-28", texto);
    }
    
    @Test
    public void testTransformStringToDate() throws ParseException {
        String texto = "28 May 2017";
        Date fecha = Transformer.transformStringToDate(texto, true);
        assertEquals(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(texto), fecha);
    }
}
