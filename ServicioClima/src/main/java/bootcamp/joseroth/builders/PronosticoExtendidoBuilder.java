/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.PronosticoExtendido;
import bootcamp.joseroth.servicios.ServicioBD;
import java.util.Date;

/**
 *
 * @author José Ignacio Roth
 */
public class PronosticoExtendidoBuilder {
    private int idPronosticoExtendido;
    private Date fecha;
    private String dia;
    private String estado;
    private int minima;
    private int maxima;
    private int idPronostico;
    
    public PronosticoExtendidoBuilder withIdPronosticoExtendido(int id) {
        this.idPronosticoExtendido = id;
        return this;
    }
    
    public PronosticoExtendidoBuilder withFecha(String f) {
        ServicioBD sBD = new ServicioBD();
        this.fecha = sBD.textoAfecha(f, false);
        return this;
    }
    
    public PronosticoExtendidoBuilder withDia(String d) {
        this.dia = d;
        return this;
    }
    
    public PronosticoExtendidoBuilder withEstado(String e) {
        this.estado = e;
        return this;
    }
    
    public PronosticoExtendidoBuilder withMinima(int m) {
        this.minima = m;
        return this;
    }
    
    public PronosticoExtendidoBuilder withMaxima(int m) {
        this.maxima = m;
        return this;
    }
    
    public PronosticoExtendidoBuilder withIdPronostico(int id) {
        this.idPronostico = id;
        return this;
    }
    
    public PronosticoExtendido build() {
        PronosticoExtendido pe = new PronosticoExtendido();
        pe.setIdPronosticoExtendido(this.idPronosticoExtendido);
        pe.setFecha(this.fecha);
        pe.setDia(this.dia);
        pe.setEstado(this.estado);
        pe.setMinima(this.minima);
        pe.setMaxima(this.maxima);
        pe.setIdPronostico(this.idPronostico);
        return pe;
    }
}
