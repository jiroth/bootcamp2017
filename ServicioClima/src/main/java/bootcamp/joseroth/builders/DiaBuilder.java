/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Dia;
import java.util.Date;

/**
 *
 * @author José Ignacio Roth
 */
public class DiaBuilder {
    private int idDia;
    private Date fecha;
    private String dia;
    private String estado;
    private int minima;
    private int maxima;
    private int idPronostico;
    
    public DiaBuilder withIdPronosticoExtendido(int id) {
        this.idDia = id;
        return this;
    }
    
    public DiaBuilder withFecha(Date fecha) {
        this.fecha = fecha;
        return this;
    }
    
    public DiaBuilder withDia(String d) {
        this.dia = d;
        return this;
    }
    
    public DiaBuilder withEstado(String e) {
        this.estado = e;
        return this;
    }
    
    public DiaBuilder withMinima(int m) {
        this.minima = m;
        return this;
    }
    
    public DiaBuilder withMaxima(int m) {
        this.maxima = m;
        return this;
    }
    
    public DiaBuilder withIdPronostico(int id) {
        this.idPronostico = id;
        return this;
    }
    
    public Dia build() {
        Dia d = new Dia();
        d.setIdDia(this.idDia);
        d.setFecha(this.fecha);
        d.setDia(this.dia);
        d.setEstado(this.estado);
        d.setMinima(this.minima);
        d.setMaxima(this.maxima);
        d.setIdPronostico(this.idPronostico);
        return d;
    }
}
