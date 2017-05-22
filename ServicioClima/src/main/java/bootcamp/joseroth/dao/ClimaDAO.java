/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.dao;

import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public interface ClimaDAO<T> {
    int insertar(T o);
    T select(int i);
    void update(int i);
    void delete(int i);
}
