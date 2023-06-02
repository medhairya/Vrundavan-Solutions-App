package com.dhairya.vrundavansolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class paperboat extends AppCompatActivity {

    public ViewFlipper flipper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paperboat);


        //profile
        Button profilebtn = findViewById(R.id.profile_paperboat);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(paperboat.this, profile.class);
                startActivity(profile_intent);
            }
        });


        int[] imgs = { R.drawable.bagira3, R.drawable.bagira6,R.drawable.bagira7, R.drawable.bagira8,R.drawable.bagira9,R.drawable.bagira10,R.drawable.bagira11};
        flipper = findViewById(R.id.flup);

        for (int j : imgs) {
            showimage(j);
        }



    }

    private void showimage(int img) {
        ImageView imageview=new ImageView(this);
        imageview.setBackgroundResource(img);


        flipper.addView(imageview);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this,android.R.anim.slide_in_left);
        flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
}