package com.nicotimeout.app.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nicotimeout.app.R;
import com.nicotimeout.app.database.DatabaseHelper;
import com.nicotimeout.app.database.UserModel;
import com.nicotimeout.app.user.UserProgress;

public class QuestionActivity extends AppCompatActivity {

    //references to button and other controls
    Button question_button;
    EditText edit_questions_cig_smok_day, edit_questions_cig_price_piece, edit_questions_years_smoking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question_button = findViewById(R.id.question_button);
        edit_questions_cig_smok_day = findViewById(R.id.edit_questions_cig_smok_day);
        edit_questions_cig_price_piece = findViewById(R.id.edit_questions_cig_price_piece);
        edit_questions_years_smoking = findViewById(R.id.edit_questions_years_smoking);

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
        //    question_button.setOnClickListener(v -> startActivity(new Intent(QuestionActivity.this, UserProgress.class)));
        question_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserModel userModel;
                try {
                    userModel = new UserModel(-1,
                            Integer.parseInt(edit_questions_cig_smok_day.getText().toString()),
                            Integer.parseInt(edit_questions_cig_price_piece.getText().toString()),
                            Integer.parseInt(edit_questions_years_smoking.getText().toString()));

                    Toast.makeText(QuestionActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuestionActivity.this, UserProgress.class);
                    QuestionActivity.this.startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(QuestionActivity.this, "error", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1,0,0,0);
                }
                DatabaseHelper databaseHelper = new DatabaseHelper(QuestionActivity.this);
                boolean success = databaseHelper.addOne(userModel);
                Toast.makeText(QuestionActivity.this, "Success", Toast.LENGTH_SHORT).show();

            }
        });

    }
}