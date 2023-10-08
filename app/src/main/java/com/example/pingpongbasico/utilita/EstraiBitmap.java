package com.example.pingpongbasico.utilita;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class EstraiBitmap {
    Canvas canvas;




    public static void drawPixmap(Canvas canvas,Bitmap bitmap, int x, int y, int srcX, int srcY,
                           int srcWidth, int srcHeight,int dstWidth,int dstHeight) {

        Rect srcRect = new Rect();
        Rect dstRect = new Rect();

        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + dstWidth - 1;
        dstRect.bottom = y + dstHeight - 1;

       canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
