/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.recursos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author José Ignacio Roth
 */
@Path("/v1/public")
@Produces("application/json")
public interface ClienteYahooWeather {
    @GET
    @Path("/yql")
    String getPronostico(@QueryParam("q") String consultaYQL, @QueryParam("format")String formato);
}
