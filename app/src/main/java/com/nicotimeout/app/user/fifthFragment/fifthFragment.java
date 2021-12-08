package com.nicotimeout.app.user.fifthFragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicotimeout.app.R;

import java.util.ArrayList;


public class fifthFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList images, name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Rehabilitaion Centers");
        View view = inflater.inflate(R.layout.fragment_fifth, container, false);
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

        return view;
    }

}