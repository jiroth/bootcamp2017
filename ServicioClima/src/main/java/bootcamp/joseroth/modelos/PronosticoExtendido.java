/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author José Ignacio Roth
 */
public class PronosticoExtendido {
    private int idPronosticoExtendido;
    private Date fecha;
    private String dia;
    private String estado;
    private int minima;
    private int maxima;
    private int idPronostico;
    static final private SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

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

    public int getIdPronosticoExtendido() {
        return idPronosticoExtendido;
    }

    public void setIdPronosticoExtendido(int idPronosticoExtendido) {
        this.idPronosticoExtendido = idPronosticoExtendido;
    }

    public int getIdPronostico() {
        return idPronostico;
    }

    public void setIdPronostico(int idPronostico) {
        this.idPronostico = idPronostico;
    }
    
    public PronosticoExtendido(Date fecha, String dia, String estado, int minima, int maxima) {
        this.fecha = fecha;
        this.dia = dia;
        this.estado = estado;
        this.minima = minima;
        this.maxima = maxima;
    }
    
    public PronosticoExtendido(){
    }

    @Override
    public String toString() {
        return "\nFecha: " + formatter.format(fecha) + "\nDía: " + dia + "\nEstado: " + estado + "\nMínima: " + minima + "\nMáxima: " + maxima;
    }
    
}
