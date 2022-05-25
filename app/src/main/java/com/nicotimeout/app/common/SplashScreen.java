package com.nicotimeout.app.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.nicotimeout.app.R;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIMER = 3000;

    //variable
    ImageView backgroundImage;

    //animations
    Animation sideAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        backgroundImage = findViewById(R.id.background_image);

        //animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);

        //set anim on elements
        backgroundImage.setAnimation(sideAnim);

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }, SPLASH_TIMER);
    }
}