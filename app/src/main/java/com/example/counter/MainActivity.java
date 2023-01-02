package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {

    Button incrementButton;
    Button decrementButton;
    TextView counter;
    SwitchMaterial vibrationSwitch;
    SwitchMaterial soundSwitch;

    int currentCount=0;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        counter = findViewById(R.id.counter);
        incrementButton = findViewById(R.id.increment);
        decrementButton = findViewById(R.id.decrement);
        counter.setText(String.valueOf(currentCount));
        soundSwitch = findViewById(R.id.soundToggle);
        vibrationSwitch = findViewById(R.id.vibrationToggle);
        soundSwitch.setChecked(false);
        vibrationSwitch.setChecked(false);
    }

    public void increment(View view) {
        Log.i("before increment",String.valueOf(currentCount));
        Log.i("soundSwitch value",String.valueOf(soundSwitch.isChecked()));
        Log.i("vibrateSwitch value",String.valueOf(vibrationSwitch.isChecked()));
        vibrate(10);
        sound();
        currentCount=currentCount+1;
        counter.setText(String.valueOf(currentCount));
        Log.i("after increment",String.valueOf(currentCount));
    }

    private void sound() {
        if(soundSwitch.isChecked()) {
            MediaPlayer mp;
            mp = MediaPlayer.create(context, R.raw.tic);
            mp.setOnCompletionListener(mp1 -> {
                mp1.reset();
                mp1.release();
            });
            mp.start();
        }
    }

    private void vibrate(long ms) {
        if(vibrationSwitch.isChecked()) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(ms);
        }
    }

    public void decrement(View view) {
        Log.i("before decrement",String.valueOf(currentCount));
        if(currentCount>0) {
            sound();
            vibrate(10);
            currentCount = currentCount - 1;
            counter.setText(String.valueOf(currentCount));
        }
        Log.i("after decrement",String.valueOf(currentCount));
    }

    public void resetCounter(View view) {
        sound();
        vibrate(50);
        Log.i("before reset",String.valueOf(currentCount));
        currentCount = 0;
        counter.setText(String.valueOf(currentCount));
        Log.i("after reset",String.valueOf(currentCount));
    }
}