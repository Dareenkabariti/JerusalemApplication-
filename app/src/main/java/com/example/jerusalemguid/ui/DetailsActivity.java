package com.example.jerusalemguid.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jerusalemguid.MainActivity;
import com.example.jerusalemguid.R;
import com.example.jerusalemguid.SecondDetailsActivity;

import java.net.URI;

public class DetailsActivity extends AppCompatActivity {
    ImageView second_back_arrow, second_arrow_up;
    TextView second_title, second_subtitle, second_rating_number, second_rating_number2, more_details;
    RatingBar second_ratingbar;
    ImageView imageBackground;
    Animation from_left, from_right, from_bottom;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

       String image  = getIntent().getStringExtra("image");
       String title = getIntent().getStringExtra("title");
       String type = getIntent().getStringExtra("type");
       String description = getIntent().getStringExtra("desc");
       String history = getIntent().getStringExtra("history");
       Double lat1 = getIntent().getDoubleExtra("lat1",0.0);
       Double lat2 = getIntent().getDoubleExtra("lat2",0.0);





        second_back_arrow = findViewById(R.id.second_back_arrow);
        imageBackground = findViewById(R.id.imageBackground);
        second_arrow_up = findViewById(R.id.seconf_arrow_up);
        second_title = findViewById(R.id.second_title);
        second_subtitle = findViewById(R.id.second_subtitle);
        second_rating_number = findViewById(R.id.second_rating_number);
        second_rating_number2 = findViewById(R.id.second_rating_number2);
        more_details = findViewById(R.id.more_details);
        second_ratingbar = findViewById(R.id.second_ratingbar);


        second_title.setText(title);
        second_subtitle.setText(description);
        Glide.with(this).load(image).into(imageBackground);



        second_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        from_right = AnimationUtils.loadAnimation(this, R.anim.anim_from_right);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);

        second_back_arrow.setAnimation(from_left);
        second_title.setAnimation(from_right);
        second_subtitle.setAnimation(from_right);
        second_ratingbar.setAnimation(from_left);
        second_rating_number.setAnimation(from_right);
        second_rating_number2.setAnimation(from_right);
        second_arrow_up.setAnimation(from_bottom);
        more_details.setAnimation(from_bottom);

        second_arrow_up.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, SecondDetailsActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("image",image);
                intent.putExtra("desc",description);
                intent.putExtra("history",history);
                intent.putExtra("type",type);
                intent.putExtra("lat1",lat1);
                intent.putExtra("lat2",lat2);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(second_arrow_up, "background_image_transition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DetailsActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }
}