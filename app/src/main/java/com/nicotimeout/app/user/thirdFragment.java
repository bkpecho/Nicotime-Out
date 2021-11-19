package com.nicotimeout.app.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicotimeout.app.R;


public class thirdFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Progress");
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
}