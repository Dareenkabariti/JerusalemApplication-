package com.example.jerusalemguid.ui.JerusalemToure;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jerusalemguid.R;
import com.example.jerusalemguid.ui.Activity_Player;


public class JerusalemFragment extends Fragment {
    CardView card1, card2, card3, card4;


    TextView link;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.row_video, container, false);
        card1 = root.findViewById(R.id.card);
        card2 = root.findViewById(R.id.card2);
        card3 = root.findViewById(R.id.card3);
        card4 = root.findViewById(R.id.card4);
        link = root.findViewById(R.id.textViewLink2);
        link.setMovementMethod(LinkMovementMethod.getInstance());


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoURL = "https://firebasestorage.googleapis.com/v0/b/jerusalemguid.appspot.com/o/videos%2F%D8%A7%D9%84%D8%B5%D8%AE%D8%B1%D8%A9%20%D8%A7%D9%84%D9%85%D9%8F%D8%B9%D9%84%D9%91%D9%82%D8%A9%20%D9%81%D9%8A%20%D8%A7%D9%84%D9%85%D8%B3%D8%AC%D8%AF%20%D8%A7%D9%84%D8%A7%D9%82%D8%B5%D9%89_Trim.mp4?alt=media&token=56837fe1-baf6-4865-8971-b074752e5256";

                Intent intent = new Intent(getContext(), Activity_Player.class);
                intent.putExtra("url", videoURL);
                startActivity(intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoURL = "https://firebasestorage.googleapis.com/v0/b/jerusalemguid.appspot.com/o/videos%2F%D9%85%D8%B9%D8%A7%D9%84%D9%85%20%D8%A7%D9%84%D9%82%D8%AF%D8%B3..%20%D9%83%D9%86%D9%8A%D8%B3%D8%A9%20%D8%A7%D9%84%D9%82%D9%8A%D8%A7%D9%85%D8%A9%20(1).mp4?alt=media&token=bb39f7e7-ac4a-403d-8f54-936ca993f6ca";
                Intent intent = new Intent(getContext(), Activity_Player.class);
                intent.putExtra("url", videoURL);

                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoURL = "https://firebasestorage.googleapis.com/v0/b/jerusalemguid.appspot.com/o/videos%2F%D9%85%D8%B9%D8%A7%D9%84%D9%85%20%D8%A7%D9%84%D9%82%D8%AF%D8%B3..%20%D8%B3%D9%88%D9%82%20%D8%A7%D9%84%D9%82%D8%B7%D8%A7%D9%86%D9%8A%D9%86.mp4?alt=media&token=809f31d5-74ca-466b-a69c-f81dbd54818c";

                Intent intent = new Intent(getContext(), Activity_Player.class);
                intent.putExtra("url", videoURL);

                startActivity(intent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoURL = "https://firebasestorage.googleapis.com/v0/b/jerusalemguid.appspot.com/o/videos%2F%D8%A7%D9%84%D9%82%D8%AF%D8%B3-%20%D8%AD%D8%A7%D8%A6%D8%B7%20%D8%A7%D9%84%D8%A8%D8%B1%D8%A7%D9%82..%20%D8%A7%D9%84%D8%AC%D8%AF%D8%A7%D8%B1%20%D8%A7%D9%84%D8%BA%D8%B1%D8%A8%D9%8A%20%D9%84%D9%84%D8%A3%D9%82%D8%B5%D9%89%20(1).mp4?alt=media&token=2ce2c0f0-88ab-416e-9d23-7ea753b20b83";
                Intent intent = new Intent(getContext(), Activity_Player.class);
                intent.putExtra("url", videoURL);

                startActivity(intent);
            }
        });

        return root;

    }

}
