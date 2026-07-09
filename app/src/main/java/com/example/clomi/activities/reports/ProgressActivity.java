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
    private TextView tvLevel;
    private TextView tvStreak;

    private TextView tvPlant;
    private TextView tvPlantDescription;

    private LinearProgressIndicator progressToday;
    private LinearProgressIndicator progressPlant;

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
        tvLevel = findViewById(R.id.tvLevel);
        tvStreak = findViewById(R.id.tvStreak);

        tvPlant = findViewById(R.id.tvPlant);
        tvPlantDescription = findViewById(R.id.tvPlantDescription);

        progressToday = findViewById(R.id.progressToday);
        progressPlant = findViewById(R.id.progressPlant);

        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void loadReport() {

        int todayProgress = 0;

        int xp = preferences.getInt(
                PreferenceKeys.XP,
                0
        );

        int streak = preferences.getInt(
                PreferenceKeys.STREAK,
                0
        );

        progressToday.setProgressCompat(todayProgress, true);

        tvTodayProgress.setText(todayProgress + "%");

        tvWeek.setText("0%");
        tvMonth.setText("0%");

        tvXP.setText("⭐ Total XP : " + xp);

        tvStreak.setText("🔥 Current Streak : " + streak + " Days");

        String level;

        if (xp >= 500) {

            level = "Expert";

        } else if (xp >= 300) {

            level = "Advanced";

        } else if (xp >= 150) {

            level = "Intermediate";

        } else {

            level = "Beginner";
        }

        tvLevel.setText("🏆 Level : " + level);

        if (xp >= 300) {

            tvPlant.setText("🌸 Blooming");

            progressPlant.setProgressCompat(100, true);

        } else if (xp >= 200) {

            tvPlant.setText("🌿 Growing");

            progressPlant.setProgressCompat(75, true);

        } else if (xp >= 100) {

            tvPlant.setText("🌱 Sprout");

            progressPlant.setProgressCompat(50, true);

        } else {

            tvPlant.setText("🌰 Seed");

            progressPlant.setProgressCompat(25, true);
        }

        tvPlantDescription.setText(
                "Complete daily habits to help your plant grow."
        );
    }

    private void setupBottomNavigation() {

        bottomNavigation.setSelectedItemId(R.id.navigation_reports);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.navigation_home) {

                startActivity(new Intent(
                        this,
                        DashboardActivity.class
                ));

                finish();

                return true;

            } else if (id == R.id.navigation_habits) {

                startActivity(new Intent(
                        this,
                        HabitsActivity.class
                ));

                finish();

                return true;

            } else if (id == R.id.navigation_reports) {

                return true;

            } else if (id == R.id.navigation_profile) {

                startActivity(new Intent(
                        this,
                        ProfileActivity.class
                ));

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