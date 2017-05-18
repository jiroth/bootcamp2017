/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicioclima;

import bootcamp.joseroth.builders.*;
import bootcamp.joseroth.dao.*;
import bootcamp.joseroth.modelos.*;
import java.util.ArrayList;

/**
 *
 * @author José Ignacio Roth
 */
public class ServicioClima {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClimaDAO atmosferaDAO = new AtmosferaDAO(); 
        ClimaDAO ubicacionDAO = new UbicacionDAO();
        ClimaDAO vientoDAO = new VientoDAO();
        ClimaDAO pronosticoDAO = new PronosticoDAO();
        ClimaDAO pronosticoExtendidoDAO = new PronosticoExtendidoDAO();

        //DATOS HARDCODEADOS
        Ubicacion ubicacion = new Ubicacion("Cordoba", "Argentina");
        int idUbicacion = ubicacionDAO.insertar(ubicacion);
        ubicacion.setIdUbicacion(idUbicacion);
        
        Atmosfera atmosfera = new Atmosfera(59, 18);
        int idAtmosfera = atmosferaDAO.insertar(atmosfera);
        atmosfera.setIdAtmosfera(idAtmosfera);

        Viento viento = new Viento(203, 7);
        int idViento = vientoDAO.insertar(viento);
        viento.setIdViento(idViento);

        Pronostico pronostico = new PronosticoBuilder().withFecha("Fri, 21 Apr 2017 05:00 PM ART").withUbicacion(ubicacion).withTemperatura(64).withEstado("Soleado").withAtmosfera(atmosfera).withViento(viento).build();
        int idPronostico = pronosticoDAO.insertar(pronostico);
        pronostico.setIdPronostico(idPronostico);

        PronosticoExtendido dia1, dia2, dia3, dia4, dia5, dia6, dia7, dia8, dia9, dia10;
        PronosticoExtendido[] dias = {
            dia1 = new PronosticoExtendidoBuilder().withFecha("21 Apr 2017").withDia("Fri").withEstado("Partly Cloudy").withMinima(58).withMaxima(64).withIdPronostico(idPronostico).build(),
            dia2 = new PronosticoExtendidoBuilder().withFecha("22 Apr 2017").withDia("Sat").withEstado("Partly Cloudy").withMinima(53).withMaxima(67).withIdPronostico(idPronostico).build(),
            dia3 = new PronosticoExtendidoBuilder().withFecha("23 Apr 2017").withDia("Sun").withEstado("Partly Cloudy").withMinima(52).withMaxima(68).withIdPronostico(idPronostico).build(),
            dia4 = new PronosticoExtendidoBuilder().withFecha("24 Apr 2017").withDia("Mon").withEstado("Scattered Showers").withMinima(55).withMaxima(70).withIdPronostico(idPronostico).build(),
            dia5 = new PronosticoExtendidoBuilder().withFecha("25 Apr 2017").withDia("Tue").withEstado("Scattered Thunderstorms").withMinima(51).withMaxima(64).withIdPronostico(idPronostico).build(),
            dia6 = new PronosticoExtendidoBuilder().withFecha("26 Apr 2017").withDia("Wed").withEstado("Sunny").withMinima(45).withMaxima(65).withIdPronostico(idPronostico).build(),
            dia7 = new PronosticoExtendidoBuilder().withFecha("27 Apr 2017").withDia("Thu").withEstado("Sunny").withMinima(53).withMaxima(67).withIdPronostico(idPronostico).build(),
            dia8 = new PronosticoExtendidoBuilder().withFecha("28 Apr 2017").withDia("Fri").withEstado("Sunny").withMinima(53).withMaxima(75).withIdPronostico(idPronostico).build(),
            dia9 = new PronosticoExtendidoBuilder().withFecha("29 Apr 2017").withDia("Sat").withEstado("Sunny").withMinima(53).withMaxima(76).withIdPronostico(idPronostico).build(),
            dia10 = new PronosticoExtendidoBuilder().withFecha("30 Apr 2017").withDia("Sun").withEstado("Sunny").withMinima(53).withMaxima(72).withIdPronostico(idPronostico).build()
        };
        ArrayList<PronosticoExtendido> pronosticoExtendido = new ArrayList<>();
        for (PronosticoExtendido dia : dias) {
            int idPronosticoExtendido = pronosticoExtendidoDAO.insertar(dia);
            dia.setIdPronosticoExtendido(idPronosticoExtendido);
            pronosticoExtendido.add(dia);
        }
        pronostico.setPronositicoExtendido(pronosticoExtendido);

        //SE TRAEN LOS DATOS DE LA BASE
        Pronostico p = (Pronostico) pronosticoDAO.select(idPronostico);

        ArrayList<PronosticoExtendido> pe = (ArrayList<PronosticoExtendido>) pronosticoExtendidoDAO.select(p.getIdPronostico());
        p.setPronositicoExtendido(pe);

        Ubicacion u = (Ubicacion) ubicacionDAO.select(p.getUbicacion().getIdUbicacion());
        p.setUbicacion(u);

        Atmosfera a = (Atmosfera) atmosferaDAO.select(p.getAtmosfera().getIdAtmosfera());
        p.setAtmosfera(a);

        Viento v = (Viento) vientoDAO.select(p.getViento().getIdViento());
        p.setViento(v);

        System.out.println(p.toString());
        
    }
}
