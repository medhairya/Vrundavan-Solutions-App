package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;


public class home extends AppCompatActivity {
    public ViewFlipper flipper = null;
    Button LogoutBtn,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int[] imgs = { R.drawable.bagira3, R.drawable.bagira6,R.drawable.bagira7, R.drawable.bagira8,R.drawable.bagira9,R.drawable.bagira10,R.drawable.bagira11};
        flipper = findViewById(R.id.flup);

        for (int j : imgs) {
            showimage(j);
        }

        //profile
        profile = findViewById(R.id.profile_bagira);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(home.this, profile.class);
                String sharePhone = getIntent().getStringExtra("Phone");
                profile_intent.putExtra("Phone", sharePhone); // Pass the phoneUser value
                startActivity(profile_intent);
            }
        });

        //logout
        LogoutBtn = findViewById(R.id.logout_button);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();

                Intent intent = new Intent(home.this, loginpage.class);
                startActivity(intent);
            }
        });


        //paperBoat
        Button paperboat1 = findViewById(R.id.paperboat_logo);
        paperboat1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        Intent intent = new Intent(home.this, paperboat.class);
        startActivity(intent);
        }
        });

        //bagira
        Button bagira1 = findViewById(R.id.bagira_logo);
        bagira1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(home.this, bagira.class);
                startActivity(intent);
            }
        });

        //haywards
        Button haywards1 = findViewById(R.id.haywards_logo);
        haywards1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(home.this, haywards.class);
                startActivity(intent);
            }
        });

        //gina
        Button gina1 = findViewById(R.id.gina_soda_logo);
        gina1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(home.this, gina.class);
                startActivity(intent);
            }
        });


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