package com.example.clomi.manager;

import android.widget.TextView;

import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.Calendar;

public class DashboardManager {

    private final ClomiPreferenceManager preferences;

    public DashboardManager(ClomiPreferenceManager preferences) {
        this.preferences = preferences;
    }

    public void loadDashboard(TextView tvGreeting,
                              TextView tvUserName,
                              TextView tvQuote,
                              TextView tvProgressPercent,
                              LinearProgressIndicator progressToday,
                              TextView tvWaterProgress,
                              TextView tvSleepProgress,
                              TextView tvXP,
                              TextView tvPlantStatus,
                              TextView tvMission) {

        // User Name
        String name = preferences.getString(
                PreferenceKeys.USER_NAME,
                "User"
        );

        tvUserName.setText(name + " 🌸");

        // Greeting
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour >= 5 && hour < 12) {
            tvGreeting.setText("☀ Good Morning");
        } else if (hour >= 12 && hour < 17) {
            tvGreeting.setText("🌤 Good Afternoon");
        } else {
            tvGreeting.setText("🌙 Good Evening");
        }

        // Quote
        tvQuote.setText("Small habits create big changes.");

        // Progress
        int progress = 0;
        tvProgressPercent.setText(progress + "%");
        progressToday.setProgress(progress);

        // Water Goal
        String waterGoal = preferences.getString(
                PreferenceKeys.WATER_GOAL,
                "2.5 L"
        );
        tvWaterProgress.setText(waterGoal);

        // Sleep Goal
        String sleepGoal = preferences.getString(
                PreferenceKeys.SLEEP_HOURS,
                "8 Hours"
        );
        tvSleepProgress.setText(sleepGoal);

        // XP
        tvXP.setText("0 XP");

        // Plant
        tvPlantStatus.setText("Healthy 🌱");

        // Mission
        tvMission.setText("Complete today's habits.");
    }
}