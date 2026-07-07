package com.example.clomi.activities.profile;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.textfield.TextInputEditText;

public class EditProfileActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private TextInputEditText etWaterGoal;
    private TextInputEditText etSleepGoal;

    private Button btnSave;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        loadUserData();
        setupListeners();
    }

    private void initializeViews() {

        etName = findViewById(R.id.etName);
        etWaterGoal = findViewById(R.id.etWaterGoal);
        etSleepGoal = findViewById(R.id.etSleepGoal);

        btnSave = findViewById(R.id.btnSave);

    }

    private void loadUserData() {

        etName.setText(
                preferences.getString(
                        PreferenceKeys.USER_NAME,
                        ""
                )
        );

        etWaterGoal.setText(
                preferences.getString(
                        PreferenceKeys.WATER_GOAL,
                        ""
                )
        );

        etSleepGoal.setText(
                preferences.getString(
                        PreferenceKeys.SLEEP_HOURS,
                        ""
                )
        );

    }

    private void setupListeners() {

        btnSave.setOnClickListener(v -> saveProfile());

    }

    private void saveProfile() {

        preferences.putString(
                PreferenceKeys.USER_NAME,
                etName.getText().toString().trim()
        );

        preferences.putString(
                PreferenceKeys.WATER_GOAL,
                etWaterGoal.getText().toString().trim()
        );

        preferences.putString(
                PreferenceKeys.SLEEP_HOURS,
                etSleepGoal.getText().toString().trim()
        );

        finish();

    }

}