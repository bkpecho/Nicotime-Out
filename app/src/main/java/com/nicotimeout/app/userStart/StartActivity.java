package com.nicotimeout.app.userStart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicotimeout.app.R;
import com.nicotimeout.app.common.QuestionActivity;

import org.joda.time.DateTime;

import java.util.Random;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREF_LOGIN_COMPARE = "loginCompare";
    public static final String PREF_LOGIN_COUNTER = "loginCounter";
    public static final String PREF_ACHIEVEMENTS_COUNTER = "achievementsCounter";

    Animation animShake = null;
    Animation animShake_2secs = null;

    CardView cv1;
    CardView cv2;
    CardView cv3;

    Dialog login_dialog;

    ImageView fragment_fourth_imageview;
    int[] imageView = new int[]{R.drawable.vid_cigarette, R.drawable.vid_smoking, R.drawable.vid_effects, R.drawable.vid_addiction, R.drawable.vid_guide};
    String[] rv_subTitle;
    String[] rv_body1;
    String[] rv_body2;
    String[] rv_body3;
    String[] rv_body4;
    String vid_guide;
    String img_guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        animShake = AnimationUtils.loadAnimation(this, R.anim.wobble);
        animShake_2secs = AnimationUtils.loadAnimation(this, R.anim.wobble_2secs);

        login_dialog = new Dialog(this);

        fragment_fourth_imageview = (ImageView) findViewById(R.id.fragment_fourth_imageview);
        fragment_fourth_imageview.setOnClickListener(view ->
                fragment_fourth_imageview.startAnimation(animShake_2secs));

        cv1 = findViewById(R.id.activity_start_cardview1);
        cv2 = findViewById(R.id.activity_start_cardview2);
        cv3 = findViewById(R.id.activity_start_cardview3);

        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);

        rv_subTitle = getResources().getStringArray(R.array.rv_subTitle);
        rv_body1 = getResources().getStringArray(R.array.rv_body1);
        rv_body2 = getResources().getStringArray(R.array.rv_body2);
        rv_body3 = getResources().getStringArray(R.array.rv_body3);
        rv_body4 = getResources().getStringArray(R.array.rv_body4);

        SharedPreferences prefs = getSharedPreferences(PREF_LOGIN_COUNTER, 0);
        SharedPreferences.Editor counter_editor = getSharedPreferences(PREF_LOGIN_COUNTER, 0).edit();
        long counter = prefs.getLong("counter", 0);

        SharedPreferences settings = getSharedPreferences(PREF_LOGIN_COMPARE, 0);
        SharedPreferences.Editor compare_editor = getSharedPreferences(PREF_LOGIN_COMPARE, 0).edit();
        long lastSaveTime = settings.getLong("last_save", 0);
        int today = DateTime.now().getDayOfYear();

        if (lastSaveTime != today) {
            compare_editor.putLong("last_save", today);
            long add = counter + 1;
            counter_editor.putLong("counter", add);

            compare_editor.apply();
            counter_editor.apply();
        }

        SharedPreferences prefs_achievements = getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0);
        SharedPreferences.Editor achievements_editor = getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();

        long pref_login_dialog = prefs_achievements.getLong("pref_login_dialog", 0);
        if (pref_login_dialog != today) {
            achievements_editor.putLong("pref_login_dialog", today);
            achievements_editor.apply();
            login_dialog();
        }
    }

    private void login_dialog() {
        login_dialog.setContentView(R.layout.login_dialog);
        login_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        login_dialog.setCancelable(false);
        login_dialog.setCanceledOnTouchOutside(false);


        ImageView imageView = login_dialog.findViewById(R.id.imageView);
        TextView text_top = login_dialog.findViewById(R.id.textView_title);
        TextView text_middle = login_dialog.findViewById(R.id.textView_quotes);
        TextView text_bottom = login_dialog.findViewById(R.id.textView_author);
        Button button = login_dialog.findViewById(R.id.button);

        int idx = new Random().nextInt(getResources().getStringArray(R.array.quotes).length - 1);

        String quotes = getResources().getStringArray(R.array.quotes)[idx];
        String authors = getResources().getStringArray(R.array.authors)[idx];
        imageView.startAnimation(animShake);
        imageView.setOnClickListener(view -> {

            imageView.clearAnimation();
            imageView.setImageResource(R.drawable.login_gift_open);

            text_top.setText("Quote of the Day");
            text_middle.setText(quotes);
            text_bottom.setText(authors);

            text_bottom.setVisibility(View.GONE);
            text_middle.setVisibility(View.VISIBLE);
            text_bottom.setVisibility(View.VISIBLE);

            button.setVisibility(View.VISIBLE);

        });

        button.setOnClickListener(view -> login_dialog.dismiss());
        login_dialog.show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.activity_start_cardview1:
                i = new Intent(this, StartActivity_badEffects.class);
                startActivity(i);
                break;
            case R.id.activity_start_cardview2:
                Intent myIntent = new Intent(this, StartActivity_popup.class);
                vid_guide = "android.resource://" + getPackageName() + "/" + R.raw.vid_guide;
                img_guide = "android.resource://" + getPackageName() + "/" + R.drawable.vid_guide;
                myIntent.putExtra("title", rv_subTitle[4]);
                myIntent.putExtra("body1", rv_body1[4]);
                myIntent.putExtra("body2", rv_body2[4]);
                myIntent.putExtra("body3", rv_body3[4]);
                myIntent.putExtra("body4", rv_body4[4]);
                myIntent.putExtra("ac_checker", "4");
                myIntent.putExtra("video", vid_guide);
                myIntent.putExtra("image", img_guide);
                startActivity(myIntent);
                break;
            case R.id.activity_start_cardview3:
                i = new Intent(this, QuestionActivity.class);
                startActivity(i);
                break;
        }
    }
}