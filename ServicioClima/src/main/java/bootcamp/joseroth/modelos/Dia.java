/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import java.util.Date;

/**
 *
 * @author José Ignacio Roth
 */
public class Dia {
    private int idDia;
    private Date fecha;
    private String dia;
    private String estado;
    private int minima;
    private int maxima;
    private int idPronostico;


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idPronosticoExtendido) {
        this.idDia = idPronosticoExtendido;
    }

    public int getIdPronostico() {
        return idPronostico;
    }

    public void setIdPronostico(int idPronostico) {
        this.idPronostico = idPronostico;
    }
    
    public Dia(Date fecha, String dia, String estado, int minima, int maxima) {
        this.fecha = fecha;
        this.dia = dia;
        this.estado = estado;
        this.minima = minima;
        this.maxima = maxima;
    }
    
    public Dia(){
    }

    @Override
    public String toString() {
        return "PronosticoExtendido{" + "idDia=" + idDia + ", fecha=" + fecha + ", dia=" + dia + ", estado=" + estado + ", minima=" + minima + ", maxima=" + maxima + ", idPronostico=" + idPronostico + '}';
    }
    
}
