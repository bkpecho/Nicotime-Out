package com.nicotimeout.app.userStart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.nicotimeout.app.R;

public class StartActivity_popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_popup);

        Intent intent = getIntent();
        ImageView imageView = findViewById(R.id.imageView);
        String image = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        String body1 = intent.getStringExtra("body1");
        String body2 = intent.getStringExtra("body2");
        String body3 = intent.getStringExtra("body3");
        String body4 = intent.getStringExtra("body4");
        String video = intent.getStringExtra("video");
        String ac_checker = intent.getStringExtra("ac_checker");
        TextView textView1 = findViewById(R.id.title);
        TextView textView2 = findViewById(R.id.body1);
        TextView textView3 = findViewById(R.id.body2);
        TextView textView4 = findViewById(R.id.body3);
        TextView textView5 = findViewById(R.id.body4);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Back");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Glide
                .with(this)
                .load(image)
                .into(imageView);

        imageView.setOnClickListener(view -> {
            Intent myIntent = new Intent(StartActivity_popup.this, StartActivity_popup_fullscreen.class);
            myIntent.putExtra("video", video);
            myIntent.putExtra("ac_checker", ac_checker);
            StartActivity_popup.this.startActivity(myIntent);
        });
        textView1.setText(title);
        textView2.setText(body1);
        textView3.setText(body2);
        textView4.setText(body3);
        textView5.setText(body4);


    }
}