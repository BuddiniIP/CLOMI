package com.example.clomi.activities.dashboard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvGreeting;
    private TextView tvUserName;
    private TextView tvQuote;
    private TextView tvProgressPercent;

    private TextView tvWaterProgress;
    private TextView tvSleepProgress;
    private TextView tvXP;
    private TextView tvPlantStatus;
    private TextView tvMission;

    private LinearProgressIndicator progressToday;

    private BottomNavigationView bottomNavigation;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        loadDashboard();
        setupBottomNavigation();
    }

    private void initializeViews() {

        tvGreeting = findViewById(R.id.tvGreeting);
        tvUserName = findViewById(R.id.tvUserName);
        tvQuote = findViewById(R.id.tvQuote);

        tvProgressPercent = findViewById(R.id.tvProgressPercent);

        progressToday = findViewById(R.id.progressToday);

        tvWaterProgress = findViewById(R.id.tvWaterProgress);
        tvSleepProgress = findViewById(R.id.tvSleepProgress);

        tvXP = findViewById(R.id.tvXP);

        tvPlantStatus = findViewById(R.id.tvPlantStatus);

        tvMission = findViewById(R.id.tvMission);

        bottomNavigation = findViewById(R.id.bottomNavigation);

    }

    private void loadDashboard() {

        String name = preferences.getString(
                PreferenceKeys.USER_NAME,
                "User"
        );

        tvUserName.setText(name + " 🌸");

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour >= 5 && hour < 12) {

            tvGreeting.setText("☀ Good Morning");

        } else if (hour >= 12 && hour < 17) {

            tvGreeting.setText("🌤 Good Afternoon");

        } else {

            tvGreeting.setText("🌙 Good Evening");

        }

        tvQuote.setText("Small habits create big changes.");

        int progress = 0;

        progressToday.setProgress(progress);

        tvProgressPercent.setText(progress + "%");

        String waterGoal = preferences.getString(
                PreferenceKeys.WATER_GOAL,
                "2.5 L"
        );

        tvWaterProgress.setText(waterGoal);

        String sleepGoal = preferences.getString(
                PreferenceKeys.SLEEP_HOURS,
                "8 Hours"
        );

        tvSleepProgress.setText(sleepGoal);

        tvXP.setText("0 XP");

        tvPlantStatus.setText("Healthy 🌱");

        tvMission.setText("Complete today's habits.");
    }

    private void setupBottomNavigation() {

        bottomNavigation.setSelectedItemId(R.id.navigation_home);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.navigation_home) {

                return true;

            } else if (id == R.id.navigation_habits) {

                // TODO: Open HabitsActivity

                return true;

            } else if (id == R.id.navigation_plant) {

                // TODO: Open PlantActivity

                return true;

            } else if (id == R.id.navigation_reports) {

                // TODO: Open ProgressActivity

                return true;

            } else if (id == R.id.navigation_profile) {

                // TODO: Open ProfileActivity

                return true;

            }

            return false;

        });

    }

}