package com.example.clomi.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.dashboard.DashboardActivity;
import com.example.clomi.activities.habits.HabitsActivity;
import com.example.clomi.activities.reports.ProgressActivity;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvGender;
    private TextView tvAge;
    private TextView tvGoal;
    private TextView tvWater;
    private TextView tvSleep;
    private TextView tvSkin;

    private MaterialButton btnEditProfile;
    private MaterialButton btnSettings;
    private MaterialButton btnAbout;
    private MaterialButton btnLogout;

    private BottomNavigationView bottomNavigation;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        loadProfile();
        setupButtons();
        setupBottomNavigation();
    }

    private void initializeViews() {

        tvName = findViewById(R.id.tvName);
        tvGender = findViewById(R.id.tvGender);
        tvAge = findViewById(R.id.tvAge);
        tvGoal = findViewById(R.id.tvGoal);
        tvWater = findViewById(R.id.tvWater);
        tvSleep = findViewById(R.id.tvSleep);
        tvSkin = findViewById(R.id.tvSkin);

        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnSettings = findViewById(R.id.btnSettings);
        btnAbout = findViewById(R.id.btnAbout);
        btnLogout = findViewById(R.id.btnLogout);

        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void loadProfile() {

        tvName.setText(preferences.getString(PreferenceKeys.USER_NAME, "User"));

        tvGender.setText("Gender : " +
                preferences.getString(PreferenceKeys.GENDER, " "));

        tvAge.setText("Age : " +
                preferences.getString(PreferenceKeys.AGE, " "));

        tvGoal.setText("Goal : " +
                preferences.getString(PreferenceKeys.MAIN_GOALS, " "));

        tvWater.setText("Water Goal : " +
                preferences.getString(PreferenceKeys.WATER_GOAL, " "));

        tvSleep.setText("Sleep Goal : " +
                preferences.getString(PreferenceKeys.SLEEP_HOURS, " "));

        tvSkin.setText("Skin Type : " +
                preferences.getString(PreferenceKeys.SKIN_TYPE, " "));
    }

    private void setupButtons() {

        btnEditProfile.setOnClickListener(v ->
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show());

        btnSettings.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));

        btnAbout.setOnClickListener(v ->
                Toast.makeText(this, "CLOMI v1.0\nYour Wellness Companion", Toast.LENGTH_LONG).show());

        btnLogout.setOnClickListener(v -> {

            preferences.clear();

            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finishAffinity();
        });
    }

    private void setupBottomNavigation() {

        bottomNavigation.setSelectedItemId(R.id.navigation_profile);

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

                startActivity(new Intent(this, ProgressActivity.class));
                finish();
                return true;

            } else if (id == R.id.navigation_profile) {

                return true;
            }

            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfile();
    }
}