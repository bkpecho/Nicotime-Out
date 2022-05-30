package com.nicotimeout.app.user;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.nicotimeout.app.R;
import com.nicotimeout.app.database.DatabaseHelper;
import com.nicotimeout.app.userStart.StartActivity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;


public class thirdFragment extends Fragment {
    TextView fragment_third_days,
            fragment_third_hours,
            fragment_third_mins,
            fragment_third_secs,
            cigarettesAvoided,
            moneySaved,
            lifeRegained_days,
            lifeRegained_hours,
            lifeRegained_minutes,
            cigarettesSmoked,
            moneyWasted,
            lifeLost_days,
            lifeLost_hours,
            lifeLost_minutes;


    long data_cigarettesAvoided;
    double data_cigarettesAvoided_double;
    double progress_cigarettesAvoided;

    double data_moneySaved;
    double progress_moneySaved;

    long progress_lifeRegained_computation;
    double progress_lifeRegained_computation_minutes;
    long progress_lifeRegained_days;
    long progress_lifeRegained_hours;
    long progress_lifeRegained_minutes;

    long data_cigarettesSmoked;
    long data_moneyWasted;

    long progress_lifeLost_computation;
    long progress_lifeLost_computation_minutes;
    long progress_lifeLost_days;
    long progress_lifeLost_hours;
    long progress_lifeLost_minutes;

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

    long reset;
    long counter;

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
    Dialog ac_blastoff;
    Dialog ac_thumbsup;
    Dialog ac_richman;
    Dialog ac_focused;
    Dialog ac_30daysofsuccess;
    Dialog ac_recoverymedal;
    Dialog ac_recoverychampion;
    Dialog ac_sturdyasashield;
    Dialog ac_standtall;
    Dialog reset_dialog;

    public static final String PREF_LOGIN_COUNTER = "loginCounter";
    public static final String PREF_ACHIEVEMENTS_COUNTER = "achievementsCounter";
    public static final String FOURTH_PREFS_NAME = "MyPrefsFile";

    ImageButton imageButton;
    LinearLayout nonsmokersince;
    GridLayout grid1;
    GridLayout grid2;
    //achievement variables

    double ms_amount;
    double ca_amount;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        imageButton = view.findViewById(R.id.threedots);
        imageButton.setOnClickListener(view1 -> reset_dialog());

        nonsmokersince = view.findViewById(R.id.nonsmokersince);
        grid1 = view.findViewById(R.id.grid1);
        grid2 = view.findViewById(R.id.grid2);

        cigarettesAvoided = view.findViewById(R.id.cigarettesAvoided);
        moneySaved = view.findViewById(R.id.moneySaved);

        /*lifeRegained*/
        lifeRegained_days = view.findViewById(R.id.lifeRegained_days);
        lifeRegained_hours = view.findViewById(R.id.lifeRegained_hours);
        lifeRegained_minutes = view.findViewById(R.id.lifeRegained_minutes);

        cigarettesSmoked = view.findViewById(R.id.cigarettesSmoked);
        moneyWasted = view.findViewById(R.id.moneyWasted);

        /*lifeLost*/
        lifeLost_days = view.findViewById(R.id.lifeLost_days);
        lifeLost_hours = view.findViewById(R.id.lifeLost_hours);
        lifeLost_minutes = view.findViewById(R.id.lifeLost_minutes);


        fragment_third_days = view.findViewById(R.id.fragment_third_days);
        fragment_third_hours = view.findViewById(R.id.fragment_third_hours);
        fragment_third_mins = view.findViewById(R.id.fragment_third_mins);
        fragment_third_secs = view.findViewById(R.id.fragment_third_secs);

        secondsInMilli = 1000;
        minutesInMilli = secondsInMilli * 60;
        hoursInMilli = minutesInMilli * 60;
        daysInMilli = hoursInMilli * 24;

        //sharedpreferences counter
        SharedPreferences prefs_counter = getActivity().getSharedPreferences(PREF_LOGIN_COUNTER, 0);
        SharedPreferences.Editor prefs_editor = getActivity().getSharedPreferences(PREF_LOGIN_COUNTER, 0).edit();

        counter = prefs_counter.getLong("counter", 0);
        reset = prefs_counter.getLong("reset", 0);

        //sharedpreferences achievements
        SharedPreferences prefs_achievements = getActivity().getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0);
        SharedPreferences.Editor achievements_editor = getActivity().getSharedPreferences(PREF_ACHIEVEMENTS_COUNTER, 0).edit();




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
                                            data_cigarettesAvoided_double = rawElapsedHours * progress_cigarettesAvoided;
                                            //roundedProgress_data_cigarettesAvoided = Math.round(data_cigarettesAvoided);
                                            if (isAdded()) {
                                                String ca_number = String.valueOf(data_cigarettesAvoided);
                                                ca_amount = Double.parseDouble(ca_number);
                                                DecimalFormat ca_formatter = new DecimalFormat("#,###");
                                                String ca_formatted = ca_formatter.format(ca_amount);
                                                cigarettesAvoided.setText(ca_formatted);
                                            }

                                            /*moneySaved*/
                                            progress_moneySaved = Double.parseDouble(cig_price) * Double.parseDouble(cig_per_day) / 1440;
                                            data_moneySaved = rawElapsedMinutes * progress_moneySaved;
                                            if (isAdded()) {
                                                String ms_number = String.valueOf(data_moneySaved);
                                                ms_amount = Double.parseDouble(ms_number);
                                                DecimalFormat ms_formatter = new DecimalFormat("₱#,###.##");
                                                String ms_formatted = ms_formatter.format(ms_amount);
                                                moneySaved.setText(ms_formatted);
                                            }

                                            /*lifeRegained*/
                                            progress_lifeRegained_computation = data_cigarettesAvoided * 11;
                                            progress_lifeRegained_computation_minutes = data_cigarettesAvoided_double * 11;

                                            progress_lifeRegained_days = progress_lifeRegained_computation / 24 / 60;
                                            progress_lifeRegained_hours = progress_lifeRegained_computation / 60 % 24;
                                            progress_lifeRegained_minutes = progress_lifeRegained_computation % 60;
                                            if (isAdded()) {
                                                lifeRegained_days.setText(getString(R.string.lifeRegained_days,
                                                        String.valueOf(progress_lifeRegained_days)));
                                                lifeRegained_hours.setText(getString(R.string.lifeRegained_hours,
                                                        String.valueOf(progress_lifeRegained_hours)));
                                                lifeRegained_minutes.setText(getString(R.string.lifeRegained_minutes,
                                                        String.valueOf(progress_lifeRegained_minutes)));
                                            }

                                            /*lifeLost*/
                                            progress_lifeLost_computation = data_cigarettesSmoked * 11;
                                            progress_lifeLost_computation_minutes = data_cigarettesSmoked * 11;

                                            progress_lifeLost_days = progress_lifeLost_computation / 24 / 60;
                                            progress_lifeLost_hours = progress_lifeLost_computation / 60 % 24;
                                            progress_lifeLost_minutes = Math.round(progress_lifeLost_computation_minutes % 60);

                                            if (isAdded()) {
                                                lifeLost_days.setText(getString(R.string.lifeLost_days,
                                                        String.valueOf(progress_lifeLost_days)));
                                                lifeLost_hours.setText(getString(R.string.lifeLost_hours,
                                                        String.valueOf(progress_lifeLost_hours)));
                                                lifeLost_minutes.setText(getString(R.string.lifeLost_minutes,
                                                        String.valueOf(progress_lifeLost_minutes)));
                                            }
                                            /*cigarettesSmoked*/
                                            data_cigarettesSmoked = Long.parseLong(cig_per_day) * 365 * Long.parseLong(cig_years);
                                            if (isAdded()) {
                                                String cs_number = String.valueOf(data_cigarettesSmoked);
                                                double cs_amount = Double.parseDouble(cs_number);
                                                DecimalFormat cs_formatter = new DecimalFormat("#,###");
                                                String cs_formatted = cs_formatter.format(cs_amount);
                                                cigarettesSmoked.setText(cs_formatted);
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

                                            fragment_third_days.setText(String.valueOf(elapsedDays));
                                            fragment_third_hours.setText(String.valueOf(elapsedHours));
                                            fragment_third_mins.setText(String.valueOf(elapsedMinutes));
                                            fragment_third_secs.setText(String.valueOf(elapsedSeconds));

                                            long pref_thumbsup = prefs_achievements.getLong("pref_thumbsup", 0);
                                            if (ms_amount >= 500 && pref_thumbsup == 0) {
                                                ac_thumbsup();
                                                achievements_editor.putLong("pref_thumbsup", 1);
                                                achievements_editor.apply();
                                            }

                                            long pref_richman = prefs_achievements.getLong("pref_richman", 0);
                                            if (ms_amount >= 1000 && pref_richman == 0) {
                                                ac_richman();
                                                achievements_editor.putLong("pref_richman", 1);
                                                achievements_editor.apply();
                                            }

                                            long pref_recoverymedal = prefs_achievements.getLong("pref_recoverymedal", 0);
                                            if (progress_lifeRegained_days >= 1 && pref_recoverymedal == 0) {
                                                ac_recoverymedal();
                                                achievements_editor.putLong("pref_recoverymedal", 1);
                                                achievements_editor.apply();
                                            }

                                            long pref_recoverychampion = prefs_achievements.getLong("pref_recoverychampion", 0);
                                            if (progress_lifeRegained_days >= 7 && pref_recoverychampion == 0) {
                                                ac_recoverychampion();
                                                achievements_editor.putLong("pref_recoverychampion", 1);
                                                achievements_editor.apply();
                                            }

                                            long pref_sturdyasashield = prefs_achievements.getLong("pref_sturdyasashield", 0);
                                            if (ca_amount >= 100 && pref_sturdyasashield == 0) {
                                                ac_sturdyasashield();
                                                achievements_editor.putLong("pref_sturdyasashield", 1);
                                                achievements_editor.apply();
                                            }

                                            long pref_standtall = prefs_achievements.getLong("pref_standtall", 0);
                                            if (ca_amount >= 1000 && pref_standtall == 0) {
                                                ac_standtall();
                                                achievements_editor.putLong("pref_standtall", 1);
                                                achievements_editor.apply();
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
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        };

        thread.start();

        ac_blastoff = new Dialog(getActivity());
        ac_thumbsup = new Dialog(getActivity());
        ac_richman = new Dialog(getActivity());
        ac_focused = new Dialog(getActivity());
        ac_30daysofsuccess = new Dialog(getActivity());
        ac_recoverymedal = new Dialog(getActivity());
        ac_recoverychampion = new Dialog(getActivity());
        ac_sturdyasashield = new Dialog(getActivity());
        ac_standtall = new Dialog(getActivity());
        reset_dialog = new Dialog(getActivity());

        long pref_blastoff = prefs_achievements.getLong("pref_blastoff", 0);
        if (pref_blastoff == 0) {
            ac_blastoff();
            achievements_editor.putLong("pref_blastoff", 1);
            achievements_editor.apply();
        }


        long pref_focused = prefs_achievements.getLong("pref_focused", 0);
        if (counter >= 3 && pref_focused == 0) {
            ac_focused();
            achievements_editor.putLong("pref_focused", 1);
            achievements_editor.apply();
        }

        long pref_30daysofsuccess = prefs_achievements.getLong("pref_30daysofsuccess", 0);
        if (counter >= 30 && pref_30daysofsuccess == 0) {
            ac_30daysofsuccess();
            achievements_editor.putLong("pref_30daysofsuccess", 1);
            achievements_editor.apply();
        }

        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xFF299cfc);

        notificationChannel();

        return view;

    }

    private void reset_dialog() {
        reset_dialog.setContentView(R.layout.reset_dialog);
        reset_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        reset_dialog.setCancelable(false);
        reset_dialog.setCanceledOnTouchOutside(false);


        Button button_no = reset_dialog.findViewById(R.id.button_no);
        button_no.setOnClickListener(view -> reset_dialog.dismiss());

        Button button_yes = reset_dialog.findViewById(R.id.button_yes);
        button_yes.setOnClickListener(view -> {
            //reset editor
            SharedPreferences prefs_counter = getActivity().getSharedPreferences(PREF_LOGIN_COUNTER, 0);
            SharedPreferences.Editor prefs_editor = getActivity().getSharedPreferences(PREF_LOGIN_COUNTER, 0).edit();
            reset = prefs_counter.getLong("reset", 0);
            long addReset = reset + 1;

            SharedPreferences.Editor editor = getActivity().getSharedPreferences(FOURTH_PREFS_NAME, 0).edit();


            prefs_editor.putLong("reset", addReset);
            editor.clear();

            prefs_editor.apply();
            editor.apply();

            databaseHelper = new DatabaseHelper(getActivity());
            databaseHelper.deleteAll();
            reset_dialog.dismiss();
            getActivity().getSupportFragmentManager().beginTransaction().remove(thirdFragment.this).commit();
            getActivity().onBackPressed();

            resetNotification();
        });
        reset_dialog.show();
    }
    private void notificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Nicotime-Out!";
            String description = "Channel for Nicotime-Out!";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void resetNotification() {
        String contentTitle1 = "I Slipped Up, Now What?";
        String contentTitle2 = "Don’t Give Up!";
        String contentText1 = "Most slip-ups occur within the first week of trying to quit smoking. Just because you take a puff or two of a cigarette or slide into a full-blown relapse doesn't mean that you can’t begin again. The important thing to remember is that you’re still in control and can move forward in your efforts to quit smoking.";
        String contentText2 = "Most people try several times before succeeding. If you have relapsed, treat this incident as something to learn from, and an experience that you can use later on.";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "CHANNEL_ID")
                .setSmallIcon(R.drawable.idea)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        if (reset == 0) {
            builder.setContentTitle(contentTitle1);
            builder.setContentText(contentText1);
            builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(contentText1));
        } else {
            builder.setContentTitle(contentTitle2);
            builder.setContentText(contentText2);
            builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(contentText2));
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
        notificationManager.notify(1, builder.build());
    }

    private void ac_blastoff() {
        ac_blastoff.setContentView(R.layout.ac_blastoff);
        ac_blastoff.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_blastoff.setCancelable(false);
        ac_blastoff.setCanceledOnTouchOutside(false);

        Button button = ac_blastoff.findViewById(R.id.button);
        button.setOnClickListener(view -> {
            ac_blastoff.dismiss();
            new MaterialIntroView.Builder(getActivity())
                    .enableIcon(false)
                    .setFocusGravity(FocusGravity.CENTER)
                    .setFocusType(Focus.ALL)
                    .enableFadeAnimation(true)
                    .performClick(false)
                    .setIdempotent(true)
                    .setInfoText("Keep track of your progress.")
                    .setInfoTextSize(14)
                    .setTextColor(getResources().getColor(R.color.main_blue))
                    .setShape(ShapeType.RECTANGLE)
                    .setTarget(nonsmokersince)
                    .setUsageId("nonsmokersince")
                    .setListener(materialIntroViewId -> new MaterialIntroView.Builder(getActivity())
                            .enableIcon(false)
                            .setFocusGravity(FocusGravity.CENTER)
                            .setFocusType(Focus.ALL)
                            .enableFadeAnimation(true)
                            .performClick(false)
                            .setIdempotent(true)
                            .setInfoText("Reap the rewards of Quitting Smoking.")
                            .setInfoTextSize(14)
                            .setTextColor(getResources().getColor(R.color.main_blue))
                            .setShape(ShapeType.RECTANGLE)
                            .setTarget(grid1)
                            .setUsageId("grid1")
                            .setListener(materialIntroViewId1 -> new MaterialIntroView.Builder(getActivity())
                                    .enableIcon(false)
                                    .setFocusGravity(FocusGravity.CENTER)
                                    .setFocusType(Focus.ALL)
                                    .enableFadeAnimation(true)
                                    .performClick(false)
                                    .setIdempotent(true)
                                    .setInfoText("These are some of the damages caused by your smoking.")
                                    .setInfoTextSize(14)
                                    .setTextColor(getResources().getColor(R.color.main_red))
                                    .setShape(ShapeType.RECTANGLE)
                                    .setTarget(grid2)
                                    .setUsageId("grid2")
                                    .show())
                            .show())
                    .show();
        });
        ac_blastoff.show();
    }

    private void ac_thumbsup() {
        ac_thumbsup.setContentView(R.layout.ac_thumbsup);
        ac_thumbsup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_thumbsup.setCancelable(false);
        ac_thumbsup.setCanceledOnTouchOutside(false);


        Button button = ac_thumbsup.findViewById(R.id.button);
        button.setOnClickListener(view -> ac_thumbsup.dismiss());
        ac_thumbsup.show();
    }

    private void ac_richman() {
        ac_richman.setContentView(R.layout.ac_richman);
        ac_richman.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_richman.setCancelable(false);
        ac_richman.setCanceledOnTouchOutside(false);


        Button button = ac_richman.findViewById(R.id.button);
        button.setOnClickListener(view -> ac_richman.dismiss());
        ac_richman.show();
    }

    private void ac_focused() {
        ac_focused.setContentView(R.layout.ac_focused);
        ac_focused.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_focused.setCancelable(false);
        ac_focused.setCanceledOnTouchOutside(false);


        Button button = ac_focused.findViewById(R.id.button);
        button.setOnClickListener(view -> ac_focused.dismiss());
        ac_focused.show();
    }

    private void ac_30daysofsuccess() {
        ac_30daysofsuccess.setContentView(R.layout.ac_30daysofsuccess);
        ac_30daysofsuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_30daysofsuccess.setCancelable(false);
        ac_30daysofsuccess.setCanceledOnTouchOutside(false);


        Button button = ac_30daysofsuccess.findViewById(R.id.button);
        button.setOnClickListener(view -> ac_30daysofsuccess.dismiss());
        ac_30daysofsuccess.show();
    }

    private void ac_recoverymedal() {
        ac_recoverymedal.setContentView(R.layout.ac_recoverymedal);
        ac_recoverymedal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_recoverymedal.setCancelable(false);
        ac_recoverymedal.setCanceledOnTouchOutside(false);


        Button button = ac_recoverymedal.findViewById(R.id.button);
        button.setOnClickListener(view -> ac_recoverymedal.dismiss());
        ac_recoverymedal.show();
    }

    private void ac_recoverychampion() {
        ac_recoverychampion.setContentView(R.layout.ac_recoverychampion);
        ac_recoverychampion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_recoverychampion.setCancelable(false);
        ac_recoverychampion.setCanceledOnTouchOutside(false);


        Button button = ac_recoverychampion.findViewById(R.id.button);
        button.setOnClickListener(view -> ac_recoverychampion.dismiss());
        ac_recoverychampion.show();
    }

    private void ac_sturdyasashield() {
        ac_sturdyasashield.setContentView(R.layout.ac_sturdyasashield);
        ac_sturdyasashield.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_sturdyasashield.setCancelable(false);
        ac_sturdyasashield.setCanceledOnTouchOutside(false);


        Button button = ac_sturdyasashield.findViewById(R.id.button);
        button.setOnClickListener(view -> ac_sturdyasashield.dismiss());
        ac_sturdyasashield.show();
    }

    private void ac_standtall() {
        ac_standtall.setContentView(R.layout.ac_standtall);
        ac_standtall.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ac_standtall.setCancelable(false);
        ac_standtall.setCanceledOnTouchOutside(false);


        Button button = ac_standtall.findViewById(R.id.button);
        button.setOnClickListener(view -> ac_standtall.dismiss());
        ac_standtall.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
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