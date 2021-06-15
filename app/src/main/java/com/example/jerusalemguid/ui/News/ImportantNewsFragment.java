package com.example.jerusalemguid.ui.News;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jerusalemguid.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;


public class ImportantNewsFragment extends Fragment {
    String videoURL= "https://firebasestorage.googleapis.com/v0/b/jerusalemguid.appspot.com/o/videos%2F%D9%86%D8%A8%D9%8A%D9%84%20%D8%A7%D9%84%D9%83%D8%B1%D8%AF%20%D9%81%D9%84%D8%B3%D8%B7%D9%8A%D9%86%D9%8A%20%D9%8A%D8%AD%D8%A7%D9%88%D9%84%20%D8%A7%D9%84%D9%85%D8%B3%D8%AA%D9%88%D8%B7%D9%86%D9%88%D9%86%20%D8%A7%D9%84%D8%A7%D8%B3%D8%AA%D9%8A%D9%84%D8%A7%D8%A1%20%D8%B9%D9%84%D9%89%20%D8%A8%D9%8A%D8%AA%D9%87%20%D8%A8%D8%AD%D9%8A%20%D8%A7%D9%84%D8%B4%D9%8A%D8%AE%20%D8%AC%D8%B1%D8%A7%D8%AD%20%D8%A8%D8%A7%D9%84%D9%82%D8%AF%D8%B3%20%D8%A7%D9%84%D9%85%D8%AD%D8%AA%D9%84%D8%A9%20%23%D9%82%D8%B5%D8%A9_%D8%A5%D9%86%D8%B3%D8%A7%D9%86.mp4?alt=media&token=87991f4b-1b70-4622-bb15-3056b6d46e20";
    PlayerView playerView;
    SimpleExoPlayer player;
    private  boolean playWhenReady = true;
    private  int currentWindow = 0;
    private  long playBackPosition = 0;
    BarChart barChart;
    TextView textView;
    BarDataSet barDataSet1;
    private Switch aSwitch;
    ArrayList barEntries;
    String[] days = new String[]{"2014","2015","2016","2017","2018","2019"};



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_important_news, container, false);

        playerView = root.findViewById(R.id.video_view);
        barChart = root.findViewById(R.id.idBarChart);
        container = root.findViewById(R.id.constraint);
        container.setRotationY(180);
        textView = root.findViewById(R.id.textViewLink);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        barDataSet1 = new BarDataSet(getBarEntriesOne(), "First Set");
        barDataSet1.setColor(getContext().getResources().getColor(R.color.purple_200));

        BarData data = new BarData(barDataSet1);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));

        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(6);

        data.setBarWidth(0.15f);
        barChart.getXAxis().setAxisMinimum(0);
        YAxis left = barChart.getAxisLeft();
        left.setLabelCount(6);
        left.setAxisMaxValue(80f);
        left.setAxisMinValue(0f);


        YAxis right  = barChart.getAxisRight();
        right.setLabelCount(6);
        right.setAxisMaxValue(80f);
        right.setAxisMinValue(0f);

        barChart.invalidate();
        return root;

    }


    @SuppressLint("RestrictedApi")
    public void initVideo(){
        player = ExoPlayerFactory.newSimpleInstance(getContext());
        playerView.setPlayer( player);
        Uri uri = Uri.parse(videoURL);
        DataSource.Factory dataSource = new DefaultDataSourceFactory(getContext(),"exoplayer-codelab");

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSource).createMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow,playBackPosition);
        player.prepare(mediaSource,false,false);
    }
    @SuppressLint("RestrictedApi")
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
    public void onStart() {
        super.onStart();
        initVideo();
    }


    @Override
    public  void onStop() {
        super.onStop();
        releaseVideo();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseVideo();
    }

    private ArrayList<BarEntry> getBarEntriesOne() {

        barEntries = new ArrayList<>();


        barEntries.add(new BarEntry(1f, 7));
        barEntries.add(new BarEntry(3f, 35));
        barEntries.add(new BarEntry(4f, 30));
        barEntries.add(new BarEntry(5f, 31));
        barEntries.add(new BarEntry(6f, 48));
        barEntries.add(new BarEntry(2f, 20));
        return barEntries;
    }

    private ArrayList<BarEntry> getBarEntriesTwo() {

        barEntries = new ArrayList<>();


        barEntries.add(new BarEntry(1f, 80));
        barEntries.add(new BarEntry(2f, 60));
        barEntries.add(new BarEntry(3f, 40));
        barEntries.add(new BarEntry(4f, 20));
        barEntries.add(new BarEntry(5f, 10));
        barEntries.add(new BarEntry(6f, 0));
        return barEntries;
    }
    private void reset(){

    }
}