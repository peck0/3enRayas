package com.example.infante.tresenraya;


import java.util.Random;

/**
 * Created by infante on 2/6/2018.
 */

public class Partida {

    public Partida(int dificultad){
        this.dificultad=dificultad;
        jugador=1;
        casillas = new int[9];
        for (int i=0; i<9;i++){//rellenar casillas del array
            casillas[i]=0;
        }
    }

    public int ia(){
        int casilla;
        Random casilla_azar=new Random();
        casilla=casilla_azar.nextInt(9);
        return  casilla;
    }

    public void turno (){
        for (int i=0;i<COMBINACIONES.length;i++) {

            for (int pos:COMBINACIONES[i]) {
                System.out.println("Valor en posicion " + i + " " + casillas[pos]);
            }
        }
        jugador ++;
        if(jugador>2){
            jugador=1;
        }
    }

    public boolean comprueba_casilla (int casilla){//para comprobar si la casilla es libre
        if (casillas[casilla]!=0){
            return false;
        }else{
            casillas[casilla]=jugador;
        }
        return true;
    }
    public final int dificultad;
    public int jugador;
    private int casillas[];
    private final int [][] COMBINACIONES = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
}
