package com.example.clomi.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.habits.HabitsActivity;
import com.example.clomi.activities.profile.ProfileActivity;
import com.example.clomi.activities.reports.ProgressActivity;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import android.view.View;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvGreeting;
    private TextView tvUserName;
    private TextView tvQuote;
    private TextView tvProgressPercent;
    private TextView tvProgressMessage;
    private TextView tvXP;
    private TextView tvStreak;
    private RecyclerView rvCustomHabits;
    private MaterialCardView cardWellness;
    private TextView tvLastPeriod;
    private TextView tvCycleLength;
    private MaterialButton btnOpenPeriodTracker;
    private MaterialButton btnUnlockAI;
    private TextView tvReminderWater;
    private TextView tvReminderSleep;
    private TextView tvReminderSkin;
    private TextView tvReminderMood;
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
        btnUnlockAI.setOnClickListener(v -> {
        });
    }

    private void initializeViews() {

        tvGreeting = findViewById(R.id.tvGreeting);
        tvUserName = findViewById(R.id.tvUserName);
        tvQuote = findViewById(R.id.tvQuote);
        tvProgressMessage = findViewById(R.id.tvProgressMessage);
        tvStreak = findViewById(R.id.tvStreak);
        rvCustomHabits = findViewById(R.id.rvCustomHabits);
        cardWellness = findViewById(R.id.cardWellness);
        btnUnlockAI = findViewById(R.id.btnUnlockAI);
        tvLastPeriod = findViewById(R.id.tvLastPeriod);
        tvCycleLength = findViewById(R.id.tvCycleLength);
        tvReminderWater = findViewById(R.id.tvReminderWater);
        tvReminderSleep = findViewById(R.id.tvReminderSleep);
        tvReminderSkin = findViewById(R.id.tvReminderSkin);
        tvReminderMood = findViewById(R.id.tvReminderMood);
        btnOpenPeriodTracker = findViewById(R.id.btnOpenPeriodTracker);

        tvProgressPercent = findViewById(R.id.tvProgressPercent);
        progressToday = findViewById(R.id.progressToday);

        tvXP = findViewById(R.id.tvXP);

        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void loadDashboard() {

        String name = preferences.getString(
                PreferenceKeys.USER_NAME,
                "User"
        );

        boolean enabled = preferences.getBoolean(
                PreferenceKeys.PERIOD_ENABLED
        );

        if (enabled) {

            cardWellness.setVisibility(View.VISIBLE);

            tvLastPeriod.setText(
                    preferences.getString(
                            PreferenceKeys.LAST_PERIOD_DATE
                    )
            );

            tvCycleLength.setText(
                    preferences.getString(
                            PreferenceKeys.CYCLE_LENGTH
                    ) + " Days"
            );

        } else {

            cardWellness.setVisibility(View.GONE);

        }

        tvUserName.setText(name);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour >= 5 && hour < 12) {

            tvGreeting.setText("☀ Good Morning");

        } else if (hour >= 12 && hour < 17) {

            tvGreeting.setText("🌤 Good Afternoon");

        } else {

            tvGreeting.setText("🌙 Good Evening");

        }

        tvQuote.setText("Let's complete your habits today 🌸");

        int progress = 0;

        progressToday.setProgress(progress);
        tvProgressPercent.setText(progress + "%");

        if (progress == 0) {

            tvProgressMessage.setText(
                    "Complete today's habits to earn XP."
            );

        } else if (progress < 50) {

            tvProgressMessage.setText(
                    "You're off to a good start!"
            );

        } else if (progress < 100) {

            tvProgressMessage.setText(
                    "Keep going, you're doing great!"
            );

        } else {

            tvProgressMessage.setText(
                    "Amazing! All habits completed today 🎉"
            );

        }

        int xp = preferences.getInt(
                PreferenceKeys.XP,
                0
        );

        int streak = preferences.getInt(
                PreferenceKeys.STREAK,
                0
        );

        tvXP.setText(xp + " XP");

        tvStreak.setText(streak + " Days");
    }

    private void setupBottomNavigation() {

        bottomNavigation.setSelectedItemId(R.id.navigation_home);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.navigation_home) {

                return true;

            } else if (id == R.id.navigation_habits) {

                startActivity(new Intent(this, HabitsActivity.class));
                finish();
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

    @Override
    protected void onResume() {
        super.onResume();
        loadDashboard();
    }
}