package com.example.clomi.activities.plant;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.dashboard.DashboardActivity;
import com.example.clomi.activities.habits.HabitsActivity;
import com.example.clomi.activities.profile.ProfileActivity;
import com.example.clomi.activities.reports.ProgressActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlantActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        setupBottomNavigation();
    }

    private void setupBottomNavigation() {

        bottomNavigation.setSelectedItemId(R.id.navigation_plant);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.navigation_home) {

                startActivity(new Intent(this, DashboardActivity.class));
                finish();
                return true;

            } else if (id == R.id.navigation_habits) {

                startActivity(new Intent(this, HabitsActivity.class));
                finish();
                return true;

            } else if (id == R.id.navigation_plant) {

                return true;

            } else if (id == R.id.navigation_reports) {

                startActivity(new Intent(this, ProgressActivity.class));
                finish();
                return true;

            } else if (id == R.id.navigation_profile) {

                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }

            return false;
        });

    }
}