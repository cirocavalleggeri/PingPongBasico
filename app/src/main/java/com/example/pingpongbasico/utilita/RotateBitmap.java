package com.example.pingpongbasico.utilita;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class RotateBitmap {
    public static Bitmap ruotaBitmap(Bitmap bitmap,int width,int height,int gradi){
        Matrix matrix = new Matrix();

        matrix.postRotate(gradi);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);

        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        return rotatedBitmap;
    }
}
