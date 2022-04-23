package com.nicotimeout.app.user.fourthFragment;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nicotimeout.app.R;
import com.nicotimeout.app.database.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class fourthFragment extends Fragment {
    ProgressBar progress_20mins,
            progress_8hrs,
            progress_24hrs,
            progress_48hrs,
            progress_72hrs,
            progress_1week;

    long secondsInMilli;
    long minutesInMilli;
    long hoursInMilli;
    long daysInMilli;

    long elapsedDays;
    long elapsedHours;
    long elapsedMinutes;
    long elapsedSeconds;

    //total elapsed time
    long rawElapsedSeconds;
    long rawElapsedMinutes;

    //max time
    long future_20mins;
    long future_8hrs;
    long future_24hrs;
    long future_48hrs;
    long future_72hrs;
    long future_1week;

    //countdownTimer
    CountDownTimer cdTimer_20mins;
    CountDownTimer cdTimer_8hrs;
    CountDownTimer cdTimer_24hrs;
    CountDownTimer cdTimer_48hrs;
    CountDownTimer cdTimer_72hrs;
    CountDownTimer cdTimer_1week;

    long different;
    long rawDifferent;

    String quit_date;
    String localTime;

    SimpleDateFormat simpleDateFormat;
    Date startDate;
    Date endDate;
    Cursor cursor;

    DatabaseHelper databaseHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

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
                                cursor = databaseHelper.getDataDate();
                                if (cursor.getCount() == 0) {
                                    Toast.makeText(getActivity(), "Database is Empty", Toast.LENGTH_SHORT).show();
                                } else {
                                    while (cursor.moveToNext()) {

                                        try {
                                            quit_date = cursor.getString(0);

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

                                            Button btnClick = view.findViewById(R.id.btnClick);
                                            btnClick.setText(String.valueOf(rawElapsedMinutes));

                                        } catch (Exception e) {
                                            Toast.makeText(getActivity(), "Logical Error", Toast.LENGTH_SHORT).show();
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

        future_20mins = minutesInMilli * 2;
        future_8hrs = hoursInMilli * 8;
        future_24hrs = daysInMilli;
        future_48hrs = daysInMilli * 2;
        future_72hrs = daysInMilli * 3;
        future_1week = daysInMilli * 7;

        progress_20mins = view.findViewById(R.id.progress_20mins);
        progress_8hrs = view.findViewById(R.id.progress_8hrs);
        progress_24hrs = view.findViewById(R.id.progress_24hrs);
        progress_48hrs = view.findViewById(R.id.progress_48hrs);
        progress_72hrs = view.findViewById(R.id.progress_72hrs);
        progress_1week = view.findViewById(R.id.progress_1week);

        cdTimer_20mins = new CountDownTimer(future_20mins, secondsInMilli) {

            @Override
            public void onTick(long millisUntilFinished) {
                progress_20mins.setProgress((int) (rawElapsedSeconds * 100 / (future_20mins / secondsInMilli)));

            }

            @Override
            public void onFinish() {
                progress_20mins.setProgress(100);
            }
        };
        cdTimer_20mins.start();

        cdTimer_8hrs = new CountDownTimer(future_8hrs, secondsInMilli) {

            @Override
            public void onTick(long millisUntilFinished) {
                progress_8hrs.setProgress((int) (rawElapsedSeconds * 100 / (future_8hrs / secondsInMilli)));

            }

            @Override
            public void onFinish() {
                //dito ilalagay yung parang alert
                progress_8hrs.setProgress(100);
            }
        };
        cdTimer_8hrs.start();

        cdTimer_24hrs = new CountDownTimer(future_24hrs, secondsInMilli) {

            @Override
            public void onTick(long millisUntilFinished) {
                progress_24hrs.setProgress((int) (rawElapsedSeconds * 100 / (future_24hrs / secondsInMilli)));

            }

            @Override
            public void onFinish() {
                progress_24hrs.setProgress(100);
            }
        };
        cdTimer_24hrs.start();

        cdTimer_48hrs = new CountDownTimer(future_48hrs, secondsInMilli) {

            @Override
            public void onTick(long millisUntilFinished) {
                progress_48hrs.setProgress((int) (rawElapsedSeconds * 100 / (future_48hrs / secondsInMilli)));

            }

            @Override
            public void onFinish() {
                progress_48hrs.setProgress(100);
            }
        };
        cdTimer_48hrs.start();

        cdTimer_72hrs = new CountDownTimer(future_72hrs, secondsInMilli) {

            @Override
            public void onTick(long millisUntilFinished) {
                progress_72hrs.setProgress((int) (rawElapsedSeconds * 100 / (future_72hrs / secondsInMilli)));

            }

            @Override
            public void onFinish() {
                progress_72hrs.setProgress(100);
            }
        };
        cdTimer_72hrs.start();

        cdTimer_1week = new CountDownTimer(future_1week, secondsInMilli) {

            @Override
            public void onTick(long millisUntilFinished) {
                progress_1week.setProgress((int) (rawElapsedSeconds * 100 / (future_1week / secondsInMilli)));

            }

            @Override
            public void onFinish() {
                progress_1week.setProgress(100);
            }
        };
        cdTimer_1week.start();

        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xFF299cfc);

        return view;

    }
/*
    @Override
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
