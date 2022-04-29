package com.nicotimeout.app.user.fourthFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nicotimeout.app.R;
import com.nicotimeout.app.common.MainActivity;
import com.nicotimeout.app.database.DatabaseHelper;
import com.nicotimeout.app.database.UserModel;
import com.nicotimeout.app.userStart.StartActivity;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;


public class fourthFragment extends Fragment {
    com.github.lzyzsd.circleprogress.ArcProgress progress_arc;
    ProgressBar progress_20mins;
    ProgressBar progress_8hrs;
    ProgressBar progress_24hrs;
    ProgressBar progress_48hrs;
    ProgressBar progress_72hrs;
    ProgressBar progress_1week;
    ProgressBar progress_2weeks;
    ProgressBar progress_3weeks;
    ProgressBar progress_1month;
    ProgressBar progress_3months;
    ProgressBar progress_6months;
    ProgressBar progress_1year;

    TextView progress_20mins_text;
    TextView progress_8hrs_text;
    TextView progress_24hrs_text;
    TextView progress_48hrs_text;
    TextView progress_72hrs_text;
    TextView progress_1week_text;
    TextView progress_2weeks_text;
    TextView progress_3weeks_text;
    TextView progress_1month_text;
    TextView progress_3months_text;
    TextView progress_6months_text;
    TextView progress_1year_text;

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
    long rawElapsedHours;
    long rawElapsedDays;

    //max time
    long future_20mins;
    long future_8hrs;
    long future_24hrs;
    long future_48hrs;
    long future_72hrs;
    long future_1week;
    long future_2weeks;
    long future_3weeks;
    long future_1month;
    long future_3months;
    long future_6months;
    long future_1year;

    long ffuture_20mins;
    long ffuture_8hrs;
    long ffuture_24hrs;
    long ffuture_48hrs;
    long ffuture_72hrs;
    long ffuture_1week;
    long ffuture_2weeks;
    long ffuture_3weeks;
    long ffuture_1month;
    long ffuture_3months;
    long ffuture_6months;
    long ffuture_1year;

    int cfuture_20mins;
    int cfuture_8hrs;
    int cfuture_24hrs;
    int cfuture_48hrs;
    int cfuture_72hrs;
    int cfuture_1week;
    int cfuture_2weeks;
    int cfuture_3weeks;
    int cfuture_1month;
    int cfuture_3months;
    int cfuture_6months;
    int cfuture_1year;

    //countdownTimer
    CountDownTimer cdTimer_20mins;
    CountDownTimer cdTimer_8hrs;
    CountDownTimer cdTimer_24hrs;
    CountDownTimer cdTimer_48hrs;
    CountDownTimer cdTimer_72hrs;
    CountDownTimer cdTimer_1week;


    DatabaseHelper databaseHelper;
    Cursor cursor;

    SimpleDateFormat simpleDateFormat;
    String quit_date;
    String localTime;
    Date startDate;
    Date endDate;

    long different;
    long rawDifferent;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    Dialog dialog_20mins;
    Dialog dialog_8hrs;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        dialog_20mins = new Dialog(getActivity());
        dialog_8hrs = new Dialog(getActivity());

        secondsInMilli = 1000;
        minutesInMilli = secondsInMilli * 60;
        hoursInMilli = minutesInMilli * 60;
        daysInMilli = hoursInMilli * 24;

        progress_arc = view.findViewById(R.id.progress_arc);

        //progress_20mins = view.findViewById(R.id.progress_20mins);
      /*  progress_8hrs = view.findViewById(R.id.progress_8hrs);
        progress_24hrs = view.findViewById(R.id.progress_24hrs);
        progress_48hrs = view.findViewById(R.id.progress_48hrs);
        progress_72hrs = view.findViewById(R.id.progress_72hrs);
        progress_1week = view.findViewById(R.id.progress_1week);
        progress_2weeks = view.findViewById(R.id.progress_2weeks);
        progress_3weeks = view.findViewById(R.id.progress_3weeks);
        progress_1month = view.findViewById(R.id.progress_1month);
        progress_3months = view.findViewById(R.id.progress_3months);
        progress_6months = view.findViewById(R.id.progress_6months);
        progress_1year = view.findViewById(R.id.progress_1year);*/

        // progress_20mins.setProgress(78);
     /*   progress_8hrs.setProgress(5);
        progress_24hrs.setProgress(11);
        progress_48hrs.setProgress(25);
        progress_72hrs.setProgress(34);
        progress_1week.setProgress(42);
        progress_2weeks.setProgress(52);
        progress_3weeks.setProgress(12);
        progress_1month.setProgress(33);
        progress_3months.setProgress(0);
        progress_6months.setProgress(0);
        progress_1year.setProgress(0);*/

       /* progress_20mins_text = view.findViewById(R.id.progress_20mins_text);
        progress_8hrs_text = view.findViewById(R.id.progress_8hrs_text);
        progress_24hrs_text = view.findViewById(R.id.progress_24hrs_text);
        progress_48hrs_text = view.findViewById(R.id.progress_48hrs_text);
        progress_72hrs_text = view.findViewById(R.id.progress_72hrs_text);
        progress_1week_text = view.findViewById(R.id.progress_1week_text);
        progress_2weeks_text = view.findViewById(R.id.progress_2weeks_text);
        progress_3weeks_text = view.findViewById(R.id.progress_3weeks_text);
        progress_1month_text = view.findViewById(R.id.progress_1month_text);
        progress_3months_text = view.findViewById(R.id.progress_3months_text);
        progress_6months_text = view.findViewById(R.id.progress_6months_text);
        progress_1year_text = view.findViewById(R.id.progress_1year_text);*/

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.sleep(100);
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
                                quit_date = databaseHelper.getQuitDate();

                                cursor = databaseHelper.getData();
                                if (cursor.getCount() == 0) {
                                    Toast.makeText(getActivity(), "Database is Empty", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    while (cursor.moveToNext()) {
                                        try {
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
                                            rawElapsedDays = rawDifferent / daysInMilli;

                                            future_20mins = 15;
                                            future_8hrs = 30;
                                            future_24hrs = minutesInMilli * 2 / secondsInMilli;
                                            future_48hrs = daysInMilli * 2 / secondsInMilli;
                                            future_72hrs = daysInMilli * 3;
                                            future_1week = daysInMilli * 7;
                                            future_2weeks = daysInMilli * 14;
                                            future_3weeks = daysInMilli * 21;
                                            future_1month = daysInMilli * 30;
                                            future_3months = daysInMilli * 90;
                                            future_6months = daysInMilli * 180;
                                            future_1year = daysInMilli * 365;


                                            cfuture_20mins = (int) (rawElapsedSeconds * 100 / future_20mins);
                                            cfuture_8hrs = (int) (rawElapsedSeconds * 100 / future_8hrs);
                                            cfuture_24hrs = (int) (rawElapsedSeconds * 100 / future_24hrs);
                                            /*     cfuture_48hrs = (int) (rawElapsedHours * 100 / future_48hrs);
                                            cfuture_72hrs = (int) (rawElapsedHours * 100 / future_72hrs);
                                            cfuture_1week = (int) (rawElapsedHours * 100 / future_1week);
                                            cfuture_2weeks = (int) (rawElapsedHours * 100 / future_2weeks);
                                            cfuture_3weeks = (int) (rawElapsedHours * 100 / future_3weeks);
                                            cfuture_1month = (int) (rawElapsedHours * 100 / future_1month);
                                            cfuture_3months = (int) (rawElapsedHours * 100 / future_3months);
                                            cfuture_6months = (int) (rawElapsedHours * 100 / future_6months);*/

                                            SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                                            String status_20mins = prefs.getString("status_20mins", "0");
                                            String status_8hrs = prefs.getString("status_8hrs", "0");

                                            //20 minutes
                                            if (rawElapsedSeconds < future_20mins) {
                                                progress_arc.setProgress(cfuture_20mins);
                                                progress_arc.setBottomText("20 mins");
                                            }
                                            if (rawElapsedSeconds >= future_20mins && status_20mins.equals("0")) {
                                                dialog_20mins();
                                                editor.putString("status_20mins", "1");
                                                editor.apply();
                                            }

                                            //8 hours
                                            if (rawElapsedSeconds < future_8hrs && rawElapsedSeconds > future_20mins) {
                                                progress_arc.setProgress(cfuture_8hrs);
                                                progress_arc.setBottomText("8 hrs");
                                            }
                                            if (rawElapsedSeconds >= future_8hrs && status_8hrs.equals("0")) {
                                                dialog_8hrs();
                                                editor.putString("status_8hrs", "1");
                                                editor.apply();
                                            }

                                            //24 hours
                                            if (rawElapsedSeconds < future_24hrs && rawElapsedSeconds > future_8hrs) {
                                                progress_arc.setProgress(cfuture_24hrs);
                                                progress_arc.setBottomText("24 hrs");
                                            }

                                            TextView txt1 = view.findViewById(R.id.txt1);
                                            TextView txt2 = view.findViewById(R.id.txt2);
                                            txt1.setText(String.valueOf(rawElapsedSeconds));
                                            txt2.setText(status_20mins);

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

        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xFF299cfc);

        return view;

    }

    //dialog

    private void dialog_20mins() {
        dialog_20mins.setContentView(R.layout.ac_layout_20mins);
        dialog_20mins.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog_20mins.setCanceledOnTouchOutside(false);
        //dialog_20mins.setCancelable(false);

        dialog_20mins.show();

    }

    private void dialog_8hrs() {
        dialog_8hrs.setContentView(R.layout.ac_layout_8hrs);
        dialog_8hrs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog_8hrs.setCanceledOnTouchOutside(false);
        //dialog_8hrs.setCancelable(false);

        dialog_8hrs.show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

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