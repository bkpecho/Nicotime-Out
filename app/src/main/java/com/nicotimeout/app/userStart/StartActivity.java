package com.nicotimeout.app.userStart;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.nicotimeout.app.R;
import com.nicotimeout.app.common.QuestionActivity;
import com.nicotimeout.app.database.DatabaseHelper;
import com.nicotimeout.app.user.thirdFragment;

import org.joda.time.DateTime;

import java.util.Random;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;

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
    Dialog about_dialog;


    ImageView fragment_fourth_imageview;
    ImageView nicotimeout;
    ImageButton imageButton;

    long counter;
    long lastSaveTime;
    long pref_login_dialog;
    long reset;
    int today;

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
        notificationChannel();

        animShake = AnimationUtils.loadAnimation(this, R.anim.wobble);
        animShake_2secs = AnimationUtils.loadAnimation(this, R.anim.wobble_2secs);

        login_dialog = new Dialog(this);
        about_dialog = new Dialog(this);

        nicotimeout = findViewById(R.id.nicotimeout);
        fragment_fourth_imageview = findViewById(R.id.fragment_fourth_imageview);
        fragment_fourth_imageview.setOnClickListener(view -> {
            fragment_fourth_imageview.startAnimation(animShake_2secs);
        });
        cv1 = findViewById(R.id.activity_start_cardview1);
        cv2 = findViewById(R.id.activity_start_cardview2);
        cv3 = findViewById(R.id.activity_start_cardview3);

        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);

        imageButton = findViewById(R.id.about_us);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about_dialog();
            }
        });

        rv_subTitle = getResources().getStringArray(R.array.rv_subTitle);
        rv_body1 = getResources().getStringArray(R.array.rv_body1);
        rv_body2 = getResources().getStringArray(R.array.rv_body2);
        rv_body3 = getResources().getStringArray(R.array.rv_body3);
        rv_body4 = getResources().getStringArray(R.array.rv_body4);

        SharedPreferences prefs = getSharedPreferences(PREF_LOGIN_COUNTER, 0);
        SharedPreferences.Editor counter_editor = getSharedPreferences(PREF_LOGIN_COUNTER, 0).edit();
        counter = prefs.getLong("counter", 0);
        reset = prefs.getLong("reset", 0);

        SharedPreferences settings = getSharedPreferences(PREF_LOGIN_COMPARE, 0);
        SharedPreferences.Editor compare_editor = getSharedPreferences(PREF_LOGIN_COMPARE, 0).edit();
        lastSaveTime = settings.getLong("last_save", 0);
        today = DateTime.now().getDayOfYear();

        if (lastSaveTime != today) {
            compare_editor.putLong("last_save", today);
            long add = counter + 1;
            counter_editor.putLong("counter", add);

            compare_editor.apply();
            counter_editor.apply();

        }
        SharedPreferences prefs_achievements = getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0);
        SharedPreferences.Editor achievements_editor = getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();
        pref_login_dialog = prefs_achievements.getLong("pref_login_dialog", 0);
        if (pref_login_dialog != today) {
            achievements_editor.putLong("pref_login_dialog", today);
            achievements_editor.apply();
            login_dialog();
        }
    }

    private void about_dialog() {
        about_dialog.setContentView(R.layout.about_dialog);
        about_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        about_dialog.setCancelable(false);
        about_dialog.setCanceledOnTouchOutside(false);
        Button button = about_dialog.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about_dialog.dismiss();
            }
        });
        about_dialog.show();
    }


    private void notificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Nicotime-Out!";
            String description = "Channel for Nicotime-Out!";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void startActivityNotification() {
        String days = String.valueOf(counter + 1);
        String contentTitle = "Nicotime-Out!";
        String contentText1 = "Hello Champ! Thank you for using Nicotime-Out! We wish you the best luck with your quitting journey :)";
        String contentText2 = "Hey Champ! This is your day " + days + " of using the Nicotime-Out app! Keep on going :)";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.idea)
                .setContentTitle(contentTitle)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        if (counter == 0) {
            builder.setContentText(contentText1);
            builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(contentText1));
        } else {
            builder.setContentText(contentText2);
            builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(contentText2));
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }

    private void resetNotification() {
        String contentTitle1 = "I Slipped Up, Now What?";
        String contentTitle2 = "Don’t Give Up!";
        String contentText1 = "Most slip-ups occur within the first week of trying to quit smoking. Just because you take a puff or two of a cigarette or slide into a full-blown relapse doesn't mean that you can’t begin again. The important thing to remember is that you’re still in control and can move forward in your efforts to quit smoking.";
        String contentText2 = "Most people try several times before succeeding. If you have relapsed, treat this incident as something to learn from, and an experience that you can use later on.";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.idea)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        if (reset == 1) {
            builder.setContentTitle(contentTitle1);
            builder.setContentText(contentText1);
            builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(contentText1));
        } else {
            builder.setContentTitle(contentTitle2);
            builder.setContentText(contentText2);
            builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(contentText2));
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
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

        button.setOnClickListener(view -> {
            if (counter == 0) {
                new MaterialIntroView.Builder(this)
                        .enableIcon(false)
                        .setFocusGravity(FocusGravity.CENTER)
                        .setFocusType(Focus.ALL)
                        .enableFadeAnimation(true)
                        .performClick(false)
                        .setInfoText("Hello there! This is your dashboard.")
                        .setInfoTextSize(14)
                        .setTextColor(getResources().getColor(R.color.main_blue))
                        .setShape(ShapeType.CIRCLE)
                        .setTarget(nicotimeout)
                        .setUsageId("0")
                        .setListener(materialIntroViewId -> {
                            new MaterialIntroView.Builder(StartActivity.this)
                                    .enableIcon(false)
                                    .setFocusGravity(FocusGravity.CENTER)
                                    .setFocusType(Focus.ALL)
                                    .enableFadeAnimation(true)
                                    .performClick(false)
                                    .setInfoText("Learn more about the Bad Effects of Cigarette Smoking")
                                    .setInfoTextSize(14)
                                    .setTextColor(getResources().getColor(R.color.main_blue))
                                    .setShape(ShapeType.RECTANGLE)
                                    .setTarget(cv1)
                                    .setUsageId("1")
                                    .setListener(materialIntroViewId1 -> {
                                        new MaterialIntroView.Builder(StartActivity.this)
                                                .enableIcon(false)
                                                .setFocusGravity(FocusGravity.CENTER)
                                                .setFocusType(Focus.ALL)
                                                .enableFadeAnimation(true)
                                                .performClick(false)
                                                .setInfoText("Discover multiple ways to help you quit.")
                                                .setInfoTextSize(14)
                                                .setTextColor(getResources().getColor(R.color.main_blue))
                                                .setShape(ShapeType.RECTANGLE)
                                                .setTarget(cv2)
                                                .setUsageId("2")
                                                .setListener(materialIntroViewId2 -> {
                                                    new MaterialIntroView.Builder(StartActivity.this)
                                                            .enableIcon(false)
                                                            .setFocusGravity(FocusGravity.CENTER)
                                                            .setFocusType(Focus.ALL)
                                                            .enableFadeAnimation(true)
                                                            .performClick(false)
                                                            .setInfoText("Click here to Start Tracking Your Progress.")
                                                            .setInfoTextSize(14)
                                                            .setTextColor(getResources().getColor(R.color.main_blue))
                                                            .setShape(ShapeType.RECTANGLE)
                                                            .setTarget(cv3)
                                                            .setUsageId("3")
                                                            .show();
                                                })

                                                .show();
                                    })
                                    .show();
                        })
                        .show();
            }
            login_dialog.dismiss();
            startActivityNotification();
        });
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
};