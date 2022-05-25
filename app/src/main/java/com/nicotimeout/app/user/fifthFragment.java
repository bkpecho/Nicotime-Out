package com.nicotimeout.app.user;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.nicotimeout.app.R;


public class fifthFragment extends Fragment {

    public static final String PREF_ACHIEVEMENTS_COUNTER = "achievementsCounter";
    Animation flicker = null;
    CardView cv_openLink;
    ImageView fragment_fifth_imageview;
    LinearLayout fifthFragment_linearheader;
    LinearLayout fifthFragment_linearbody;
    Dialog ac_explorer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_fifth, container, false);

        //shared preferences achievements
        SharedPreferences prefs_achievements = getActivity().getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0);
        SharedPreferences.Editor achievements_editor = getActivity().getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();
        long pref_explorer = prefs_achievements.getLong("pref_explorer", 0);

        cv_openLink = view.findViewById(R.id.cv_openLink);
        flicker = AnimationUtils.loadAnimation(getActivity(), R.anim.flicker);
        fragment_fifth_imageview = view.findViewById(R.id.fragment_fifth_imageview);
        fifthFragment_linearheader = view.findViewById(R.id.fifthFragment_linearheader);
        fifthFragment_linearbody = view.findViewById(R.id.fifthFragment_linearbody);
        ac_explorer = new Dialog(getActivity());

        if (pref_explorer == 0) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            fifthFragment_linearheader.setLayoutParams(param);
            fragment_fifth_imageview.setAnimation(flicker);
        }
        if (pref_explorer == 2) {
            ac_explorer();
        }

        fifthFragment_linearheader.setOnClickListener(view1 -> {
            if (pref_explorer == 0) {
                achievements_editor.putLong("pref_explorer", 2);
                achievements_editor.apply();
                getParentFragmentManager().beginTransaction().replace(
                        R.id.fragmentContainerView, new fifthFragment()).commit();
            }
        });

        cv_openLink.setOnClickListener(view12 -> {
            Uri uri = Uri.parse(getString(R.string.rehab_link));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xFF299cfc);

        return view;
    }

    private void ac_explorer() {
        ac_explorer.setContentView(R.layout.ac_explorer);
        ac_explorer.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_explorer.setCancelable(false);
        ac_explorer.setCanceledOnTouchOutside(false);


        Button button = ac_explorer.findViewById(R.id.button);
        button.setOnClickListener(view -> {
            SharedPreferences.Editor achievements_editor = getActivity().getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();
            achievements_editor.putLong("pref_explorer", 1);
            achievements_editor.apply();
            ac_explorer.dismiss();
        });
        ac_explorer.show();
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}