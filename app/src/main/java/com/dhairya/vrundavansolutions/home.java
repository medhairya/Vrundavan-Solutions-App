package com.dhairya.vrundavansolutions;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;


public class home extends AppCompatActivity {

    public ViewFlipper flipper=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        int[] iamDhairya ={R.drawable.bagira1,R.drawable.bagira2,R.drawable.bagira3,R.drawable.bagira4,R.drawable.bagira5,R.drawable.bagira6};
        flipper= findViewById(R.id.flup);

        for (int j : iamDhairya) {
            showimage(j);
        }

    }

    public void showimage(int img){
        ImageView imageview=new ImageView(this);
        imageview.setBackgroundResource(img);


        flipper.addView(imageview);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this,android.R.anim.slide_in_left);
        flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
}