package com.example.pingpongbasico.utilita;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

public class MirrorBitmap {
    public static Bitmap flip(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap src = d;
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return  dst;
    }
}
