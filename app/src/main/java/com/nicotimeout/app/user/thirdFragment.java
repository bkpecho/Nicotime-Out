package com.nicotimeout.app.user;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nicotimeout.app.R;
import com.nicotimeout.app.common.QuestionActivity;
import com.nicotimeout.app.database.DatabaseHelper;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class thirdFragment extends Fragment {

    TextView fragment_third_days,
            fragment_third_hours,
            fragment_third_mins,
            fragment_third_secs,
            cigarettesAvoided,
            moneySaved,
            lifeRegained,
            cigarettesSmoked,
            moneyWasted,
            lifeLost;


    long data_cigarettesAvoided;
    double progress_cigarettesAvoided;

    double data_moneySaved;
    double progress_moneySaved;

    long data_lifeRegained;
    long data_cigarettesSmoked;
    long data_moneyWasted;
    long data_lifeLost;

    long secondsInMilli;
    long minutesInMilli;
    long hoursInMilli;
    long daysInMilli;

    long elapsedDays;
    long elapsedHours;
    long elapsedMinutes;
    long elapsedSeconds;
    long rawElapsedSeconds;
    long rawElapsedMinutes;
    long rawElapsedHours;

    long different;
    long rawDifferent;

    String quit_date;
    String cig_per_day;
    String cig_price;
    String cig_years;
    String localTime;

    SimpleDateFormat simpleDateFormat;
    Date startDate;
    Date endDate;
    Cursor cursor;

    DatabaseHelper databaseHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        cigarettesSmoked = view.findViewById(R.id.cigarettesSmoked);
        moneyWasted = view.findViewById(R.id.moneyWasted);
        lifeLost = view.findViewById(R.id.lifeLost);
        cigarettesAvoided = view.findViewById(R.id.cigarettesAvoided);
        moneySaved = view.findViewById(R.id.moneySaved);
        lifeRegained = view.findViewById(R.id.lifeRegained);

        fragment_third_days = view.findViewById(R.id.fragment_third_days);
        fragment_third_hours = view.findViewById(R.id.fragment_third_hours);
        fragment_third_mins = view.findViewById(R.id.fragment_third_mins);
        fragment_third_secs = view.findViewById(R.id.fragment_third_secs);

        secondsInMilli = 1000;
        minutesInMilli = secondsInMilli * 60;
        hoursInMilli = minutesInMilli * 60;
        daysInMilli = hoursInMilli * 24;

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.sleep(1000);
                        if (getActivity() == null)
                            return;
                        getActivity().runOnUiThread(() -> {
                            try {
                                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                                Date currentLocalTime = calendar.getTime();
                                DateFormat date = new SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault());
                                date.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                                localTime = date.format(currentLocalTime);

                                if (databaseHelper == null) {
                                    databaseHelper = new DatabaseHelper(getActivity());
                                }
                                // DatabaseHelper databaseHelper = new DatabaseHelper(thirdFragment.this);
                                cursor = databaseHelper.getData();
                                if (cursor.getCount() == 0) {
                                    Toast.makeText(getActivity(), "Database is Empty", Toast.LENGTH_SHORT).show();
                                } else {
                                    while (cursor.moveToNext()) {

                                        try {
                                            quit_date = cursor.getString(1);
                                            cig_per_day = cursor.getString(2);
                                            cig_price = cursor.getString(3);
                                            cig_years = cursor.getString(4);

                                            simpleDateFormat = new SimpleDateFormat(
                                                    "dd/M/yyyy hh:mm:ss", Locale.getDefault());
                                            startDate = simpleDateFormat.parse(quit_date);
                                            endDate = simpleDateFormat.parse(localTime);

                                            different = endDate.getTime() - startDate.getTime();
                                            rawDifferent = endDate.getTime() - startDate.getTime();

                                            elapsedDays = different / daysInMilli;
                                            different = different % daysInMilli;

                                            elapsedHours = different / hoursInMilli;
                                            different = different % hoursInMilli;

                                            elapsedMinutes = different / minutesInMilli;
                                            different = different % minutesInMilli;

                                            elapsedSeconds = different / secondsInMilli;

                                            rawElapsedSeconds = rawDifferent / secondsInMilli;
                                            rawElapsedMinutes = rawDifferent / minutesInMilli;
                                            rawElapsedHours = rawDifferent / hoursInMilli;

                                            /*cigarettesAvoided*/
                                            progress_cigarettesAvoided = Double.parseDouble(cig_per_day) / 24;
                                            data_cigarettesAvoided = (long) (rawElapsedHours * progress_cigarettesAvoided);
                                            //roundedProgress_data_cigarettesAvoided = Math.round(data_cigarettesAvoided);
                                            if (isAdded()) {
                                                String ca_number = String.valueOf(data_cigarettesAvoided);
                                                double ca_amount = Double.parseDouble(ca_number);
                                                DecimalFormat ca_formatter = new DecimalFormat("#,###");
                                                String ca_formatted = ca_formatter.format(ca_amount);
                                                cigarettesAvoided.setText(ca_formatted);
                                            }

                                            /*moneySaved*/
                                            progress_moneySaved = Double.parseDouble(cig_price) * Double.parseDouble(cig_per_day) / 1440;
                                            data_moneySaved = rawElapsedMinutes * progress_moneySaved;
                                            if (isAdded()) {
                                                String ms_number = String.valueOf(data_moneySaved);
                                                double ms_amount = Double.parseDouble(ms_number);
                                                DecimalFormat ms_formatter = new DecimalFormat("₱#,###.##");
                                                String ms_formatted = ms_formatter.format(ms_amount);
                                                moneySaved.setText(ms_formatted);
                                            }

                                            /*moneyWasted*/
                                            data_moneyWasted = data_cigarettesSmoked * Long.parseLong(cig_price);
                                            if (isAdded()) {
                                                String mw_number = String.valueOf(data_moneyWasted);
                                                double mw_amount = Double.parseDouble(mw_number);
                                                DecimalFormat mw_formatter = new DecimalFormat("₱#,###");
                                                String mw_formatted = mw_formatter.format(mw_amount);
                                                moneyWasted.setText(mw_formatted);
                                            }

                                            /*cigarettesSmoked*/
                                            data_cigarettesSmoked = Long.parseLong(cig_per_day) * 365 * Long.parseLong(cig_years);
                                            if (isAdded()) {
                                                String cs_number = String.valueOf(data_cigarettesSmoked);
                                                double cs_amount = Double.parseDouble(cs_number);
                                                DecimalFormat cs_formatter = new DecimalFormat("#,###");
                                                String cs_formatted = cs_formatter.format(cs_amount);
                                                cigarettesSmoked.setText(String.valueOf(cs_formatted));
                                            }


                                            fragment_third_days.setText(String.valueOf(elapsedDays));
                                            fragment_third_hours.setText(String.valueOf(elapsedHours));
                                            fragment_third_mins.setText(String.valueOf(elapsedMinutes));
                                            fragment_third_secs.setText(String.valueOf(elapsedSeconds));

                                            Button btnClick = view.findViewById(R.id.btnClick);
                                            btnClick.setText(String.valueOf(rawElapsedHours));

                                        } catch (Exception e) {
                                            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();

        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xFFdb3056);

        return view;

    }
/*    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cursor != null) {
            cursor.close();
        }
    }*/


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}