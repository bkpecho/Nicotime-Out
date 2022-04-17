package com.nicotimeout.app.user;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.nicotimeout.app.database.UserModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class thirdFragment extends Fragment {
    TextView fragment_third_days,
            fragment_third_hours,
            fragment_third_mins,
            fragment_third_secs,
            cigarettesSmoked,
            moneyWasted,
            lifeLost;

    Button btnClickMe;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        //int user_id, String quit_date, int cig_per_day, int cig_price, int cig_years

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        Cursor cursor = databaseHelper.getData();
        cigarettesSmoked = view.findViewById(R.id.cigarettesSmoked);
        moneyWasted = view.findViewById(R.id.moneyWasted);
        lifeLost = view.findViewById(R.id.lifeLost);
        QuestionActivity questionActivity = new QuestionActivity();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = calendar.getTime();
        DateFormat date = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        date.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        String localTime = date.format(currentLocalTime);

        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "WALANG LAMAN KINGINA MO", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                try {
                    fragment_third_days = view.findViewById(R.id.fragment_third_days);
                    fragment_third_hours = view.findViewById(R.id.fragment_third_hours);
                    fragment_third_mins = view.findViewById(R.id.fragment_third_mins);
                    fragment_third_secs = view.findViewById(R.id.fragment_third_secs);

                    String quit_date = cursor.getString(1);
                    String cig_per_day = cursor.getString(2);
                    String cig_price = cursor.getString(3);
                    String cig_years = cursor.getString(4);

                    Integer data_cigarettesSmoked = Integer.parseInt(cig_per_day) * 360 * Integer.parseInt(cig_years);
                    Integer data_moneyWasted = data_cigarettesSmoked * Integer.parseInt(cig_price);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                    Date startDate = simpleDateFormat.parse(quit_date);
                    Date endDate = simpleDateFormat.parse(localTime);

                    long different = endDate.getTime() - startDate.getTime();

                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;
                    long daysInMilli = hoursInMilli * 24;

                    long elapsedDays = different / daysInMilli;
                    different = different % daysInMilli;

                    long elapsedHours = different / hoursInMilli;
                    different = different % hoursInMilli;

                    long elapsedMinutes = different / minutesInMilli;
                    different = different % minutesInMilli;

                    long elapsedSeconds = different / secondsInMilli;

                    cigarettesSmoked.setText(String.valueOf(data_cigarettesSmoked));
                    moneyWasted.setText(String.valueOf("₱" + data_moneyWasted));
                    //  Toast.makeText(getActivity(), String.valueOf(startDate), Toast.LENGTH_SHORT).show();
                    fragment_third_days.setText(String.valueOf(elapsedDays));
                    fragment_third_hours.setText(String.valueOf(elapsedHours));
                    fragment_third_mins.setText(String.valueOf(elapsedMinutes));
                    fragment_third_secs.setText(String.valueOf(elapsedSeconds));

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "SHET MALI", Toast.LENGTH_SHORT).show();
                    Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0xFFdb3056);

        }

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        // set text to your TextView
//
//        try {
//            Date startDate = simpleDateFormat.parse(String.valueOf(everyone.get(1)));
//            Date endDate = simpleDateFormat.parse(localTime);
//
//            long different = endDate.getTime() - startDate.getTime();
//
//            long secondsInMilli = 1000;
//            long minutesInMilli = secondsInMilli * 60;
//            long hoursInMilli = minutesInMilli * 60;
//            long daysInMilli = hoursInMilli * 24;
//
//            long elapsedDays = different / daysInMilli;
//            different = different % daysInMilli;
//
//            long elapsedHours = different / hoursInMilli;
//            different = different % hoursInMilli;
//
//            long elapsedMinutes = different / minutesInMilli;
//            different = different % minutesInMilli;
//
//            long elapsedSeconds = different / secondsInMilli;


//        fragment_third_days.setText(String.valueOf(everyone.get(1)));
//            fragment_third_hours.setText(String.valueOf(elapsedHours));
//            fragment_third_mins.setText(String.valueOf(elapsedMinutes));
//            fragment_third_secs.setText(String.valueOf(elapsedSeconds));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        super.onViewCreated(view, savedInstanceState);
    }
//

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

//    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
//    List<UserModel> everyone = databaseHelper.getEveryone();
//
//    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
//    Date currentLocalTime = calendar.getTime();
//    DateFormat date = new SimpleDateFormat("yyMMddHHmmss");
//        date.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//
//                String localTime = date.format(currentLocalTime);
//
//                thirdFragment obj = new thirdFragment();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
//
//                try {
//                Date date1 = simpleDateFormat.parse(String.valueOf(everyone.get(1)));
//                Date date2 = simpleDateFormat.parse(localTime);
//
//                obj.printDifference(date1, date2);
//
//                } catch (ParseException e) {
//                e.printStackTrace();
//                }

//1 minute = 60 seconds
//1 hour = 60 x 60 = 3600
//1 day = 3600 x 24 = 86400
//    public void printDifference(Date startDate, Date endDate) {
//        //milliseconds
//        long different = endDate.getTime() - startDate.getTime();
//        System.out.println("startDate : " + startDate);
//        System.out.println("endDate : " + endDate);
//        System.out.println("different : " + different);
//
//        long secondsInMilli = 1000;
//        long minutesInMilli = secondsInMilli * 60;
//        long hoursInMilli = minutesInMilli * 60;
//        long daysInMilli = hoursInMilli * 24;
//
//        long elapsedDays = different / daysInMilli;
//        different = different % daysInMilli;
//
//        long elapsedHours = different / hoursInMilli;
//        different = different % hoursInMilli;
//
//        long elapsedMinutes = different / minutesInMilli;
//        different = different % minutesInMilli;
//
//        long elapsedSeconds = different / secondsInMilli;
//        System.out.printf(
//                "%d days, %d hours, %d minutes, %d seconds%n",
//                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
//    }