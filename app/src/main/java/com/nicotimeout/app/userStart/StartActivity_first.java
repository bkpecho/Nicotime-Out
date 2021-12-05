package com.nicotimeout.app.userStart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nicotimeout.app.R;


public class StartActivity_first extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] s1;
    String[] s2;
    int[] images = {R.drawable.start_learning, R.drawable.start_knowledge, R.drawable.start_book, R.drawable.start_launch};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_first);

        recyclerView = findViewById(R.id.recyclerview);

        s1 = getResources().getStringArray(R.array.video_title);
        s2 = getResources().getStringArray(R.array.video_description);

        StartActivity_first_adapter myAdapter = new StartActivity_first_adapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}