package com.nicotimeout.app.common;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nicotimeout.app.R;
import com.nicotimeout.app.database.DatabaseHelper;
import com.nicotimeout.app.database.UserModel;
import com.nicotimeout.app.user.UserProgress;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class QuestionActivity extends AppCompatActivity {

    //references to button and other controls
    Button question_button;

    EditText edit_questions_cig_smok_day,
            edit_questions_cig_price_piece,
            edit_questions_years_smoking;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(QuestionActivity.this);
        }
        Cursor cursor= databaseHelper.getData();
        if (cursor.getCount() == 0) {
        } else {
                Intent intent = new Intent(QuestionActivity.this, UserProgress.class);
                startActivity(intent);
        }

        question_button = findViewById(R.id.question_button);

        edit_questions_cig_smok_day = findViewById(R.id.edit_questions_cig_smok_day);
        edit_questions_cig_price_piece = findViewById(R.id.edit_questions_cig_price_piece);
        edit_questions_years_smoking = findViewById(R.id.edit_questions_years_smoking);



        //    question_button.setOnClickListener(v -> startActivity(new Intent(QuestionActivity.this, UserProgress.class)));
        question_button.setOnClickListener(view -> {

            String editTxt1 = edit_questions_cig_smok_day.getText().toString();
            String editTxt2 = edit_questions_cig_price_piece.getText().toString();
            String editTxt3 = edit_questions_years_smoking.getText().toString();

            if (editTxt1.isEmpty() || editTxt2.isEmpty() || editTxt3.isEmpty()) {

                AlertDialog alertDialog = new AlertDialog.Builder(QuestionActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Some fields are empty");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();

            } else {
                UserModel userModel = null;
                try {
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                    Date currentLocalTime = calendar.getTime();
                    DateFormat date = new SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault());
                    date.setTimeZone(TimeZone.getTimeZone("GMT+8"));

                    String localTime = date.format(currentLocalTime);

                    userModel = new UserModel(-1,
                            localTime,
                            Integer.parseInt(editTxt1),
                            Integer.parseInt(editTxt2),
                            Integer.parseInt(editTxt3));

                    Toast.makeText(QuestionActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuestionActivity.this, UserProgress.class);
                    QuestionActivity.this.startActivity(intent);


                } catch (Exception e) {
                    Toast.makeText(QuestionActivity.this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }
                DatabaseHelper databaseHelper = new DatabaseHelper(QuestionActivity.this);
                databaseHelper.addOne(userModel);

            }
        });

    }
}
