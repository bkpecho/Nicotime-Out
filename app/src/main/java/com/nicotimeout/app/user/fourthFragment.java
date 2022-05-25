package com.nicotimeout.app.user;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.nicotimeout.app.R;
import com.nicotimeout.app.database.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class fourthFragment extends Fragment {
    com.github.lzyzsd.circleprogress.ArcProgress progress_arc;

    ImageView icon_20mins;
    ImageView icon_8hrs;
    ImageView icon_24hrs;
    ImageView icon_48hrs;
    ImageView icon_72hrs;
    ImageView icon_1week;
    ImageView icon_2weeks;
    ImageView icon_3weeks;
    ImageView icon_1month;
    ImageView icon_3months;
    ImageView icon_6months;
    ImageView icon_1year;

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

    DatabaseHelper databaseHelper;
    Cursor cursor;

    SimpleDateFormat simpleDateFormat;
    String quit_date;
    String localTime;
    Date startDate;
    Date endDate;

    long different;
    long rawDifferent;

    Dialog dialog_20mins;
    Dialog dialog_8hrs;
    Dialog dialog_24hrs;
    Dialog dialog_48hrs;
    Dialog dialog_72hrs;
    Dialog dialog_1week;
    Dialog dialog_2weeks;
    Dialog dialog_3weeks;
    Dialog dialog_1month;
    Dialog dialog_3months;
    Dialog dialog_6months;
    Dialog dialog_1year;
    Dialog dialog_1year_2nd;

    public static final String FOURTH_PREFS_NAME = "MyPrefsFile";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        progress_arc = view.findViewById(R.id.progress_arc);

        dialog_20mins = new Dialog(getActivity());
        dialog_8hrs = new Dialog(getActivity());
        dialog_24hrs = new Dialog(getActivity());
        dialog_48hrs = new Dialog(getActivity());
        dialog_72hrs = new Dialog(getActivity());
        dialog_1week = new Dialog(getActivity());
        dialog_2weeks = new Dialog(getActivity());
        dialog_3weeks = new Dialog(getActivity());
        dialog_1month = new Dialog(getActivity());
        dialog_3months = new Dialog(getActivity());
        dialog_6months = new Dialog(getActivity());
        dialog_1year = new Dialog(getActivity());
        dialog_1year_2nd = new Dialog(getActivity());

        icon_20mins = view.findViewById(R.id.icon_20mins);
        icon_8hrs = view.findViewById(R.id.icon_8hrs);
        icon_24hrs = view.findViewById(R.id.icon_24hrs);
        icon_48hrs = view.findViewById(R.id.icon_48hrs);
        icon_72hrs = view.findViewById(R.id.icon_72hrs);
        icon_1week = view.findViewById(R.id.icon_1week);
        icon_2weeks = view.findViewById(R.id.icon_2weeks);
        icon_3weeks = view.findViewById(R.id.icon_3weeks);
        icon_1month = view.findViewById(R.id.icon_1month);
        icon_3months = view.findViewById(R.id.icon_3months);
        icon_6months = view.findViewById(R.id.icon_6months);
        icon_1year = view.findViewById(R.id.icon_1year);

        secondsInMilli = 1000;
        minutesInMilli = secondsInMilli * 60;
        hoursInMilli = minutesInMilli * 60;
        daysInMilli = hoursInMilli * 24;

        SharedPreferences prefs = getActivity().getSharedPreferences(FOURTH_PREFS_NAME, 0);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(FOURTH_PREFS_NAME, 0).edit();

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
                                    databaseHelper = DatabaseHelper.getInstance(getActivity());

                                }

                                cursor = databaseHelper.getData();
                                if (cursor.getCount() == 0) {
                                } else {
                                    while (cursor.moveToNext()) {
                                        try {
                                            quit_date = cursor.getString(1);

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

                                            future_20mins = minutesInMilli * 20 / secondsInMilli;
                                            future_8hrs = hoursInMilli * 8 / secondsInMilli;
                                            future_24hrs = daysInMilli / secondsInMilli;
                                            future_48hrs = daysInMilli * 2 / secondsInMilli;
                                            future_72hrs = daysInMilli * 3 / secondsInMilli;
                                            future_1week = daysInMilli * 7 / secondsInMilli;
                                            future_2weeks = daysInMilli * 14 / secondsInMilli;
                                            future_3weeks = daysInMilli * 21 / secondsInMilli;
                                            future_1month = daysInMilli * 30 / secondsInMilli;
                                            future_3months = daysInMilli * 90 / secondsInMilli;
                                            future_6months = daysInMilli * 180 / secondsInMilli;
                                            future_1year = daysInMilli * 365 / secondsInMilli;


                                            cfuture_20mins = (int) (rawElapsedSeconds * 100 / future_20mins);
                                            cfuture_8hrs = (int) (rawElapsedSeconds * 100 / future_8hrs);
                                            cfuture_24hrs = (int) (rawElapsedSeconds * 100 / future_24hrs);
                                            cfuture_48hrs = (int) (rawElapsedSeconds * 100 / future_48hrs);
                                            cfuture_72hrs = (int) (rawElapsedSeconds * 100 / future_72hrs);
                                            cfuture_1week = (int) (rawElapsedSeconds * 100 / future_1week);
                                            cfuture_2weeks = (int) (rawElapsedSeconds * 100 / future_2weeks);
                                            cfuture_3weeks = (int) (rawElapsedSeconds * 100 / future_3weeks);
                                            cfuture_1month = (int) (rawElapsedSeconds * 100 / future_1month);
                                            cfuture_3months = (int) (rawElapsedSeconds * 100 / future_3months);
                                            cfuture_6months = (int) (rawElapsedSeconds * 100 / future_6months);


                                            String status_20mins = prefs.getString("status_20mins", "0");
                                            String status_8hrs = prefs.getString("status_8hrs", "0");
                                            String status_24hrs = prefs.getString("status_24hrs", "0");
                                            String status_48hrs = prefs.getString("status_48hrs", "0");
                                            String status_72hrs = prefs.getString("status_72hrs", "0");
                                            String status_1week = prefs.getString("status_1week", "0");
                                            String status_2weeks = prefs.getString("status_2weeks", "0");
                                            String status_3weeks = prefs.getString("status_3weeks", "0");
                                            String status_1month = prefs.getString("status_1month", "0");
                                            String status_3months = prefs.getString("status_3months", "0");
                                            String status_6months = prefs.getString("status_6months", "0");
                                            String status_1year = prefs.getString("status_1year", "0");

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
                                            if (rawElapsedSeconds >= future_24hrs && status_24hrs.equals("0")) {
                                                dialog_24hrs();
                                                editor.putString("status_24hrs", "1");
                                                editor.apply();
                                            }

                                            //48 hours
                                            if (rawElapsedSeconds < future_48hrs && rawElapsedSeconds > future_24hrs) {
                                                progress_arc.setProgress(cfuture_48hrs);
                                                progress_arc.setBottomText("48 hrs");
                                            }
                                            if (rawElapsedSeconds >= future_48hrs && status_48hrs.equals("0")) {
                                                dialog_48hrs();
                                                editor.putString("status_48hrs", "1");
                                                editor.apply();
                                            }

                                            //72 hours
                                            if (rawElapsedSeconds < future_72hrs && rawElapsedSeconds > future_48hrs) {
                                                progress_arc.setProgress(cfuture_72hrs);
                                                progress_arc.setBottomText("72 hrs");
                                            }
                                            if (rawElapsedSeconds >= future_72hrs && status_72hrs.equals("0")) {
                                                dialog_72hrs();
                                                editor.putString("status_72hrs", "1");
                                                editor.apply();
                                            }

                                            //1 week
                                            if (rawElapsedSeconds < future_1week && rawElapsedSeconds > future_72hrs) {
                                                progress_arc.setProgress(cfuture_1week);
                                                progress_arc.setBottomText("1 week");
                                            }
                                            if (rawElapsedSeconds >= future_1week && status_1week.equals("0")) {
                                                dialog_1week();
                                                editor.putString("status_1week", "1");
                                                editor.apply();
                                            }

                                            //2 weeks
                                            if (rawElapsedSeconds < future_2weeks && rawElapsedSeconds > future_1week) {
                                                progress_arc.setProgress(cfuture_2weeks);
                                                progress_arc.setBottomText("2 weeks");
                                            }
                                            if (rawElapsedSeconds >= future_2weeks && status_2weeks.equals("0")) {
                                                dialog_2weeks();
                                                editor.putString("status_2weeks", "1");
                                                editor.apply();
                                            }

                                            //3 weeks
                                            if (rawElapsedSeconds < future_3weeks && rawElapsedSeconds > future_2weeks) {
                                                progress_arc.setProgress(cfuture_3weeks);
                                                progress_arc.setBottomText("3 weeks");
                                            }
                                            if (rawElapsedSeconds >= future_3weeks && status_3weeks.equals("0")) {
                                                dialog_3weeks();
                                                editor.putString("status_3weeks", "1");
                                                editor.apply();
                                            }

                                            //1 month
                                            if (rawElapsedSeconds < future_1month && rawElapsedSeconds > future_3weeks) {
                                                progress_arc.setProgress(cfuture_1month);
                                                progress_arc.setBottomText("1 month");
                                            }
                                            if (rawElapsedSeconds >= future_1month && status_1month.equals("0")) {
                                                dialog_1month();
                                                editor.putString("status_1month", "1");
                                                editor.apply();
                                            }

                                            //3 months
                                            if (rawElapsedSeconds < future_3months && rawElapsedSeconds > future_1month) {
                                                progress_arc.setProgress(cfuture_3months);
                                                progress_arc.setBottomText("3 months");
                                            }
                                            if (rawElapsedSeconds >= future_3months && status_3months.equals("0")) {
                                                dialog_3months();
                                                editor.putString("status_3months", "1");
                                                editor.apply();
                                            }

                                            //6 months
                                            if (rawElapsedSeconds < future_6months && rawElapsedSeconds > future_3months) {
                                                progress_arc.setProgress(cfuture_6months);
                                                progress_arc.setBottomText("6 months");
                                            }
                                            if (rawElapsedSeconds >= future_6months && status_6months.equals("0")) {
                                                dialog_6months();
                                                editor.putString("status_6months", "1");
                                                editor.apply();
                                            }

                                            //1 year
                                            if (rawElapsedSeconds < future_1year && rawElapsedSeconds > future_6months) {
                                                progress_arc.setProgress(cfuture_1year);
                                                progress_arc.setBottomText("1 year");
                                            }
                                            if (rawElapsedSeconds >= future_1year && status_1year.equals("0")) {
                                                dialog_1year();
                                                editor.putString("status_1year", "1");
                                                editor.apply();
                                            }
                                            if (rawElapsedSeconds > future_1year) {
                                                progress_arc.setFinishedStrokeColor(getResources().getColor(R.color.main_red));
                                                progress_arc.setProgress(100);
                                                progress_arc.setBottomText(elapsedDays + " days");
                                            }

                                            if (status_20mins.equals("1")) {
                                                icon_20mins.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_8hrs.equals("1")) {
                                                icon_8hrs.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_24hrs.equals("1")) {
                                                icon_24hrs.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_48hrs.equals("1")) {
                                                icon_48hrs.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_72hrs.equals("1")) {
                                                icon_72hrs.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_1week.equals("1")) {
                                                icon_1week.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_2weeks.equals("1")) {
                                                icon_2weeks.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_3weeks.equals("1")) {
                                                icon_3weeks.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_1month.equals("1")) {
                                                icon_1month.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_3months.equals("1")) {
                                                icon_3months.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_6months.equals("1")) {
                                                icon_6months.setImageResource(R.drawable.fourth_fragment_check);
                                            }
                                            if (status_1year.equals("1")) {
                                                icon_1year.setImageResource(R.drawable.fourth_fragment_check);
                                            }

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
                    Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
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
        dialog_20mins.setContentView(R.layout.dialog_20mins);
        dialog_20mins.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_20mins.setCancelable(false);
        dialog_20mins.setCanceledOnTouchOutside(false);

        Button button = dialog_20mins.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_20mins.dismiss());
        dialog_20mins.show();
    }

    private void dialog_8hrs() {
        dialog_8hrs.setContentView(R.layout.dialog_8hrs);
        dialog_8hrs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_8hrs.setCancelable(false);
        dialog_8hrs.setCanceledOnTouchOutside(false);

        Button button = dialog_8hrs.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_8hrs.dismiss());
        dialog_8hrs.show();
    }

    private void dialog_24hrs() {
        dialog_24hrs.setContentView(R.layout.dialog_24hrs);
        dialog_24hrs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_24hrs.setCancelable(false);
        dialog_24hrs.setCanceledOnTouchOutside(false);

        Button button = dialog_24hrs.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_24hrs.dismiss());
        dialog_24hrs.show();
    }

    private void dialog_48hrs() {
        dialog_48hrs.setContentView(R.layout.dialog_48hrs);
        dialog_48hrs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_48hrs.setCancelable(false);
        dialog_48hrs.setCanceledOnTouchOutside(false);

        Button button = dialog_48hrs.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_48hrs.dismiss());
        dialog_48hrs.show();
    }

    private void dialog_72hrs() {
        dialog_72hrs.setContentView(R.layout.dialog_72hrs);
        dialog_72hrs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_72hrs.setCancelable(false);
        dialog_72hrs.setCanceledOnTouchOutside(false);

        Button button = dialog_72hrs.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_72hrs.dismiss());
        dialog_72hrs.show();
    }

    private void dialog_1week() {
        dialog_1week.setContentView(R.layout.dialog_1week);
        dialog_1week.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_1week.setCancelable(false);
        dialog_1week.setCanceledOnTouchOutside(false);

        Button button = dialog_1week.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_1week.dismiss());
        dialog_1week.show();
    }

    private void dialog_2weeks() {
        dialog_2weeks.setContentView(R.layout.dialog_2weeks);
        dialog_2weeks.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_2weeks.setCancelable(false);
        dialog_2weeks.setCanceledOnTouchOutside(false);

        Button button = dialog_2weeks.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_2weeks.dismiss());
        dialog_2weeks.show();
    }

    private void dialog_3weeks() {
        dialog_3weeks.setContentView(R.layout.dialog_3weeks);
        dialog_3weeks.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_3weeks.setCancelable(false);
        dialog_3weeks.setCanceledOnTouchOutside(false);

        Button button = dialog_3weeks.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_3weeks.dismiss());
        dialog_3weeks.show();
    }

    private void dialog_1month() {
        dialog_1month.setContentView(R.layout.dialog_1month);
        dialog_1month.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_1month.setCancelable(false);
        dialog_1month.setCanceledOnTouchOutside(false);

        Button button = dialog_1month.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_1month.dismiss());
        dialog_1month.show();
    }

    private void dialog_3months() {
        dialog_3months.setContentView(R.layout.dialog_3months);
        dialog_3months.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_3months.setCancelable(false);
        dialog_3months.setCanceledOnTouchOutside(false);

        Button button = dialog_3months.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_3months.dismiss());
        dialog_3months.show();
    }

    private void dialog_6months() {
        dialog_6months.setContentView(R.layout.dialog_6months);
        dialog_6months.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_6months.setCancelable(false);
        dialog_6months.setCanceledOnTouchOutside(false);

        Button button = dialog_6months.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_6months.dismiss());
        dialog_6months.show();
    }

    private void dialog_1year() {
        dialog_1year.setContentView(R.layout.dialog_1year);
        dialog_1year.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_1year.setCancelable(false);
        dialog_1year.setCanceledOnTouchOutside(false);

        Button button = dialog_1year.findViewById(R.id.button);
        button.setOnClickListener(view -> dialog_1year.dismiss());
        dialog_1year.show();
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