package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView textView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView = findViewById(R.id.splashView);
        textView = findViewById(R.id.textView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.animate().setStartDelay(2000).setDuration(2500);
                lottieAnimationView.animate().setDuration(1000);
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 6000);
    }
}