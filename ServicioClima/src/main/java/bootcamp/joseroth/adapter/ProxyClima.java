/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.adapter;

import bootcamp.joseroth.validaciones.Validacion;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author José Ignacio Roth
 */
@Component
public class ProxyClima implements ClienteYahooWeather {

    @Resource
    private ClienteYahooWeather clienteYahooWeather;

    @Autowired
    private Validacion validacion;

    @Override
    public String getJsonWeather(String ciudad, String pais) {

        String respuesta = "";

        if (validacion.validarParametros(ciudad, pais)) {
            String consultaYQL = "select * from weather.forecast where woeid in "
                    + "(select woeid from geo.places(1) where text=\"" + ciudad + ", " + pais + "\") and u=\"c\"";

            respuesta = clienteYahooWeather.getJsonWeather(consultaYQL, "json");
        }

        return respuesta;

    }

    public ProxyClima(ClienteYahooWeather clienteYahooWeather, Validacion validacion) {
        this.clienteYahooWeather = clienteYahooWeather;
        this.validacion = validacion;
    }

    public ProxyClima() {
    }

}
