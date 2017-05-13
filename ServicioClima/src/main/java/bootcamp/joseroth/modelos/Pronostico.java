/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author José Ignacio Roth
 */
public class Pronostico {
    private int idPronostico;
    private Date fecha;
    private Ubicacion ubicacion;
    private int temperatura;
    private String estado;
    private Atmosfera atmosfera;
    private Viento viento;
    private ArrayList<PronosticoExtendido> pronositicoExtendido;

    public int getIdPronostico() {
        return idPronostico;
    }

    public void setIdPronostico(int idPronostico) {
        this.idPronostico = idPronostico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Atmosfera getAtmosfera() {
        return atmosfera;
    }

    public void setAtmosfera(Atmosfera atmosfera) {
        this.atmosfera = atmosfera;
    }

    public Viento getViento() {
        return viento;
    }

    public void setViento(Viento viento) {
        this.viento = viento;
    }

    public ArrayList<PronosticoExtendido> getPronositicoExtendido() {
        return pronositicoExtendido;
    }

    public void setPronositicoExtendido(ArrayList<PronosticoExtendido> pronositicoExtendido) {
        this.pronositicoExtendido = pronositicoExtendido;
    }

    public Pronostico(Date fecha, Ubicacion ubicacion, int temperatura, String estado, Atmosfera atmosfera, Viento viento) {
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.temperatura = temperatura;
        this.estado = estado;
        this.atmosfera = atmosfera;
        this.viento = viento;
    }
    
    public Pronostico(){
    }

    @Override
    public String toString() {
        return "Pronostico: \nFecha: " + fecha + "\nUbicacion: " + ubicacion + "\nTemperatura: " + temperatura 
                + "\nEstado: " + estado + "\nAtmósfera: " + atmosfera + "\nViento: " + viento + "\nPronósitico Extendido: " 
                + pronositicoExtendido;
    }
    
}
