/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author José Ignacio Roth
 */
public class PronosticoBuilder {
    private int idPronostico;
    private Date fecha;
    private Ubicacion ubicacion;
    private int temperatura;
    private String estado;
    private Atmosfera atmosfera;
    private Viento viento;
    private ArrayList<PronosticoExtendido> pronositicoExtendido;
    
    public PronosticoBuilder withIdPronostico(int id) {
        this.idPronostico = id;
        return this;
    }
    
    public PronosticoBuilder withFecha(Date fecha) {
        this.fecha = fecha;
        return this;
    }
    
    public PronosticoBuilder withUbicacion(Ubicacion u) {
        this.ubicacion = u;
        return this;
    }
    
    public PronosticoBuilder withTemperatura(int t) {
        this.temperatura = t;
        return this;
    }
    
    public PronosticoBuilder withEstado(String e) {
        this.estado = e;
        return this;
    }
    
    public PronosticoBuilder withAtmosfera(Atmosfera a) {
        this.atmosfera = a;
        return this;
    }
    
    public PronosticoBuilder withViento(Viento v) {
        this.viento = v;
        return this;
    }
    
    public PronosticoBuilder withPronosticoExtendido(ArrayList<PronosticoExtendido> pe) {
        this.pronositicoExtendido = pe;
        return this;
    }
    
    public Pronostico build() {
        Pronostico p = new Pronostico();
        p.setIdPronostico(this.idPronostico);
        p.setFecha(this.fecha);
        p.setUbicacion(this.ubicacion);
        p.setTemperatura(this.temperatura);
        p.setEstado(this.estado);
        p.setAtmosfera(this.atmosfera);
        p.setViento(this.viento);
        p.setPronositicoExtendido(this.pronositicoExtendido);
        return p;
    }
}
