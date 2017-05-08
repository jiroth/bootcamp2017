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
public class Viento {
    private int idViento;
    private int direccion;
    private int velocidad;

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getIdViento() {
        return idViento;
    }

    public void setIdViento(int idViento) {
        this.idViento = idViento;
    }

    public Viento(int direccion, int velocidad) {
        this.direccion = direccion;
        this.velocidad = velocidad;
    }
    
    public Viento(int idViento) {
        this.idViento = idViento;
    }
    
    public Viento() {
    }

    @Override
    public String toString() {
        return "Direccion: " + direccion + ", Velocidad: " + velocidad;
    }
    
}
