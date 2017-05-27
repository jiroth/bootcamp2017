/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Viento;

/**
 *
 * @author José Ignacio Roth
 */
public class VientoBuilder {
    private int idViento;
    private int direccion;
    private int velocidad;
    
    public VientoBuilder withIdViento(int idViento) {
        this.idViento = idViento;
        return this;
    }
    
    public VientoBuilder withDireccion(int direccion) {
        this.direccion = direccion;
        return this;
    }
    
    public VientoBuilder withVelocidad(int velocidad) {
        this.velocidad = velocidad;
        return this;
    }
    
    public Viento build() {
        Viento v = new Viento();
        v.setIdViento(this.idViento);
        v.setDireccion(this.direccion);
        v.setVelocidad(this.velocidad);
        return v;
    }
}
