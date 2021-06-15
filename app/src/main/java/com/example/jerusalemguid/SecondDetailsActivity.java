package com.example.jerusalemguid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jerusalemguid.ui.DHelper.DBHelper;
import com.example.jerusalemguid.ui.MapsActivity;
import com.example.jerusalemguid.ui.models.Place;

import java.util.ArrayList;
import java.util.List;

import top.defaults.colorpicker.ColorPickerPopup;

public class SecondDetailsActivity extends AppCompatActivity {
    ImageView down_arrow, image1, star;
    ScrollView third_scrollview;
    Animation from_bottom;
    DBHelper DB;
    TextView title1, description1, history1, type1, about, type2, h1;

    ImageView color;
    private List<Place> placesList;
    String image = null;
    String title = null;
    String type = null;
    String description = null;
    String history = null;
    Double lat1 = null;

    Double lat2 = null;
    int isFav = 0;
    Button roadmap;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_second_details);


        placesList = new ArrayList<>();
        DB = new DBHelper(this);

        image = getIntent().getStringExtra("image");
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        description = getIntent().getStringExtra("desc");
        history = getIntent().getStringExtra("history");
        lat1 = getIntent().getDoubleExtra("lat1", 0.0);
        lat2 = getIntent().getDoubleExtra("lat2", 0.0);

        down_arrow = findViewById(R.id.down_arrow);
        title1 = findViewById(R.id.third_title);
        description1 = findViewById(R.id.about_text);
        history1 = findViewById(R.id.type_of_view_text);
        type1 = findViewById(R.id.venue_type_text);
        image1 = findViewById(R.id.header_background);
        star = findViewById(R.id.star);
        color = findViewById(R.id.color);
        about = findViewById(R.id.about);
        type2 = findViewById(R.id.venue_type);
        h1 = findViewById(R.id.type_of_view);
        roadmap = findViewById(R.id.roadmapButton);

        third_scrollview = findViewById(R.id.third_scrillview);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);
        down_arrow.setAnimation(from_bottom);
        third_scrollview.setAnimation(from_bottom);


        getData();

        title1.setText(title);
        description1.setText(description);
        history1.setText(history);
        type1.setText(type);
        Glide.with(this).load(image).into(image1);


        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //on a button click , open color picker dialog
                new ColorPickerPopup.Builder(SecondDetailsActivity.this).initialColor(Color.RED) //by default red color is selected
                        .enableBrightness(true).enableAlpha(true).okTitle("Choose")
                        .cancelTitle("Cancel").showIndicator(true)
                        .showValue(true).build().show(v, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        // this method call , when user selected color..
                        title1.setTextColor(color);
                        description1.setTextColor(color);
                        history1.setTextColor(color);
                        type1.setTextColor(color);
                        about.setTextColor(color);
                        type2.setTextColor(color);
                        h1.setTextColor(color);


                    }

                    @Override
                    public void onColor(int color, boolean fromUser) {
                        // this method call , when user selecting color..
                        title1.setTextColor(color);
                        description1.setTextColor(color);
                        history1.setTextColor(color);
                        type1.setTextColor(color);
                        about.setTextColor(color);
                        type2.setTextColor(color);
                        h1.setTextColor(color);
                    }
                });
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
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("mm", "star");
                Log.e("update", "update");


                Boolean checkinsertdata = DB.insertFavPlace(title, description, image, history);
                if (isFav == 0) {
                    if (checkinsertdata == true) {
                        //   Toast.makeText(SecondDetailsActivity.this,"new place added to favorite ",Toast.LENGTH_LONG).show();
                    } else {
                        //   Toast.makeText(SecondDetailsActivity.this,"faild to added to favorite ",Toast.LENGTH_LONG).show();
                        return;
                    }
                    star.setImageResource(R.drawable.ic_baseline_star_yellow);

                    isFav = 1;
                } else if (isFav == 1) {
                    Log.e("delete", "delete");
                    Boolean checkupdeletedata = DB.deleteData(title);
                    if (checkupdeletedata == true) {
                        //   Toast.makeText(SecondDetailsActivity.this,"new place deleted from favorite ",Toast.LENGTH_LONG).show();
                    } else {
                        //    Toast.makeText(SecondDetailsActivity.this,"faild to delete ",Toast.LENGTH_LONG).show();
                        return;
                    }
                    star.setImageResource(R.drawable.ic_baseline_star_24);

                    isFav = 0;
                }

            }
        });

        roadmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondDetailsActivity.this, MapsActivity.class);
                intent.putExtra("lat1", lat1);
                intent.putExtra("lat2", lat2);
                intent.putExtra("title", title);

                startActivity(intent);
            }
        });
        down_arrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondDetailsActivity.this, MainActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(down_arrow, "background_image_transition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SecondDetailsActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }

    private void getData() {
        Cursor res = DB.getData();
        if (res.getCount() == 0) {
            //   Toast.makeText(this,"No entry Exists",Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            Place place = new Place(res.getString(0), res.getString(1), res.getString(2), res.getString(3));


            if (res.getString(0).equals(title)) {

                star.setImageResource(R.drawable.ic_baseline_star_yellow);
                Log.e("yes1", res.getString(0));
                placesList.add(place);
                isFav = 1;
                break;
            } else {
                star.setImageResource(R.drawable.ic_baseline_star_24);
                Log.e("yes2", res.getString(0));

                isFav = 0;
            }

        }


    }


}