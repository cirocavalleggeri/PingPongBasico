package com.example.pingpongbasico.utilita;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MergeBitmap {
    public static Bitmap mergeBitmap(Bitmap firstImage, Bitmap secondImage){
        Bitmap result = Bitmap.createBitmap(250, 250, firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 100, 1000, null);
        canvas.drawBitmap(secondImage, 150, 150, null);
        return result;
    }
    public static Bitmap mergeToCenter(Bitmap back, Bitmap front) {
        Bitmap result = Bitmap.createBitmap(back.getWidth(), back.getHeight(), back.getConfig());
        Canvas canvas = new Canvas(result);
        int widthBack = back.getWidth();
        int widthFront = front.getWidth();
        float move = (widthBack - widthFront) / 2;
        canvas.drawBitmap(back, 0f, 0f, null);
        canvas.drawBitmap(front, move, move, null);
        return result;
    }
}
