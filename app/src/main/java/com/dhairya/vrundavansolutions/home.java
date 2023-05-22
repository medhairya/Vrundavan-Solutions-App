package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class home extends AppCompatActivity {
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       vv=findViewById(R.id.videoView);
       Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.teaser_video);
       vv.setVideoURI(videoUri);


    Button paperboat1 = findViewById(R.id.paperboat_logo);
    paperboat1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        Intent intent = new Intent(home.this, paperboat.class);
        startActivity(intent);
    }
    });



    }
}