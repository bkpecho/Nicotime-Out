package com.nicotimeout.app.userStart;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nicotimeout.app.R;

public class StartActivity_badEffects extends AppCompatActivity {
    CardView cv1, cv2, cv3, cv4;
    String[] rv_subTitle;
    String[] rv_body1;
    String[] rv_body2;
    String[] rv_body3;
    String[] rv_body4;
    String vid_cigarette;
    String vid_smoking;
    String vid_effects;
    String vid_addiction;
    String img_cigarette;
    String img_smoking;
    String img_effects;
    String img_addiction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_bad_effects);

        rv_subTitle = getResources().getStringArray(R.array.rv_subTitle);
        rv_body1 = getResources().getStringArray(R.array.rv_body1);
        rv_body2 = getResources().getStringArray(R.array.rv_body2);
        rv_body3 = getResources().getStringArray(R.array.rv_body3);
        rv_body4 = getResources().getStringArray(R.array.rv_body4);

        cv1 = findViewById(R.id.cigarette);
        cv2 = findViewById(R.id.cigaretteSmoking);
        cv3 = findViewById(R.id.healthEffects);
        cv4 = findViewById(R.id.addiction);


        vid_cigarette = "android.resource://" + getPackageName() + "/" + R.raw.vid_cigarette;
        vid_smoking = "android.resource://" + getPackageName() + "/" + R.raw.vid_smoking;
        vid_effects = "android.resource://" + getPackageName() + "/" + R.raw.vid_effects;
        vid_addiction = "android.resource://" + getPackageName() + "/" + R.raw.vid_addiction;

        img_cigarette = "android.resource://" + getPackageName() + "/" + R.drawable.vid_cigarette;
        img_smoking = "android.resource://" + getPackageName() + "/" + R.drawable.vid_smoking;
        img_effects = "android.resource://" + getPackageName() + "/" + R.drawable.vid_effects;
        img_addiction = "android.resource://" + getPackageName() + "/" + R.drawable.vid_addiction;

        cv1.setOnClickListener(view -> {
            Intent myIntent = new Intent(StartActivity_badEffects.this, StartActivity_popup.class);
            myIntent.putExtra("title", rv_subTitle[0]);
            myIntent.putExtra("body1", rv_body1[0]);
            myIntent.putExtra("body2", rv_body2[0]);
            myIntent.putExtra("body3", rv_body3[0]);
            myIntent.putExtra("body4", rv_body4[0]);
            myIntent.putExtra("ac_checker", "0");
            myIntent.putExtra("video", vid_cigarette);
            myIntent.putExtra("image", img_cigarette);
            StartActivity_badEffects.this.startActivity(myIntent);
        });
        cv2.setOnClickListener(view -> {
            Intent myIntent = new Intent(StartActivity_badEffects.this, StartActivity_popup.class);
            myIntent.putExtra("title", rv_subTitle[1]);
            myIntent.putExtra("body1", rv_body1[1]);
            myIntent.putExtra("body2", rv_body2[1]);
            myIntent.putExtra("body3", rv_body3[1]);
            myIntent.putExtra("body4", rv_body4[1]);
            myIntent.putExtra("ac_checker", "1");
            myIntent.putExtra("video", vid_smoking);
            myIntent.putExtra("image", img_smoking);
            StartActivity_badEffects.this.startActivity(myIntent);
        });
        cv3.setOnClickListener(view -> {
            Intent myIntent = new Intent(StartActivity_badEffects.this, StartActivity_popup.class);
            myIntent.putExtra("title", rv_subTitle[2]);
            myIntent.putExtra("body1", rv_body1[2]);
            myIntent.putExtra("body2", rv_body2[2]);
            myIntent.putExtra("body3", rv_body3[2]);
            myIntent.putExtra("body4", rv_body4[2]);
            myIntent.putExtra("ac_checker", "2");
            myIntent.putExtra("video", vid_effects);
            myIntent.putExtra("image", img_effects);
            StartActivity_badEffects.this.startActivity(myIntent);
        });
        cv4.setOnClickListener(view -> {
            Intent myIntent = new Intent(StartActivity_badEffects.this, StartActivity_popup.class);
            myIntent.putExtra("title", rv_subTitle[3]);
            myIntent.putExtra("body1", rv_body1[3]);
            myIntent.putExtra("body2", rv_body2[3]);
            myIntent.putExtra("body3", rv_body3[3]);
            myIntent.putExtra("body4", rv_body4[3]);
            myIntent.putExtra("ac_checker", "3");
            myIntent.putExtra("video", vid_addiction);
            myIntent.putExtra("image", img_addiction);
            StartActivity_badEffects.this.startActivity(myIntent);
        });
    }
}