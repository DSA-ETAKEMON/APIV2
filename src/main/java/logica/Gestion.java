package logica;

import Entity.Etakemons;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by hixam on 21/12/16.
 */
public  class Gestion {

    static int i=0;
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

    public static int Ganar ( int idEtakemon, int puntosGanador, int puntosPerdedor){

        int puntosGanadosFinalPartida = 0;

        switch (idEtakemon){
            case 1: puntosGanadosFinalPartida = puntosGanador + 20 + (int) (puntosPerdedor*0.2);
                break;
            case 2: puntosGanadosFinalPartida = puntosGanador + 10 + (int) (puntosPerdedor*0.2);
                break;
            case 3: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2)*2;
                break;
            case 4: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2);
                break;
            case 5: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2);
                break;
            case 6: puntosGanadosFinalPartida = puntosGanador + 10 + (int) (puntosPerdedor*0.2);
                break;
            case 7: puntosGanadosFinalPartida = puntosGanador + 10 + (int) (puntosPerdedor*0.2);
                break;
            case 8: puntosGanadosFinalPartida = puntosGanador + 5 + (int) (puntosPerdedor*0.2);
                break;
            case 9: puntosGanadosFinalPartida = puntosGanador + 5 + (int) (puntosPerdedor*0.2);
                break;
            case 10: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2)*2;
                break;
            case 11: puntosGanadosFinalPartida = puntosGanador + 10 + (int) (puntosPerdedor*0.2);
                break;
            case 12: puntosGanadosFinalPartida = puntosGanador + 20 + (int) (puntosPerdedor*0.2);
                break;
            case 13: puntosGanadosFinalPartida = puntosGanador + 25 + (int) (puntosPerdedor*0.2);
                break;
            case 14: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2)*3;
                break;
            case 15: puntosGanadosFinalPartida = puntosGanador + 50 + (int) (puntosPerdedor*0.2);
                break;
            case 16: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2)*2;
                break;
            case 17: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2);
                break;
            case 18: puntosGanadosFinalPartida = puntosGanador + 50 + (int) (puntosPerdedor*0.2);
                break;
            case 19: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2);
                break;
            case 20: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2)*2;
                break;
            case 21: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2);
                break;
            case 22: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2)*3;
                break;
            case 23: puntosGanadosFinalPartida = puntosGanador + 50 + (int) (puntosPerdedor*0.2);
                break;
            case 24: puntosGanadosFinalPartida = puntosGanador + (int) (puntosPerdedor*0.2);
                break;
            case 25: puntosGanadosFinalPartida = puntosGanador + 200 + (int) (puntosPerdedor*0.2);
                break;
        }
        return puntosGanadosFinalPartida;
    }

    public static int Perder (int idEtakemon, int puntosPerdidos){

        int puntosPerdidosFinalPartida = 0;


        switch (idEtakemon){
            case 1: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2))) ;
                break;
            case 2: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 3: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 4: puntosPerdidosFinalPartida = puntosPerdidos;
                break;
            case 5: puntosPerdidosFinalPartida = puntosPerdidos - (int)(puntosPerdidos*0.2)/2;
                break;
            case 6: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 7: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 8: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 9: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 10: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 11: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 12: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 13: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 14: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 15: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 16: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 17: puntosPerdidosFinalPartida = puntosPerdidos - (int)(puntosPerdidos*0.2)/2;
                break;
            case 18: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 19: puntosPerdidosFinalPartida = puntosPerdidos - (int)(puntosPerdidos*0.2)/2;
                break;
            case 20: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 21: puntosPerdidosFinalPartida = puntosPerdidos;
                break;
            case 22: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 23: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
            case 24: puntosPerdidosFinalPartida = puntosPerdidos;
                break;
            case 25: puntosPerdidosFinalPartida = (int) ((puntosPerdidos - (puntosPerdidos*0.2)));
                break;
        }
        return puntosPerdidosFinalPartida;
    }


    public static float getRandomPos(float d)
    {
        Random rand = new Random();
        i++;
        float a;
        //float finalX = (float)(rand.nextFloat() + (d - (0.00001)));
        if(i%2==0)
        a = (float) (Math.random()*0.0005) + d - ((float) (Math.random()*0.0005)) ;
        else
        {
             a =  d - (float)(Math.random()*0.0002 ) + (float) (Math.random()*0.0005);

        }

        return a;
    }
}
