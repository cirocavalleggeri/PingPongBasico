package com.example.pingpongbasico;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import com.example.pingpongbasico.utilita.ConvertiLunghezzaStringInPixel;
import com.example.pingpongbasico.utilita.EstraiBitmap;
import com.example.pingpongbasico.utilita.MacchinaDaScrivereVecchia;
import com.example.pingpongbasico.utilita.MergeBitmap;
import com.example.pingpongbasico.utilita.MirrorBitmap;
import com.example.pingpongbasico.utilita.RotateBitmap;
import com.example.pingpongbasico.utilita.SalvaBitmapToPNG;
import com.example.pingpongbasico.utilita.SplitBitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GameView extends View {

    Context context;
    float ballX, ballY;
    Velocity velocity = new Velocity(25, 32);
    Handler handler;
    final long UPDATE_MILLIS = 60;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float TEXT_SIZE = 120;
    float paddleX, paddleY;
    float oldX, oldPaddleX;
    int points = 0;
    int life = 3;
    Bitmap ball, paddle;
    int dWidth, dHeight;
    MediaPlayer mpHit, mpMiss;
    Random random;
    SharedPreferences sharedPreferences;
    Boolean audioState;
    Bitmap bird;
    Bitmap ali;
    Bitmap aligrandi;
    Bitmap aligrandi_mirror;
    Bitmap[] contenitore_ali;
    Bitmap coppia_di_ali;
    MacchinaDaScrivereVecchia macchinaDaScrivereVecchia;
    boolean unsolopassaggio;
    public GameView(Context context) {
        super(context);
        this.context = context;
        contenitore_ali=new Bitmap[4];
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
       bird= BitmapFactory.decodeResource(getResources(), R.drawable.bird);
       ali=BitmapFactory.decodeResource(getResources(), R.drawable.wing);
       aligrandi=scaleImage(ali);
       aligrandi_mirror= MirrorBitmap.flip(aligrandi);
       contenitore_ali= SplitBitmap.splitBitmap(aligrandi_mirror);
       coppia_di_ali= MergeBitmap.mergeBitmap(contenitore_ali[0],contenitore_ali[0]);
       // coppia_di_ali= MergeBitmap.mergeToCenter(contenitore_ali[0],contenitore_ali[1]);
        SalvaBitmapToPNG.salvaBitmapInPng(coppia_di_ali);

        paddle = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        mpHit = MediaPlayer.create(context, R.raw.hit);
        mpMiss = MediaPlayer.create(context, R.raw.miss);

        textPaint.setColor(Color.RED);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        healthPaint.setColor(Color.GREEN);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        random = new Random();
        ballX = random.nextInt(dWidth);
        paddleY = (dHeight * 4) / 5;
        paddleX = dWidth / 2 - paddle.getWidth() /2;
        sharedPreferences = context.getSharedPreferences("my_pref", 0);
        audioState = sharedPreferences.getBoolean("audioState", true);
        ConvertiLunghezzaStringInPixel.convertiStringLenToPixel("Vel:");
        unsolopassaggio=true;
       macchinaDaScrivereVecchia=new MacchinaDaScrivereVecchia((Activity)getContext());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        super.onDraw(canvas);




        canvas.drawColor(Color.BLACK);
        ballX += velocity.getX();
        ballY += velocity.getY();
        if((ballX >= dWidth - ball.getWidth()) || ballX <= 0){
            velocity.setX(velocity.getX() * -1);
        }
        if(ballY <= 0){
            velocity.setY(velocity.getY() * -1);
        }
        if(ballY > paddleY + paddle.getHeight()){
            ballX = 1 + random.nextInt(dWidth - ball.getWidth() -1);
            ballY = 0;
            if(mpMiss != null && audioState){
                mpMiss.start();
            }
            velocity.setX(xVelocity());
            velocity.setY(32);
            life--;
            if(life == 0){
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("points", points);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        }
        if(((ballX+ball.getWidth()) >= paddleX)
                && (ballX <= paddleX + paddle.getWidth())
                && (ballY + ball.getHeight() >= paddleY)
                && (ballY + ball.getHeight() <= paddleY + paddle.getHeight())){
            if(mpHit != null && audioState){
                mpHit.start();
            }
            velocity.setX(velocity.getX() + 1);
            velocity.setY((velocity.getY() + 1) * -1);
            points++;
        }
        canvas.drawBitmap(ball, ballX, ballY, null);
        canvas.drawBitmap(paddle, paddleX, paddleY, null);
        canvas.drawBitmap(contenitore_ali[0], 250, 550, null);
        canvas.drawBitmap(coppia_di_ali, 150, 150, null);
        canvas.drawBitmap(RotateBitmap.ruotaBitmap(contenitore_ali[0],250,250,15), 300, 500, null);
       // EstraiBitmap.drawPixmap(canvas,bird,100,400,1000,1123,794,1123,123,123);

        //canvas.drawBitmap(ali, 500, 500, null);

        //EstraiBitmap.drawPixmap(canvas,aligrandi,100,500,0,0,250,250,250,250);
       // EstraiBitmap.drawPixmap(canvas,aligrandi_mirror,600,500,250,0,250,250,250,250);
       // EstraiBitmap.drawPixmap(canvas, aligrandi_mirror,650,500,250,0,250,250,250,250);
       // canvas.drawBitmap(aligrandi_mirror, 500, 800, null);
        canvas.drawText(""+points, 20, TEXT_SIZE, textPaint);
        if(life == 2){
            healthPaint.setColor(Color.YELLOW);
        }else if(life == 1){
            healthPaint.setColor(Color.RED);
        }
        canvas.drawRect(dWidth-200, 30,dWidth - 200 + 60*life, 80, healthPaint);
        if (unsolopassaggio){
                macchinaDaScrivereVecchia.update("muovi la barra rossa con il dito  - e cerca di prendere la pallina",dWidth,canvas);

                unsolopassaggio=true;
        }
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }
    private int xVelocity() {
        int[] values = {-35, -30, -25, 25, 30, 35};
        int index = random.nextInt(6);
        return values[index];
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if(touchY >= paddleY){
            int action = event.getAction();
            if(action == MotionEvent.ACTION_DOWN){
                oldX = event.getX();
                oldPaddleX = paddleX;
            }
            if(action == MotionEvent.ACTION_MOVE){
                float shift = oldX - touchX;
                float newPaddleX = oldPaddleX - shift;
                if(newPaddleX <= 0)
                    paddleX = 0;
                else if(newPaddleX >= dWidth - paddle.getWidth())
                    paddleX = dWidth - paddle.getWidth();
                else
                    paddleX = newPaddleX;
            }
        }
        return true;
    }
    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = bitmap.getWidth() / bitmap.getHeight();
        /*
        We'll multiply widthHeightRatio with screenHeight to get
        scaled width of the bitmap. Then call createScaledBitmap()
        to create a new bitmap, scaled from an existing bitmap, when possible.
         */
        int backgroundScaledWidth = (int) widthHeightRatio * 500;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth,500, false);
    }

}
