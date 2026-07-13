package com.example.clomi.activities.habits;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.dashboard.DashboardActivity;
import com.example.clomi.activities.profile.ProfileActivity;
import com.example.clomi.activities.reports.ProgressActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class HabitsActivity extends AppCompatActivity {

    private MaterialCardView cardWater;
    private MaterialCardView cardSleep;
    private MaterialCardView cardMood;
    private MaterialCardView cardSkincare;
    private MaterialCardView cardCustomHabits;

    private MaterialButton btnAddHabit;

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);

        initializeViews();
        setupClickListeners();
        setupBottomNavigation();
    }

    private void initializeViews() {

        cardWater = findViewById(R.id.cardWater);
        cardSleep = findViewById(R.id.cardSleep);
        cardMood = findViewById(R.id.cardMood);
        cardSkincare = findViewById(R.id.cardSkincare);
        cardCustomHabits = findViewById(R.id.cardCustomHabits);

        btnAddHabit = findViewById(R.id.btnAddHabit);

        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupClickListeners() {

        cardWater.setOnClickListener(v -> openHabit("Water"));

        cardSleep.setOnClickListener(v -> openHabit("Sleep"));

        cardMood.setOnClickListener(v -> openHabit("Mood"));

        cardSkincare.setOnClickListener(v -> openHabit("Skincare"));

        cardCustomHabits.setOnClickListener(v ->
                startActivity(new Intent(
                        HabitsActivity.this,
                        CustomHabitListActivity.class)));

        btnAddHabit.setOnClickListener(v ->
                startActivity(new Intent(
                        HabitsActivity.this,
                        CreateHabitActivity.class)));
    }

    private void openHabit(String habitType) {

        Intent intent = new Intent(
                HabitsActivity.this,
                HabitDetailsActivity.class
        );

        intent.putExtra("HABIT_TYPE", habitType);

        startActivity(intent);
    }

    private void setupBottomNavigation() {

        bottomNavigation.setSelectedItemId(R.id.navigation_habits);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.navigation_home) {

                startActivity(new Intent(this, DashboardActivity.class));
                finish();
                return true;

            } else if (id == R.id.navigation_habits) {

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