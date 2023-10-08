package com.example.pingpongbasico.utilita;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.pingpongbasico.R;

public class MacchinaDaScrivereVecchia {
    MediaPlayer  tastoMacchinaDaScrivere;
    MediaPlayer  ritornocarrello;
    Paint mPaint;
    int i;
    int Larghezza_tasto;
    int rigaY;
    int numerorighe;
    String[] righe;
    String tasto1;
    String tasto;
    boolean superatariga;
    int nuovapartenza;
    int lunghezzatesto;
    public MacchinaDaScrivereVecchia(Context context)  {

        this.tastoMacchinaDaScrivere=MediaPlayer.create(context, R.raw.tasto);
        ritornocarrello=MediaPlayer.create(context, R.raw.ritornocarrello);


        mPaint = new Paint();
        i=1;
        rigaY=360;
        numerorighe=0;
        righe=new String[100];
        tasto1="";
        tasto="";
        superatariga=false;
        nuovapartenza=0;
        lunghezzatesto=120;

    }

    public void update(String testodascrivere,int massimospazio,Canvas canvas){

        testodascrivere=testodascrivere.substring(nuovapartenza,testodascrivere.length());

        Log.d("TASTO","testodascrivere "+testodascrivere);
       int lunghezzatesto=testodascrivere.length();

      if (i<=lunghezzatesto){






                              tasto=testodascrivere.substring(0,i+1);


                    Log.d("TASTO","tasto:"+tasto);
                    this.tastoMacchinaDaScrivere.start();
                    if(i==1){
                            Log.d("TASTO","i=1:"+tasto);
                           disegnatasto(tasto,canvas,true);}
                               else {
                            disegnatasto(tasto,canvas,false);
                               }
                      float lunghezzainpixel= ConvertiLunghezzaStringInPixel.convertiStringLenToPixel(testodascrivere);

                           if( testodascrivere.substring(i,i+1).equals("-")){
                               //disegnatasto("?",canvas,true);
                               disegnatasto(" ",canvas,true);
                               Log.d("TASTO",testodascrivere.substring(i,i+1));
                               nuovapartenza=i+2;
                               i=0;
                               ritornocarrello.start();
                           }


                    i+=1;}








          if(i==lunghezzatesto){
              ritornocarrello.start();
              i++;



          }
          if(i>lunghezzatesto){
              disegnatasto(testodascrivere,canvas,false);
              // i=1;


          }


        }


    private void disegnatasto(String tasto,Canvas canvas,boolean aumentariga) {

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(64);
        mPaint.setColor(Color.RED);
        mPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
       float misuraLarghezzaTasto=ConvertiLunghezzaStringInPixel.convertiStringLenToPixel(tasto);
       int larghezzatasto=(int)misuraLarghezzaTasto;

           if(aumentariga){rigaY+=64;
                            numerorighe=numerorighe+1;
                           }
              righe[numerorighe]=tasto;
              // Log.d("TASTO","numerorighe:"+numerorighe);
               int ciclo=numerorighe;
               int rigaciclo=rigaY;
               while (ciclo>0){



                       canvas.drawText(righe[ciclo], 20, rigaciclo, mPaint);
                       rigaciclo-=64;
                       if(rigaciclo<360){rigaciclo=360;}
                        ciclo-=1;

               }


          //canvas.drawText(tasto, 20, rigaY, mPaint);



       }
}
