package com.nicotimeout.app.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nicotimeout.app.R;
import com.nicotimeout.app.user.fifthFragment.fifthFragment;
import com.nicotimeout.app.user.firstFragment.firstFragment;
import com.nicotimeout.app.user.fourthFragment.fourthFragment;
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