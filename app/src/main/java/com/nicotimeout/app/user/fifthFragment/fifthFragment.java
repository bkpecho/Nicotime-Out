package com.nicotimeout.app.user.fifthFragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.nicotimeout.app.R;

import java.util.ArrayList;


public class fifthFragment extends Fragment {

    public static final String PREF_ACHIEVEMENTS_COUNTER = "achievementsCounter";

    RecyclerView recyclerView;
    ArrayList images, name;

    Dialog ac_explorer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_fifth, container, false);

        //shared preferences achievements
        SharedPreferences prefs_achievements = getActivity().getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0);
        SharedPreferences.Editor achievements_editor = getActivity().getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();
        long pref_explorer = prefs_achievements.getLong("pref_explorer", 0);

        ac_explorer = new Dialog(getActivity());

        if (pref_explorer == 0) {
            ac_explorer();
            achievements_editor.putLong("pref_explorer", 1);
            achievements_editor.apply();
        }


        recyclerView = view.findViewById(R.id.fifthFragment_recyclerView);
        images = new ArrayList();
        name = new ArrayList();

        for (int i = 0; i < fifthFragment_data.data_title.length; i++) {
            images.add(fifthFragment_data.data_images);
            name.add(fifthFragment_data.data_title);
        }
        fifthFragment_adapter helperAdapter = new fifthFragment_adapter(getContext(), images, name);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);

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
        button.setOnClickListener(view -> ac_explorer.dismiss());
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