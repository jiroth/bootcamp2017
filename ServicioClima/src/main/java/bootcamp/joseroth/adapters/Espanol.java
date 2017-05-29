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
public interface Espanol {
    String get();
    void set(Ingles ingles);
}
