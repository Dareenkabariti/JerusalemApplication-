package com.example.jerusalemguid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media2.exoplayer.external.util.Util;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.jerusalemguid.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class Activity_Player extends AppCompatActivity {
    String videoURL= null;
    PlayerView playerView;
    SimpleExoPlayer player;
    private  boolean playWhenReady = true;
    private  int currentWindow = 0;
    private  long playBackPosition = 0;
    float currentVolume ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        playerView = findViewById(R.id.playerView);
        videoURL = getIntent().getStringExtra("url");
    }

    public void initVideo(){
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        Uri uri = Uri.parse(videoURL);
        DataSource.Factory dataSource = new DefaultDataSourceFactory(this,"exoplayer-codelab");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSource).createMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow,playBackPosition);

        player.prepare(mediaSource,false,false);

    }

    public void releaseVideo(){
        if (player !=null){
            playWhenReady= player.getPlayWhenReady();
            playBackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initVideo();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseVideo();
    }
}