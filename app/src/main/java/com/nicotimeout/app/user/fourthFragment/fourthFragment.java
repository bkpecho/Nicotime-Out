package com.nicotimeout.app.user.fourthFragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nicotimeout.app.R;

public class fourthFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Quit Smoking Timeline");
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        return view;
    }
}