/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.modelos.Dia;
import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.Ubicacion;
import bootcamp.joseroth.modelos.Viento;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Ignacio Roth
 */
public class PronosticoBuilderTest {
    
    public PronosticoBuilderTest() {
    }

    @Test
    public void testWithIdPronostico() {
        int id = 1;
        Pronostico p = new PronosticoBuilder().withIdPronostico(id).build();
        assertEquals(id, p.getIdPronostico());
    }

    @Test
    public void testWithFecha() {
        Date fecha = new Date();
        Pronostico p = new PronosticoBuilder().withFecha(fecha).build();
        assertEquals(fecha, p.getFecha());
    }

    @Test
    public void testWithUbicacion() {
        Ubicacion u = new UbicacionBuilder().withIdUbicacion(1).withCiudad("Cordoba").withPais("Argentina").build();
        Pronostico p = new PronosticoBuilder().withUbicacion(u).build();
        assertEquals(u, p.getUbicacion());
    }

    @Test
    public void testWithTemperatura() {
        int temp = 10;
        Pronostico p = new PronosticoBuilder().withTemperatura(temp).build();
        assertEquals(temp, p.getTemperatura());
    }

    @Test
    public void testWithEstado() {
        String estado = "Sunny";
        Pronostico p = new PronosticoBuilder().withEstado(estado).build();
        assertEquals(estado, p.getEstado());
    }

    @Test
    public void testWithAtmosfera() {
        Atmosfera a = new AtmosferaBuilder().withHumedad(10).withVisibilidad(20).build();
        Pronostico p = new PronosticoBuilder().withAtmosfera(a).build();
        assertEquals(a, p.getAtmosfera());
    }

    @Test
    public void testWithViento() {
        Viento v = new VientoBuilder().withDireccion(200).withVelocidad(4).build();
        Pronostico p = new PronosticoBuilder().withViento(v).build();
        assertEquals(v, p.getViento());
    }

    @Test
    public void testWithPronosticoExtendido() {
        ArrayList<Dia> pronosticoExtendido = new ArrayList<>();
        Dia dia = new DiaBuilder().withFecha(new Date()).withDia("Mon").withEstado("Cloudy").withMinima(3).withMaxima(7).build();
        pronosticoExtendido.add(dia);
        Pronostico p = new PronosticoBuilder().withPronosticoExtendido(pronosticoExtendido).build();
        assertEquals(pronosticoExtendido, p.getPronositicoExtendido());
    }

    
}
