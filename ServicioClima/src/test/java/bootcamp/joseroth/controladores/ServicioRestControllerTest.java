/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.controladores;

import bootcamp.joseroth.adapter.ObjectYahooWeather;
import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.Ubicacion;
import java.sql.SQLException;
import java.text.ParseException;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author José Ignacio Roth
 */
public class ServicioRestControllerTest {
    
    private static final String ciudad = "Cordoba";
    private static final String pais = "Argentina";
    private Pronostico proMock;
    private Ubicacion ubiMock;
    private ObjectYahooWeather objYahooMock;
    private ServicioBaseController servBaseContMock;

    public ServicioRestControllerTest() {
    }

    @Before
    public void setUp() {
        proMock = EasyMock.createMock(Pronostico.class);
        ubiMock = EasyMock.createMock(Ubicacion.class);
        objYahooMock = EasyMock.createMock(ObjectYahooWeather.class);
        servBaseContMock = EasyMock.createMock(ServicioBaseController.class);
    }

    @After
    public void tearDown() {
        proMock = null;
        ubiMock = null;
        objYahooMock = null;
        servBaseContMock = null;
    }

    @Test
    public void testGetPronosticoOk() throws ClassNotFoundException, SQLException {
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject())).andReturn(true);
        EasyMock.expect(servBaseContMock.getPronosticoBaseDatos(ciudad)).andReturn(proMock);

        EasyMock.replay(proMock, objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.getPronostico(ciudad);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        EasyMock.verify(proMock, objYahooMock, servBaseContMock);
    }
    
    @Test
    public void testGetPronosticoNull() throws ClassNotFoundException, SQLException {
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject())).andReturn(false);

        EasyMock.replay(objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.getPronostico(ciudad);

        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());

        EasyMock.verify(objYahooMock, servBaseContMock);
    }

    @Test
    public void testAddPronosticoOK() {
        EasyMock.expect(proMock.getUbicacion()).andReturn(ubiMock).times(2);
        EasyMock.expect(ubiMock.getCiudad()).andReturn(ciudad);
        
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject())).andReturn(false);
        servBaseContMock.registrarEnBaseDatos(proMock);
        EasyMock.expect(servBaseContMock.getPronosticoBaseDatos(ciudad)).andReturn(proMock);

        EasyMock.replay(ubiMock, proMock, objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.addPronostico(proMock);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        EasyMock.verify(ubiMock, proMock, objYahooMock, servBaseContMock);
    }
    
    @Test
    public void testAddPronosticoNull() {
        EasyMock.expect(proMock.getUbicacion()).andReturn(ubiMock);
        
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject())).andReturn(true);

        EasyMock.replay(ubiMock, proMock, objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.addPronostico(proMock);

        assertEquals(HttpStatus.CONFLICT, resultado.getStatusCode());

        EasyMock.verify(ubiMock, proMock, objYahooMock, servBaseContMock);
    }

    @Test
    public void testUpdatePronosticoOK() {
        EasyMock.expect(proMock.getUbicacion()).andReturn(ubiMock).times(2);
        EasyMock.expect(ubiMock.getCiudad()).andReturn(ciudad);
        
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject())).andReturn(true);
        servBaseContMock.actualizarEnBaseDatos(proMock);
        EasyMock.expect(servBaseContMock.getPronosticoBaseDatos(ciudad)).andReturn(proMock);

        EasyMock.replay(ubiMock, proMock, objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.updatePronostico(proMock);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        EasyMock.verify(ubiMock, proMock, objYahooMock, servBaseContMock);
    }
    
    @Test
    public void testUpdatePronosticoNull() {
        EasyMock.expect(proMock.getUbicacion()).andReturn(ubiMock);
        
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject())).andReturn(false);

        EasyMock.replay(ubiMock, proMock, objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.updatePronostico(proMock);

        assertEquals(HttpStatus.CONFLICT, resultado.getStatusCode());

        EasyMock.verify(ubiMock, proMock, objYahooMock, servBaseContMock);
    }

    @Test
    public void testGetYahooWeatherNull() throws ClassNotFoundException, SQLException, ParseException {
        EasyMock.expect(objYahooMock.getPronostico(ciudad, pais)).andReturn(null);

        EasyMock.expect(servBaseContMock.comprobarConexionBaseDatos()).andReturn(false);
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject(Ubicacion.class))).andReturn(false);

        EasyMock.replay(objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.getYahooWeather(ciudad, pais);

        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());

        EasyMock.verify(objYahooMock, servBaseContMock);
    }

    @Test
    public void testGetYahooWeatherAllOK() throws ClassNotFoundException, SQLException, ParseException {

        EasyMock.expect(objYahooMock.getPronostico(ciudad, pais)).andReturn(proMock);

        EasyMock.expect(servBaseContMock.comprobarConexionBaseDatos()).andReturn(true);
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject(Ubicacion.class))).andReturn(true);
        servBaseContMock.actualizarEnBaseDatos(proMock);

        EasyMock.replay(ubiMock, proMock, objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.getYahooWeather(ciudad, pais);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        EasyMock.verify(ubiMock, proMock, objYahooMock, servBaseContMock);
    }
    
    @Test
    public void testGetYahooWeatherBaseOK() throws ClassNotFoundException, SQLException, ParseException {
        EasyMock.expect(objYahooMock.getPronostico(ciudad, pais)).andReturn(null);

        EasyMock.expect(servBaseContMock.comprobarConexionBaseDatos()).andReturn(true);
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject())).andReturn(true);
        EasyMock.expect(servBaseContMock.getPronosticoBaseDatos(EasyMock.anyString())).andReturn(proMock);

        EasyMock.replay(ubiMock, proMock, objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.getYahooWeather(ciudad, pais);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        EasyMock.verify(ubiMock, proMock, objYahooMock, servBaseContMock);
    }
    
    @Test
    public void testGetYahooWeatherConexionOK() throws ClassNotFoundException, SQLException, ParseException {
        EasyMock.expect(objYahooMock.getPronostico(ciudad, pais)).andReturn(proMock);

        EasyMock.expect(servBaseContMock.comprobarConexionBaseDatos()).andReturn(false);
        EasyMock.expect(servBaseContMock.comprobarExistenciaEnBaseDatos(EasyMock.anyObject(Ubicacion.class))).andReturn(true);

        EasyMock.replay(proMock, objYahooMock, servBaseContMock);

        ServicioRestController servRestCont = new ServicioRestController(objYahooMock, servBaseContMock);
        ResponseEntity resultado = servRestCont.getYahooWeather(ciudad, pais);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        EasyMock.verify(proMock, objYahooMock, servBaseContMock);
    }

}
