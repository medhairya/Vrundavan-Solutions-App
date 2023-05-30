package com.dhairya.vrundavansolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class loginpage extends AppCompatActivity {

    TextView textView;
    RelativeLayout buttonLayout;
    TextView buttonText;
    LottieAnimationView buttonAnimation;
    public static final int TIMER = 2000;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        textView= findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginpage.this, registrationpage.class);
                startActivity(intent);
            }
        });

    buttonLayout = findViewById(R.id.registerButton);
    buttonText = findViewById(R.id.button_text);
    buttonAnimation = findViewById(R.id.button_animation);

    buttonLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonAnimation.setVisibility(View.VISIBLE);
            buttonAnimation.playAnimation();
            buttonText.setVisibility(View.GONE);
            new Handler().postDelayed(this::resetButton, TIMER);
        }
        private void resetButton() {
            //make Lottie Animation
            buttonAnimation.pauseAnimation();
            buttonAnimation.setVisibility(View.GONE);
            buttonText.setVisibility(View.VISIBLE);
            //start new activity from bellow
            startActivity(new Intent(getApplicationContext(),home.class));

        }

    });


    }
}