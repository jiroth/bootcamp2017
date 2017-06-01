/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapter;

import bootcamp.joseroth.modelos.Pronostico;

/**
 *
 * @author José Ignacio Roth
 */
public interface ObjectYahooWeather {
    Pronostico getPronostico(String ciudad, String pais);
}
