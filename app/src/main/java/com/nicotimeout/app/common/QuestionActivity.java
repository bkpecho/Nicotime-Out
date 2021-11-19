package com.nicotimeout.app.common;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import com.nicotimeout.app.R;
import java.util.Calendar;

public class QuestionActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodayDate());
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

        int style = AlertDialog.THEME_HOLO_LIGHT;

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