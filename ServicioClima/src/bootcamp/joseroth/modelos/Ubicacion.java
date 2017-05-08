/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

/**
 *
 * @author José Ignacio Roth
 */
public class Ubicacion {
    private int idUbicacion;
    private String ciudad;
    private String pais;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Ubicacion(String ciudad, String pais) {
        this.ciudad = ciudad;
        this.pais = pais;
    }
    
    public Ubicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Ubicacion() {
    }

    @Override
    public String toString() {
        return ciudad + ", " + pais;
    }
    
}
