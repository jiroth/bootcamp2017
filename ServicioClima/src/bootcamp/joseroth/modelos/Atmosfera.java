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
public class Atmosfera {
    private int idAtmosfera;
    private int humedad;
    private double visibilidad;

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public double getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(double visibilidad) {
        this.visibilidad = visibilidad;
    }

    public int getIdAtmosfera() {
        return idAtmosfera;
    }

    public void setIdAtmosfera(int idAtmosfera) {
        this.idAtmosfera = idAtmosfera;
    }

    public Atmosfera(int humedad, double visibilidad) {
        this.humedad = humedad;
        this.visibilidad = visibilidad;
    }
    
    public Atmosfera(int idAtmosfera) {
        this.idAtmosfera = idAtmosfera;
    }

    public Atmosfera() {
    }

    @Override
    public String toString() {
        return "Humedad: " + humedad + ", Visibilidad: " + visibilidad;
    }
    
}
