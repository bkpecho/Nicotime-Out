package com.nicotimeout.app.userStart;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.nicotimeout.app.R;

public class StartActivity_popup_fullscreen extends AppCompatActivity {
    public static final String PREF_ACHIEVEMENTS_COUNTER = "achievementsCounter";
    Dialog ac_knowledgeispower;
    Dialog ac_inquisitivemind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_popup_fullscreen);

        ac_knowledgeispower = new Dialog(this);
        ac_inquisitivemind = new Dialog(this);

        VideoView videoView = findViewById(R.id.videoView);
        Intent intent = getIntent();
        String video = intent.getStringExtra("video");
        String ac_checker = intent.getStringExtra("ac_checker");


        hideNav();

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(video));
        videoView.start();

        videoView.setOnCompletionListener(mediaPlayer -> {
            //sharedpreferences achievements
            SharedPreferences prefs_achievements = getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0);
            SharedPreferences.Editor achievements_editor = getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();

            long pref_watch = prefs_achievements.getLong("pref_watch", 0);
            long pref_vid0 = prefs_achievements.getLong("pref_vid0", 0);
            long pref_vid1 = prefs_achievements.getLong("pref_vid1", 0);
            long pref_vid2 = prefs_achievements.getLong("pref_vid2", 0);
            long pref_vid3 = prefs_achievements.getLong("pref_vid3", 0);
            long pref_vid4 = prefs_achievements.getLong("pref_vid4", 0);

            if (pref_watch == 0) {
                if (ac_checker.equals("0") && pref_vid0 == 0) {
                    achievements_editor.putLong("pref_vid0", 1);
                    achievements_editor.apply();
                }

                if (ac_checker.equals("1") && pref_vid1 == 0) {
                    achievements_editor.putLong("pref_vid0", 1);
                    achievements_editor.apply();
                }

                if (ac_checker.equals("2") && pref_vid2 == 0) {
                    achievements_editor.putLong("pref_vid2", 1);
                    achievements_editor.apply();
                }

                if (ac_checker.equals("3") && pref_vid3 == 0) {
                    achievements_editor.putLong("pref_vid3", 1);
                    achievements_editor.apply();

                }
                if (ac_checker.equals("4") && pref_vid4 == 0) {
                    achievements_editor.putLong("pref_vid4", 1);
                    achievements_editor.apply();

                }
                ac_knowledgeispower();
            }
            if (pref_watch == 4) {
                if (ac_checker.equals("0") && pref_vid0 == 0) {
                    achievements_editor.putLong("pref_vid0", 1);
                    achievements_editor.apply();
                }

                if (ac_checker.equals("1") && pref_vid1 == 0) {
                    achievements_editor.putLong("pref_vid0", 1);
                    achievements_editor.apply();
                }

                if (ac_checker.equals("2") && pref_vid2 == 0) {
                    achievements_editor.putLong("pref_vid2", 1);
                    achievements_editor.apply();
                }

                if (ac_checker.equals("3") && pref_vid3 == 0) {
                    achievements_editor.putLong("pref_vid3", 1);
                    achievements_editor.apply();

                }
                if (ac_checker.equals("4") && pref_vid4 == 0) {
                    achievements_editor.putLong("pref_vid4", 1);
                    achievements_editor.apply();
                }
                ac_inquisitivemind();
            }
            if (pref_watch >= 1 && pref_watch <= 3) {
                if (ac_checker.equals("0") && pref_vid0 == 0) {
                    achievements_editor.putLong("pref_vid0", 1);
                    long i = pref_watch + 1;
                    achievements_editor.putLong("pref_watch", i);
                    achievements_editor.apply();
                }


                if (ac_checker.equals("1") && pref_vid1 == 0) {
                    achievements_editor.putLong("pref_vid1", 1);
                    long i = pref_watch + 1;
                    achievements_editor.putLong("pref_watch", i);
                    achievements_editor.apply();
                }


                if (ac_checker.equals("2") && pref_vid2 == 0) {
                    achievements_editor.putLong("pref_vid2", 1);
                    long i = pref_watch + 1;
                    achievements_editor.putLong("pref_watch", i);
                    achievements_editor.apply();
                }


                if (ac_checker.equals("3") && pref_vid3 == 0) {
                    achievements_editor.putLong("pref_vid3", 1);
                    long i = pref_watch + 1;
                    achievements_editor.putLong("pref_watch", i);
                    achievements_editor.apply();

                }
                if (ac_checker.equals("4") && pref_vid4 == 0) {
                    achievements_editor.putLong("pref_vid4", 1);
                    long i = pref_watch + 1;
                    achievements_editor.putLong("pref_watch", i);
                    achievements_editor.apply();

                }
                onBackPressed();
            }
            if (pref_watch == 5) {

                onBackPressed();
            }
        });
    }

    public void ac_knowledgeispower() {
        ac_knowledgeispower.setContentView(R.layout.ac_knowledgeispower);
        ac_knowledgeispower.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_knowledgeispower.setCancelable(false);
        ac_knowledgeispower.setCanceledOnTouchOutside(false);

        Button button = ac_knowledgeispower.findViewById(R.id.button);
        button.setOnClickListener(view -> {
            SharedPreferences.Editor achievements_editor = getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();
            achievements_editor.putLong("pref_watch", 1);
            achievements_editor.apply();
            ac_knowledgeispower.dismiss();
            onBackPressed();
        });
        ac_knowledgeispower.show();
    }

    public void ac_inquisitivemind() {
        ac_inquisitivemind.setContentView(R.layout.ac_inquisitivemind);
        ac_inquisitivemind.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_inquisitivemind.setCancelable(false);
        ac_inquisitivemind.setCanceledOnTouchOutside(false);

        Button button = ac_inquisitivemind.findViewById(R.id.button);
        button.setOnClickListener(view -> {
            SharedPreferences.Editor achievements_editor = getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();
            achievements_editor.putLong("pref_watch", 5);
            achievements_editor.apply();
            ac_inquisitivemind.dismiss();
            onBackPressed();
        });
        ac_inquisitivemind.show();
    }

    public void hideNav() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}