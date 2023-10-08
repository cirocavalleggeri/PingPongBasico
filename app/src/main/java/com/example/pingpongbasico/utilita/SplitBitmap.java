package com.example.pingpongbasico.utilita;

import android.graphics.Bitmap;

public class SplitBitmap {
    public static Bitmap[] splitBitmap(Bitmap src){

            Bitmap[] divided = new Bitmap[4];
            divided[0] = Bitmap.createBitmap(
                    src,
                    0, 0,
                    src.getWidth() / 2, src.getHeight() / 2
            );
           divided[1] = Bitmap.createBitmap(
                    src,
                    src.getWidth() / 2, 0,
                    src.getWidth() / 2, src.getHeight() / 2
            );
           divided[2] = Bitmap.createBitmap(
                    src,
                    0, src.getHeight() / 2,
                    src.getWidth() / 2, src.getHeight() / 2
            );
           divided[3] = Bitmap.createBitmap(
                    src,
                    src.getWidth() / 2, src.getHeight() / 2,
                    src.getWidth() / 2, src.getHeight() / 2
            );
            return divided;

    }
}
