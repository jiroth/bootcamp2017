/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapter;

import bootcamp.joseroth.modelos.Pronostico;
import java.io.IOException;
import java.text.ParseException;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author José Ignacio Roth
 */
public class AdapterJsonWeatherPronosticoTest {
    private static String ciudad = "Cordoba";
    private static String pais = "Argentina";
    private ClienteYahooWeather clienteMock;
    
    public AdapterJsonWeatherPronosticoTest() {
    }
    
    @Before
    public void setUp() {
        clienteMock = EasyMock.createMock(ClienteYahooWeather.class);
    }

    @After
    public void tearDown() {
        clienteMock = null;
    }


    @Test
    public void testGetPronostico() throws ParseException, IOException {
        EasyMock.expect(clienteMock.getJsonWeather(ciudad, pais)).andReturn("{\"query\":{\"count\":1,\"created\":\"2017-06-01T14:11:57Z\",\"lang\":\"es\",\"results\":{\"channel\":{\"units\":{\"distance\":\"km\",\"pressure\":\"mb\",\"speed\":\"km/h\",\"temperature\":\"C\"},\"title\":\"Yahoo! Weather - Cordoba, CBA, AR\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-466861/\",\"description\":\"Yahoo! Weather for Cordoba, CBA, AR\",\"language\":\"en-us\",\"lastBuildDate\":\"Thu, 01 Jun 2017 11:11 AM ART\",\"ttl\":\"60\",\"location\":{\"city\":\"Cordoba\",\"country\":\"Argentina\",\"region\":\" CBA\"},\"wind\":{\"chill\":\"43\",\"direction\":\"0\",\"speed\":\"11.27\"},\"atmosphere\":{\"humidity\":\"86\",\"pressure\":\"32780.26\",\"rising\":\"0\",\"visibility\":\"25.91\"},\"astronomy\":{\"sunrise\":\"8:7 am\",\"sunset\":\"6:21 pm\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Cordoba, CBA, AR at 10:00 AM ART\",\"lat\":\"-31.403959\",\"long\":\"-64.197929\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-466861/\",\"pubDate\":\"Thu, 01 Jun 2017 10:00 AM ART\",\"condition\":{\"code\":\"30\",\"date\":\"Thu, 01 Jun 2017 10:00 AM ART\",\"temp\":\"7\",\"text\":\"Partly Cloudy\"},\"forecast\":[{\"code\":\"32\",\"date\":\"01 Jun 2017\",\"day\":\"Thu\",\"high\":\"15\",\"low\":\"4\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"02 Jun 2017\",\"day\":\"Fri\",\"high\":\"17\",\"low\":\"3\",\"text\":\"Partly Cloudy\"},{\"code\":\"28\",\"date\":\"03 Jun 2017\",\"day\":\"Sat\",\"high\":\"15\",\"low\":\"10\",\"text\":\"Mostly Cloudy\"},{\"code\":\"30\",\"date\":\"04 Jun 2017\",\"day\":\"Sun\",\"high\":\"17\",\"low\":\"6\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"05 Jun 2017\",\"day\":\"Mon\",\"high\":\"13\",\"low\":\"4\",\"text\":\"Mostly Sunny\"},{\"code\":\"34\",\"date\":\"06 Jun 2017\",\"day\":\"Tue\",\"high\":\"17\",\"low\":\"5\",\"text\":\"Mostly Sunny\"},{\"code\":\"34\",\"date\":\"07 Jun 2017\",\"day\":\"Wed\",\"high\":\"20\",\"low\":\"6\",\"text\":\"Mostly Sunny\"},{\"code\":\"30\",\"date\":\"08 Jun 2017\",\"day\":\"Thu\",\"high\":\"18\",\"low\":\"11\",\"text\":\"Partly Cloudy\"},{\"code\":\"32\",\"date\":\"09 Jun 2017\",\"day\":\"Fri\",\"high\":\"19\",\"low\":\"6\",\"text\":\"Sunny\"},{\"code\":\"32\",\"date\":\"10 Jun 2017\",\"day\":\"Sat\",\"high\":\"16\",\"low\":\"8\",\"text\":\"Sunny\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/30.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Partly Cloudy\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Thu - Sunny. High: 15Low: 4\\n<BR /> Fri - Partly Cloudy. High: 17Low: 3\\n<BR /> Sat - Mostly Cloudy. High: 15Low: 10\\n<BR /> Sun - Partly Cloudy. High: 17Low: 6\\n<BR /> Mon - Mostly Sunny. High: 13Low: 4\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-466861/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}");
        
        EasyMock.replay(clienteMock);
        
        ObjectYahooWeather adapterJsonWeatherPronostico = new AdapterJsonWeatherPronostico(clienteMock);
        Pronostico resultado = adapterJsonWeatherPronostico.getPronostico(ciudad, pais);
        
        assertNotNull(resultado);
        assertTrue(resultado instanceof Pronostico);
        
        EasyMock.verify(clienteMock);
    }
    
    @Test
    public void testGetPronosticoNull() throws ParseException, IOException {
        EasyMock.expect(clienteMock.getJsonWeather(ciudad, pais)).andReturn(null);
        
        EasyMock.replay(clienteMock);
        
        ObjectYahooWeather adapterJsonWeatherPronostico = new AdapterJsonWeatherPronostico(clienteMock);
        Pronostico resultado = adapterJsonWeatherPronostico.getPronostico(ciudad, pais);
        
        assertNull(resultado);
        
        EasyMock.verify(clienteMock);
    }
    
}
