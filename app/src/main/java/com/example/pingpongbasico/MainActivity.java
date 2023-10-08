package com.example.pingpongbasico;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.pingpongbasico.permessi.Permessidiscrittura;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean audioState;
    ImageButton ibAudio;
    Permessidiscrittura chiedipermessi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chiedipermessi=new Permessidiscrittura();
        chiedipermessi.verifyStoragePermissions(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ibAudio = findViewById(R.id.ibAudio);
        sharedPreferences = getSharedPreferences("my_pref",0);
        audioState = sharedPreferences.getBoolean("audioState", true);
        if(audioState){
            ibAudio.setImageResource(R.drawable.audio_on);
        }else{
            ibAudio.setImageResource(R.drawable.audio_off);
        }
    }

    public void startGame(View view) {
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }

    public void audioPref(View view) {
        if(audioState){
            audioState = false;
            ibAudio.setImageResource(R.drawable.audio_off);
        }else{
            audioState = true;
            ibAudio.setImageResource(R.drawable.audio_on);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("audioState", audioState);
        editor.commit();
    }
}