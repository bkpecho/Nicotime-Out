package com.nicotimeout.app.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.nicotimeout.app.R;

public class firstFragment extends Fragment {

    public static final String PREF_LOGIN_COUNTER = "loginCounter";
    public static final String PREF_ACHIEVEMENTS_COUNTER = "achievementsCounter";

    //layout variables
    TextView tv_badgesUnlocked;

    CardView cv_thumbsup;
    CardView cv_richman;
    CardView cv_focused;
    CardView cv_30daysofsuccess;
    CardView cv_recoverymedal;
    CardView cv_recoverychampion;
    CardView cv_sturdyasashield;
    CardView cv_standtall;
    CardView cv_explorer;
    CardView cv_knowledgeispower;
    CardView cv_inquisitivemind;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        tv_badgesUnlocked = view.findViewById(R.id.tv_badgesUnlocked);
        cv_thumbsup = view.findViewById(R.id.cv_thumbsup);
        cv_richman = view.findViewById(R.id.cv_richman);
        cv_focused = view.findViewById(R.id.cv_focused);
        cv_30daysofsuccess = view.findViewById(R.id.cv_30daysofsuccess);
        cv_recoverymedal = view.findViewById(R.id.cv_recoverymedal);
        cv_recoverychampion = view.findViewById(R.id.cv_recoverychampion);
        cv_sturdyasashield = view.findViewById(R.id.cv_sturdyasashield);
        cv_standtall = view.findViewById(R.id.cv_standtall);
        cv_explorer = view.findViewById(R.id.cv_explorer);
        cv_knowledgeispower = view.findViewById(R.id.cv_knowledgeispower);
        cv_inquisitivemind = view.findViewById(R.id.cv_inquisitivemind);


        //shared preferences counter
        SharedPreferences prefs_counter = getActivity().getSharedPreferences(PREF_LOGIN_COUNTER, 0);
        long counter = prefs_counter.getLong("counter", 0);


        //shared preferences achievements
        SharedPreferences prefs_achievements = getActivity().getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0);

        long pref_thumbsup = prefs_achievements.getLong("pref_thumbsup", 0);
        long pref_richman = prefs_achievements.getLong("pref_richman", 0);
        long pref_focused = prefs_achievements.getLong("pref_focused", 0);
        long pref_30daysofsuccess = prefs_achievements.getLong("pref_30daysofsuccess", 0);
        long pref_recoverymedal = prefs_achievements.getLong("pref_recoverymedal", 0);
        long pref_recoverychampion = prefs_achievements.getLong("pref_recoverychampion", 0);
        long pref_sturdyasashield = prefs_achievements.getLong("pref_sturdyasashield", 0);
        long pref_standtall = prefs_achievements.getLong("pref_standtall", 0);
        long pref_explorer = prefs_achievements.getLong("pref_explorer", 0);
        long pref_watch = prefs_achievements.getLong("pref_watch", 0);


        if (pref_thumbsup == 1) {
            cv_thumbsup.setVisibility(View.VISIBLE);
        }

        if (pref_richman == 1) {
            cv_richman.setVisibility(View.VISIBLE);
        }

        if (pref_focused == 1) {
            cv_focused.setVisibility(View.VISIBLE);
        }

        if (pref_30daysofsuccess == 1) {
            cv_30daysofsuccess.setVisibility(View.VISIBLE);
        }

        if (pref_recoverymedal == 1) {
            cv_recoverymedal.setVisibility(View.VISIBLE);
        }

        if (pref_recoverychampion == 1) {
            cv_recoverychampion.setVisibility(View.VISIBLE);
        }

        if (pref_sturdyasashield == 1) {
            cv_sturdyasashield.setVisibility(View.VISIBLE);
        }

        if (pref_standtall == 1) {
            cv_standtall.setVisibility(View.VISIBLE);
        }

        if (pref_explorer == 1) {
            cv_explorer.setVisibility(View.VISIBLE);
        }

        if (pref_watch >= 1) {
            cv_knowledgeispower.setVisibility(View.VISIBLE);
        }

        if (pref_watch == 5) {
            cv_inquisitivemind.setVisibility(View.VISIBLE);
        }

        if (counter == 12) {
            cv_explorer.setVisibility(View.GONE);
        }

        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        int childCount = linearLayout.getChildCount();
        int count = 0;

        for (int i = 0; i < childCount; i++) {
            if (linearLayout.getChildAt(i).getVisibility() == View.VISIBLE) {
                count++;
            }
        }
        int total = count - 1;
        tv_badgesUnlocked.setText(String.valueOf(total));

        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xFF299cfc);

        return view;
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