package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the status bar to display full-screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Play the video animation
        VideoView videoView = findViewById(R.id.logoanimation);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo_animation);
        videoView.setVideoURI(videoUri);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, loginpage.class);
                        startActivity(intent);
                        finish(); // Call finish to close the current activity
                    }
                }, 1000); // Delay the intent by 1 second (1000 milliseconds)
            }
        });
        videoView.start();
    }
}
