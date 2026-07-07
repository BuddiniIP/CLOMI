package com.example.clomi.activities.reports;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.dashboard.DashboardActivity;
import com.example.clomi.activities.habits.HabitsActivity;
import com.example.clomi.activities.profile.ProfileActivity;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class ProgressActivity extends AppCompatActivity {

    private TextView tvTodayProgress;
    private TextView tvWeek;
    private TextView tvMonth;

    private TextView tvXP;
    private TextView tvStreak;
    private TextView tvPlant;

    private LinearProgressIndicator progressToday;

    private BottomNavigationView bottomNavigation;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        loadReport();
        setupBottomNavigation();
    }

    private void initializeViews() {

        tvTodayProgress = findViewById(R.id.tvTodayProgress);
        tvWeek = findViewById(R.id.tvWeek);
        tvMonth = findViewById(R.id.tvMonth);

        tvXP = findViewById(R.id.tvXP);
        tvStreak = findViewById(R.id.tvStreak);
        tvPlant = findViewById(R.id.tvPlant);

        progressToday = findViewById(R.id.progressToday);

        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void loadReport() {

        int todayProgress = 0;

        progressToday.setProgress(todayProgress);

        tvTodayProgress.setText(todayProgress + "%");

        tvWeek.setText("0%");
        tvMonth.setText("0%");

        int xp = preferences.getInt(PreferenceKeys.XP);
        int streak = preferences.getInt(PreferenceKeys.STREAK);

        tvXP.setText("⭐ Total XP : " + xp);

        tvStreak.setText("🔥 Current Streak : " + streak + " Days");

        String plantLevel;

        if (xp >= 300) {

            plantLevel = "Blooming 🌸";

        } else if (xp >= 200) {

            plantLevel = "Growing 🌿";

        } else if (xp >= 100) {

            plantLevel = "Sprout 🌱";

        } else {

            plantLevel = "Seed 🌰";

        }

        tvPlant.setText("🌱 Plant Level : " + plantLevel);
    }

    private void setupBottomNavigation() {

        bottomNavigation.setSelectedItemId(R.id.navigation_reports);

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

            } else if (id == R.id.navigation_reports) {

                return true;

            } else if (id == R.id.navigation_profile) {

                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReport();
    }
}