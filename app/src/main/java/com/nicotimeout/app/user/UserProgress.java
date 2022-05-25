package com.nicotimeout.app.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nicotimeout.app.R;
import com.nicotimeout.app.userStart.StartActivity;

public class UserProgress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_progress);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.thirdFragment);

        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, StartActivity.class);
        startActivity(i);
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.firstFragment:
                        selectedFragment = new firstFragment();
                        break;
                    case R.id.thirdFragment:
                        selectedFragment = new thirdFragment();
                        break;
                    case R.id.fourthFragment:
                        selectedFragment = new fourthFragment();
                        break;
                    case R.id.fifthFragment:
                        selectedFragment = new fifthFragment();
                        break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
                            selectedFragment).commit();
                }
                return true;
            };

}