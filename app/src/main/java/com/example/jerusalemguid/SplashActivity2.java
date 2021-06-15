package com.example.jerusalemguid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class SplashActivity2 extends AppCompatActivity {

    VideoView videoView;
    Button button;
    ImageView imageView;
    MediaPlayer mediaPlayer1;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_splash2);
        mediaPlayer1 = MediaPlayer.create(this, R.raw.fyrouz);
        button = findViewById(R.id.nextActivity);
        videoView = findViewById(R.id.viewVideo);
        imageView = findViewById(R.id.image);




        String path = "android.resource://com.example.jerusalemguid/"+R.raw.videoplayback;

        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mediaPlayer1.start();

                imageView.setVisibility(View.GONE);
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mediaPlayer1.start();
            }
        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.release();
                startActivity(new Intent(SplashActivity2.this,MainActivity.class));
            }
        });

    }

}