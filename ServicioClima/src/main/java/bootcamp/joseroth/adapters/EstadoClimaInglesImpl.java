/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapters;

import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public class EstadoClimaInglesImpl implements Ingles {

    private String estadoClima;
    
    @Override
    public String get() {
        return estadoClima;
    }

    @Override
    public void set(String estadoClima) {
        this.estadoClima = estadoClima;
    }

    public EstadoClimaInglesImpl() {
        estadoClima = "";
    }
    
}
