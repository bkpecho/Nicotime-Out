package com.nicotimeout.app.userStart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.nicotimeout.app.R;
import com.nicotimeout.app.common.QuestionActivity;
import com.nicotimeout.app.user.UserProgress;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        CardView cv1 = findViewById(R.id.activity_start_cardview1);
        CardView cv2 = findViewById(R.id.activity_start_cardview2);
        CardView cv3 = findViewById(R.id.activity_start_cardview3);

        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.activity_start_cardview1:
                i = new Intent(this, StartActivity_first.class);
                startActivity(i);
                break;
            case R.id.activity_start_cardview2:
                i = new Intent(this, StartActivity_first.class);
                startActivity(i);
                break;
            case R.id.activity_start_cardview3:
                i = new Intent(this, QuestionActivity.class);
                startActivity(i);
                break;
        }
    }
}