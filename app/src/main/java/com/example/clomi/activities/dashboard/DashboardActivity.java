package com.example.clomi.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.clomi.activities.ai.AiAssistantActivity;
import com.example.clomi.activities.period.PeriodTrackerActivity;
import com.example.clomi.activities.habits.CreateHabitActivity;
import com.example.clomi.activities.habits.HabitDetailsActivity;
import com.example.clomi.activities.habits.CustomHabitDetailsActivity;

import com.example.clomi.R;
import com.example.clomi.activities.habits.HabitsActivity;
import com.example.clomi.activities.profile.ProfileActivity;
import com.example.clomi.activities.reports.ProgressActivity;
import com.example.clomi.adapters.CustomHabitAdapter;
import com.example.clomi.db.ClomiDatabase;
import com.example.clomi.model.CustomHabit;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import android.view.View;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.List;

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
    private MaterialCardView cardWater;
    private MaterialCardView cardSleep;
    private MaterialCardView cardMood;
    private MaterialCardView cardSkincare;
    private MaterialButton btnAddHabit;
    private RecyclerView rvTodayReminders;
    private TextView tvNoCustomReminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        loadDashboard();
        setupClickListeners();
        setupBottomNavigation();
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
        cardWater = findViewById(R.id.cardWater);
        cardSleep = findViewById(R.id.cardSleep);
        cardMood = findViewById(R.id.cardMood);
        cardSkincare = findViewById(R.id.cardSkincare);
        btnAddHabit = findViewById(R.id.btnAddHabit);
        rvTodayReminders = findViewById(R.id.rvTodayReminders);
        tvNoCustomReminders = findViewById(R.id.tvNoCustomReminders);
    }

    private void loadCustomHabits() {
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");
        List<CustomHabit> habits = ClomiDatabase.getInstance(this)
                .customHabitDao()
                .getCustomHabits(username);

        rvCustomHabits.setLayoutManager(new LinearLayoutManager(this));
        rvCustomHabits.setAdapter(new CustomHabitAdapter(habits, habit -> {
            Intent intent = new Intent(DashboardActivity.this, CustomHabitDetailsActivity.class);
            intent.putExtra("HABIT_NAME", habit.getHabitName());
            startActivity(intent);
        }));
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

        progressToday.setProgressCompat(progress, true);
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

        loadCustomHabits();
        loadTodayReminders();
    }

    private void openHabit(String habitType) {

        Intent intent = new Intent(
                DashboardActivity.this,
                HabitDetailsActivity.class
        );

        intent.putExtra("HABIT_TYPE", habitType);

        startActivity(intent);
    }
    private void loadTodayReminders() {
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");
        List<CustomHabit> reminderHabits = ClomiDatabase.getInstance(this)
                .customHabitDao()
                .getReminderHabits(username);

        if (reminderHabits == null || reminderHabits.isEmpty()) {
            tvNoCustomReminders.setVisibility(View.VISIBLE);
            rvTodayReminders.setVisibility(View.GONE);
            return;
        }

        tvNoCustomReminders.setVisibility(View.GONE);
        rvTodayReminders.setVisibility(View.VISIBLE);

        rvTodayReminders.setLayoutManager(new LinearLayoutManager(this));
        rvTodayReminders.setAdapter(new CustomHabitAdapter(reminderHabits, habit -> {
            Intent intent = new Intent(DashboardActivity.this, CustomHabitDetailsActivity.class);
            intent.putExtra("HABIT_NAME", habit.getHabitName());
            startActivity(intent);
        }));
    }

    private void setupClickListeners() {

        cardWater.setOnClickListener(v -> openHabit("Water"));

        cardSleep.setOnClickListener(v -> openHabit("Sleep"));

        cardMood.setOnClickListener(v -> openHabit("Mood"));

        cardSkincare.setOnClickListener(v -> openHabit("Skincare"));

        btnAddHabit.setOnClickListener(v ->
                startActivity(new Intent(
                        DashboardActivity.this,
                        CreateHabitActivity.class)));

        btnOpenPeriodTracker.setOnClickListener(v ->
                startActivity(new Intent(
                        DashboardActivity.this,
                        PeriodTrackerActivity.class)));

        btnUnlockAI.setOnClickListener(v ->
                startActivity(new Intent(
                        DashboardActivity.this,
                        AiAssistantActivity.class)));
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
        com.example.clomi.manager.SyncManager.performFullSync(this);
    }

}