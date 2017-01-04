package logica;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hixam on 21/12/16.
 */
public class Gestion {

    public static double MediaKm (double [] coordenadas, double duracion)
    {
        // double pos1Lat = coordenadas[0];
        // double pos1Lon = coordenadas[1];
        // double pos2Lat = coordenadas[2];
        // double pos2Lon = coordenadas[3];
        double [] tablaDistancias = coordenadas;//new double[2];
        double distanciaMedia =0;
        double distanciaEntreDosPosiciones= tablaDistancias[0] - tablaDistancias[1];
        String error = "";
        double velocidad = (distanciaEntreDosPosiciones/duracion)*3600; // *60segundos*60minutos = km/hora

        System.out.println("velocidad en metros/segundo  = " + (velocidad*1000) + " M/H");
        System.out.println("velocidad en Kilometros/hora  = " + String.format( "%.10f", (velocidad)) + " KM/H");


        return (velocidad)   ; // dividido 1000 = metros
    }

}
