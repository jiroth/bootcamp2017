/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.builders;

import bootcamp.joseroth.modelos.Ubicacion;

/**
 *
 * @author José Ignacio Roth
 */
public class UbicacionBuilder {
    private int idUbicacion;
    private String ciudad;
    private String pais;
    
    public UbicacionBuilder withIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
        return this;
    }
    
    public UbicacionBuilder withCiudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }
    
    public UbicacionBuilder withPais(String pais) {
        this.pais = pais;
        return this;
    }
    
    public Ubicacion build() {
        Ubicacion u = new Ubicacion();
        u.setIdUbicacion(this.idUbicacion);
        u.setCiudad(this.ciudad);
        u.setPais(this.pais);
        return u;
    }
}
