package com.nicotimeout.app.common;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nicotimeout.app.R;
import com.nicotimeout.app.user.UserProgress;

import java.util.Calendar;

public class QuestionActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = context;
        setContentView(R.layout.activity_question);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodayDate());
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

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year);

    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, R.style.MyDatePickerDialogTheme, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year) {

        return getMonthFormat(month) + " " + day + ", " + year;

    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        return "JAN";
    }

    public void openDatePicker(View view) {

        datePickerDialog.show();

    }
}