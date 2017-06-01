/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.validaciones;

import org.springframework.stereotype.Service;

/**
 *
 * @author José Ignacio Roth
 */
@Service
public class Validacion {

    public boolean validarParametros(String ciudad, String pais) {
        String regex = "^[a-zA-Z ]+$";
        if (ciudad.matches(regex) && pais.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

}
