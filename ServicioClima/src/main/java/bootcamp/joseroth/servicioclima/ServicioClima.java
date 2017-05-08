/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicioclima;

import bootcamp.joseroth.modelos.Pronostico;
import bootcamp.joseroth.modelos.Viento;
import bootcamp.joseroth.modelos.Ubicacion;
import bootcamp.joseroth.modelos.Atmosfera;
import bootcamp.joseroth.modelos.PronosticoExtendido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import bootcamp.joseroth.servicios.ServicioPronostico;

/**
 *
 * @author José Ignacio Roth
 */
public class ServicioClima {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        ServicioPronostico servicioPronostico = new ServicioPronostico();

        Date fecha = servicioPronostico.cambiarFormatoFecha("Fri, 21 Apr 2017 05:00 PM ART");
        
        Ubicacion ubicacion = new Ubicacion("Cordoba", "Argentina");
        servicioPronostico.registrarUbicacion(ubicacion);
        ubicacion.setIdUbicacion(servicioPronostico.getIdUbicacion());
        
        Atmosfera atmosfera = new Atmosfera(59, 18);
        servicioPronostico.registrarAtmosfera(atmosfera);
        atmosfera.setIdAtmosfera(servicioPronostico.getIdAtmosfera());
        
        Viento viento = new Viento(203, 7);
        servicioPronostico.registrarViento(viento);
        viento.setIdViento(servicioPronostico.getIdViento());
        
        Pronostico pronostico = new Pronostico(fecha, ubicacion, 64, "Cloudy", atmosfera, viento);
        servicioPronostico.registrarPronostico(pronostico);
        pronostico.setIdPronostico(servicioPronostico.getIdPronostico());
        
        Date dia1 = servicioPronostico.textoAFechaPE("21 Apr 2017");
        Date dia2 = servicioPronostico.textoAFechaPE("22 Apr 2017");
        Date dia3 = servicioPronostico.textoAFechaPE("23 Apr 2017");
        Date dia4 = servicioPronostico.textoAFechaPE("24 Apr 2017");
        Date dia5 = servicioPronostico.textoAFechaPE("25 Apr 2017");
        Date dia6 = servicioPronostico.textoAFechaPE("26 Apr 2017");
        Date dia7 = servicioPronostico.textoAFechaPE("27 Apr 2017");
        Date dia8 = servicioPronostico.textoAFechaPE("28 Apr 2017");
        Date dia9 = servicioPronostico.textoAFechaPE("29 Apr 2017");
        Date dia10 = servicioPronostico.textoAFechaPE("30 Apr 2017");
        PronosticoExtendido pe1 = new PronosticoExtendido(dia1, "Fri", "Partly Cloudy", 58, 64);
        PronosticoExtendido pe2 = new PronosticoExtendido(dia2, "Sat", "Partly Cloudy", 53, 67);
        PronosticoExtendido pe3 = new PronosticoExtendido(dia3, "Sun", "Partly Cloudy", 52, 68);
        PronosticoExtendido pe4 = new PronosticoExtendido(dia4, "Mon", "Scattered Showers", 55, 70);
        PronosticoExtendido pe5 = new PronosticoExtendido(dia5, "Tue", "Scattered Thunderstorms", 51, 64);
        PronosticoExtendido pe6 = new PronosticoExtendido(dia6, "Wed", "Sunny", 45, 65);
        PronosticoExtendido pe7 = new PronosticoExtendido(dia7, "Thu", "Sunny", 53, 67);
        PronosticoExtendido pe8 = new PronosticoExtendido(dia8, "Fri", "Sunny", 53, 75);
        PronosticoExtendido pe9 = new PronosticoExtendido(dia9, "Sat", "Sunny", 53, 76);
        PronosticoExtendido pe10 = new PronosticoExtendido(dia10, "Sun", "Sunny", 53, 72);
        ArrayList<PronosticoExtendido> pronosticoExtendido = new ArrayList<>();
        pronosticoExtendido.add(pe1);
        pronosticoExtendido.add(pe2);
        pronosticoExtendido.add(pe3);
        pronosticoExtendido.add(pe4);
        pronosticoExtendido.add(pe5);
        pronosticoExtendido.add(pe6);
        pronosticoExtendido.add(pe7);
        pronosticoExtendido.add(pe8);
        pronosticoExtendido.add(pe9);
        pronosticoExtendido.add(pe10);
        for (PronosticoExtendido pro : pronosticoExtendido) {
            pro.setIdPronostico(pronostico.getIdPronostico());
            servicioPronostico.registrarPronosticoExtendido(pro);
        }
        pronostico.setPronositicoExtendido(pronosticoExtendido);

        //SE MUESTRAN LOS DATOS INGRESADOS A MANO
//        System.out.println(pronostico.toString());
        
        //SE TRAEN LOS DATOS DE LA BASE
        Pronostico p = servicioPronostico.getPronostico(servicioPronostico.getIdPronostico());
        ArrayList<PronosticoExtendido> pe = servicioPronostico.getPronosticoExtendido(p.getIdPronostico());
        p.setPronositicoExtendido(pe);
        Ubicacion u = servicioPronostico.getUbicacion(p.getUbicacion().getIdUbicacion());
        p.setUbicacion(u);
        Atmosfera a = servicioPronostico.getAtmosfera(p.getAtmosfera().getIdAtmosfera());
        p.setAtmosfera(a);
        Viento v = servicioPronostico.getViento(p.getViento().getIdViento());
        p.setViento(v);
        System.out.println(p.toString());
    }
    
}
