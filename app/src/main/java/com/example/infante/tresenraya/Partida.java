package com.example.infante.tresenraya;


import android.util.Log;

import java.util.Random;

/**
 * Created by infante on 2/6/2018.
 */

public class Partida {
    private static final String TAG = "PARTIDA";

    public Partida(int dificultad){
        this.dificultad=dificultad;
        jugador=1;
        casillas = new int[9];
        for (int i=0; i<9;i++){//rellenar casillas del array
            casillas[i]=0;
        }
    }

    public int dosEnraya(int jugador_en_turno){
        Log.d(TAG, "dosEnRaya");
        int casilla= -1;
        int cuantas_lleva = 0;
        //Recorrer el array COMBINACIONES
        for (int i=0; i<COMBINACIONES.length; i++){
            for (int pos : COMBINACIONES[i]){
                if (casillas[pos]==jugador_en_turno){
                    cuantas_lleva++;
                }
                if (casillas[pos]==0){
                    casilla=pos;
                }
            }
            if (cuantas_lleva==2 && casilla!=-1){
                return  casilla;
            }
            casilla = -1;
            cuantas_lleva = 0;
        }

        return -1;
    }

    public int ia(){
        Log.d(TAG, "IA");
        int casilla;
        casilla=dosEnraya(2);
        if (casilla!=-1){
            return casilla;
        }
        if (dificultad>0){
            casilla=dosEnraya(1);
            if (casilla!=-1){
                return casilla;
            }
        }
        if (dificultad==2){
            if (casillas[0]==0){
                return 0;
            }
            if (casillas[2]==0){
                return 2;
            }
            if (casillas[6]==0){
                return 6;
            }
            if (casillas[8]==0){
                return 8;
            }
        }
        Random casilla_azar=new Random();
        casilla=casilla_azar.nextInt(9);
        return  casilla;
    }

    public int turno (){
        Log.d(TAG, "turno");
        boolean emapte = true;
        boolean ult_movimiento = true;
        for (int i=0;i<COMBINACIONES.length;i++) {
            for (int pos:COMBINACIONES[i]) {
                //imprime las c0ombinaciones marcadas del jugador
               System.out.println(pos + " " + casillas[pos]);
               if (casillas[pos]!=jugador){
                   ult_movimiento=false;
               }
               if(casillas[pos]==0){
                   emapte=false;
               }
            }//cierre de for anidado
            System.out.println("*********************");
//            System.out.println(jugador);
            if (ult_movimiento){
                return jugador;
            }
            ult_movimiento=true;
        }
        if(emapte){
            return 3;
        }
        jugador ++;
        if(jugador>2){
            jugador=1;
        }
        return 0;
    }

    public boolean comprueba_casilla (int casilla){//para comprobar si la casilla es libre
        Log.d(TAG, "comprueba casilla");
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
