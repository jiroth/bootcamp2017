/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp.joseroth.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author José Ignacio Roth
 */
public class Utils {
    public Date textoAfecha(String s, boolean b) {
        Date fecha = new Date();
        SimpleDateFormat formatter;
        if(b) {
            formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa z", Locale.US);
        } else {
            formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        }
        try {
            fecha = formatter.parse(s);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return fecha;
    }
    
    public String formatoFecha(Date d) {
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(d);
        return fecha;
    }
}
