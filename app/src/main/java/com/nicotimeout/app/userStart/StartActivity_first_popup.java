package com.nicotimeout.app.userStart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nicotimeout.app.R;

public class StartActivity_first_popup extends AppCompatActivity {

    ImageView imageView;
    TextView title, description;

    String data1, data2;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_first_popup);

        imageView = findViewById(R.id.popup_imageView);
        title = findViewById(R.id.popup_title);
        description = findViewById(R.id.popup_description);

        getData();
        setData();
    }

    private void getData() {
        if (getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") &&
                getIntent().hasExtra("data2")) {

            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            myImage = getIntent().getIntExtra("myImage", 1);

        } else
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
    }

    private void setData() {
        title.setText(data1);
        description.setText(data2);
        imageView.setImageResource(myImage);
    }
}