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

    public MacchinaDaScrivereVecchia(Context context)  {

        this.tastoMacchinaDaScrivere=MediaPlayer.create(context, R.raw.tasto);
        ritornocarrello=MediaPlayer.create(context, R.raw.ritornocarrello);


        mPaint = new Paint();
        i=1;
        rigaY=360;
        numerorighe=0;
        righe=new String[100];

    }
    public void calcolaLunghezzaRiga(String testo ,int massimospazio,Canvas canvas){

     float lunghezzainpixel= ConvertiLunghezzaStringInPixel.convertiStringLenToPixel(testo);
     if(lunghezzainpixel>massimospazio){
         String testo1=testo.substring(0,testo.length()/2);
         String testo2=testo.substring(testo1.length(),testo.length());
         update(testo,canvas);
        // update(testo1,canvas);
      }
    }
    public void update(String testodascrivere,Canvas canvas){
        int lunghezzatesto=testodascrivere.length();

        if(i<=lunghezzatesto){

                String tasto=testodascrivere.substring(0,i+1);
                Log.d("TASTO","tasto:"+tasto);
                disegnatasto(tasto,canvas,false);
                this.tastoMacchinaDaScrivere.start();
                i+=1;
            }




          if(i==lunghezzatesto){
              ritornocarrello.start();
              i++;



          }
          if(i>lunghezzatesto){
              disegnatasto(testodascrivere,canvas,true);
              i=1;
              numerorighe=numerorighe+1;

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

           if(aumentariga){rigaY+=64;}
               righe[numerorighe]=tasto;
               int ciclo=numerorighe;
               int rigaciclo=rigaY;
               while (ciclo>0){
                   rigaciclo-=64;

                   canvas.drawText(righe[ciclo], 20, rigaciclo, mPaint);
                   ciclo-=1;
                   if(ciclo==-1){ciclo=0;}
               }


          //canvas.drawText(tasto, 20, rigaY, mPaint);



       }
}
