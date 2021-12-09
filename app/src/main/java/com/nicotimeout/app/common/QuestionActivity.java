package com.nicotimeout.app.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.nicotimeout.app.R;
import com.nicotimeout.app.user.UserProgress;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstallQuestions", "");

        if (FirstTime.equals("Yes")) {
            Intent intent = new Intent(QuestionActivity.this, UserProgress.class);
            startActivity(intent);
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstallQuestions", "Yes");
            editor.apply();

        }
        Button btn = findViewById(R.id.question_button);
        btn.setOnClickListener(v -> startActivity(new Intent(QuestionActivity.this, UserProgress.class)));

    }
}