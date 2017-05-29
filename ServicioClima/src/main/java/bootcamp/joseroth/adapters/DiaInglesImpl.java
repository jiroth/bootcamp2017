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
public class DiaInglesImpl implements Ingles {
    
    private String dia;

    @Override
    public void set(String dia) {
        this.dia = dia;
    }
    
    @Override
    public String get() {
        return dia;
    }

    public DiaInglesImpl() {
        dia = "";
    }
    
}
