/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Atmosfera;

/**
 *
 * @author José Ignacio Roth
 */
public class AtmosferaBuilder {
    private int idAtmosfera;
    private int humedad;
    private double visibilidad;
    
    public AtmosferaBuilder withIdAtmosfera(int idAtmosfera) {
        this.idAtmosfera = idAtmosfera;
        return this;
    }
    
    public AtmosferaBuilder withHumedad(int humedad) {
        this.humedad = humedad;
        return this;
    }
    
    public AtmosferaBuilder withVisibilidad(double visibilidad) {
        this.visibilidad = visibilidad;
        return this;
    }
    
    public Atmosfera build() {
        Atmosfera a = new Atmosfera();
        a.setIdAtmosfera(this.idAtmosfera);
        a.setHumedad(this.humedad);
        a.setVisibilidad(this.visibilidad);
        return a;
    }
}
