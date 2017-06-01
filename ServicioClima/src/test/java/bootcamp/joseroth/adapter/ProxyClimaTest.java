/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapter;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class ProxyClimaTest {
    
    public ProxyClimaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetJsonWeather() {
        String ciudad = "Cordoba";
        String pais = "Argentina";
        
        ClienteYahooWeather clienteMock = EasyMock.createMock(ClienteYahooWeather.class);
        EasyMock.expect(clienteMock.getJsonWeather(ciudad, pais)).andReturn(EasyMock.anyString());
        EasyMock.replay(clienteMock);
        
        ClienteYahooWeather proxyClima = new ProxyClima();
        String resultado = proxyClima.getJsonWeather(ciudad, pais);
        
        assertEquals(clienteMock, resultado);
        
        EasyMock.verify(clienteMock);
    }
    
}
