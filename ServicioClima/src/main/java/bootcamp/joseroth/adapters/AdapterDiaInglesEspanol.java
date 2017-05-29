/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public class AdapterDiaInglesEspanol implements Espanol {

    @Autowired
    @Qualifier("diaInglesImpl")
    private Ingles ingles;

    @Override
    public void set(Ingles ingles) {
        this.ingles = ingles;
    }
    
    @Override
    public String get() {
        String dia = "";
        switch (ingles.get()) {
            case "Sun":
                dia = "Domingo"; break;
            case "Mon":
                dia = "Lunes"; break;
            case "Tue":
                dia = "Martes"; break;
            case "Wed":
                dia = "Miércoles"; break;
            case "Thu":
                dia = "Jueves"; break;
            case "Fri":
                dia = "Viernes"; break;
            case "Sat":
                dia = "Sábado"; break;
        }
        return dia;
    }

}
