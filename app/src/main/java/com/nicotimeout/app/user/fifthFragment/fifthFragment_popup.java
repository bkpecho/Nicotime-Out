package com.nicotimeout.app.user.fifthFragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nicotimeout.app.R;

public class fifthFragment_popup extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description;

    String data1, data2;
    int myImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fifth_popup);

        mainImageView = findViewById(R.id.fragment_fifth_imageView_popup);
        title = findViewById(R.id.fragment_fifth_textView_popup);
        description = findViewById(R.id.fragment_fifth_textView2_popup);

        getData();
        setData();

    }

    private void getData() {
        if (getIntent().hasExtra("images") && getIntent().hasExtra("title")) {

            data1 = getIntent().getStringExtra("title");
            data2 = getIntent().getStringExtra("description");
            myImage = getIntent().getIntExtra("images", 1);

        } else
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
    }

    private void setData() {

        title.setText(data1);
        description.setText(data2);
        mainImageView.setImageResource(myImage);

    }

}

