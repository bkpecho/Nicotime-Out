package com.nicotimeout.app.userStart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.nicotimeout.app.R;
import com.nicotimeout.app.common.QuestionActivity;

import org.joda.time.DateTime;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREF_LOGIN_COMPARE = "loginCompare";
    public static final String PREF_LOGIN_COUNTER = "loginCounter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        CardView cv1 = findViewById(R.id.activity_start_cardview1);
        CardView cv2 = findViewById(R.id.activity_start_cardview2);
        CardView cv3 = findViewById(R.id.activity_start_cardview3);

        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);

        SharedPreferences prefs = getSharedPreferences(PREF_LOGIN_COUNTER, 0);
        SharedPreferences.Editor counter_editor = getSharedPreferences(PREF_LOGIN_COUNTER, 0).edit();
        long counter = prefs.getLong("counter", 0);

        SharedPreferences settings = getSharedPreferences(PREF_LOGIN_COMPARE, 0);
        SharedPreferences.Editor compare_editor = getSharedPreferences(PREF_LOGIN_COMPARE, 0).edit();
        long lastSaveTime = settings.getLong("last_save", 0);
        int today = DateTime.now().getDayOfYear();

        if (lastSaveTime < today) {
            compare_editor.putLong("last_save", today);
            long add = counter + 1;
            counter_editor.putLong("counter", add);

            compare_editor.apply();
            counter_editor.apply();
        }
        if(lastSaveTime>today){
            compare_editor.putLong("last_save", today);
            long add = counter + 1;
            counter_editor.putLong("counter", add);

            compare_editor.apply();
            counter_editor.apply();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.activity_start_cardview1:
                i = new Intent(this, StartActivity_first.class);
                startActivity(i);
                break;
            case R.id.activity_start_cardview2:
                i = new Intent(this, StartActivity_first.class);
                startActivity(i);
                break;
            case R.id.activity_start_cardview3:
                i = new Intent(this, QuestionActivity.class);
                startActivity(i);
                break;
        }
    }
}