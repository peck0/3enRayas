package com.example.infante.tresenraya;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    private View vista;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciamos el array casilla para identifaras y almacenarla

        CASILLAS= new int[9];

        CASILLAS[0]=R.id.a1;
        CASILLAS[1]=R.id.a2;
        CASILLAS[2]=R.id.a3;
        CASILLAS[3]=R.id.b1;
        CASILLAS[4]=R.id.b2;
        CASILLAS[5]=R.id.b3;
        CASILLAS[6]=R.id.c1;
        CASILLAS[7]=R.id.c2;
        CASILLAS[8]=R.id.c3;
    }

    public void aJugar(View vista){

        ImageView imagen;
        for(int cadaCasilla:CASILLAS){
            imagen=(ImageView)findViewById((cadaCasilla));
            imagen.setImageResource(R.drawable.casilla);
        }

        jugadores=1;
        if(vista.getId()==R.id.dosjug){
            jugadores=2;
        }

        RadioGroup configDificultad=(RadioGroup)findViewById(R.id.configID);

        int id=configDificultad.getCheckedRadioButtonId();
        int dificultad=0;
        if(id==R.id.normal){
            dificultad=1;
        } else if (id == R.id.imposible) {
            dificultad=2;
        }

        partida=new Partida(dificultad);

        ((Button)findViewById(R.id.unjug)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.configID)).setAlpha(0);
        ((Button)findViewById(R.id.dosjug)).setEnabled(false);


    }//Fin del metodo aJugar

    public void toque(View vista){
        if (partida==null){
            return;
        }
        int casilla=0;

        for(int i=0;i<9;i++){
            if(CASILLAS[i]==vista.getId()){
                casilla=i;
                break;
            }
        }
        if(partida.comprueba_casilla(casilla)==false){
            return;
        }
        marca(casilla);
        int resultado = partida.turno();
        if (resultado>0){
            termina(resultado);
            return;
        }
        casilla=partida.ia();
        while(partida.comprueba_casilla(casilla)!=true){
            casilla=partida.ia();
        }
        marca(casilla);
        resultado = partida.turno();
        if (resultado>0){
            termina(resultado);
        }
        /*Toast toast=Toast.makeText(this,"has pulsado la casilla " + (casilla+1), Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER,0,0);
        toast.show();*/
    }

    private void termina(int resultado){
        String mensaje;
        if (resultado==1){
            mensaje="Gana jugador 1";
        }else if(resultado==2){
            mensaje="Gana jugado 2";
        }else{
            mensaje="Empate";
        }
        Toast toast=Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        partida=null;

        findViewById(R.id.unjug).setEnabled(true);
        findViewById(R.id.dosjug).setEnabled(true);
        findViewById(R.id.configID).setAlpha(1);

    }

    private void marca (int casilla){//metodo para marcar la casilla
        ImageView imagen;
        imagen=(ImageView)findViewById(CASILLAS[casilla]);
        if(partida.jugador==1){
            imagen.setImageResource(R.drawable.circulo);
        }else{
            imagen.setImageResource(R.drawable.aspa);
        }
    }//fin metodo marca

    private int jugadores;
    private int[] CASILLAS;
    private Partida partida;
}//Fin de la clase
