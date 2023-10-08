package com.example.pingpongbasico.utilita;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class ConvertiLunghezzaStringInPixel {
 public static float   convertiStringLenToPixel(String text){
        Paint mPaint;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(64);
        mPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
// ...
        float w = mPaint.measureText(text, 0, text.length());
        Log.d("TESTO","la stringa passata:"+text+" misura in pixel:"+w);
        return w;
    }
}
