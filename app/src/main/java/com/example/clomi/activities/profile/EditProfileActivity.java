package com.example.clomi.activities.profile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class EditProfileActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private TextInputEditText etName;
    private TextInputEditText etWaterGoal;
    private TextInputEditText etSleepGoal;

    private RadioGroup rgGender;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    private Spinner spGoal;
    private Spinner spSkinType;

    private MaterialButton btnSave;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupSpinners();
        loadUserData();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);

        etName = findViewById(R.id.etName);
        etWaterGoal = findViewById(R.id.etWaterGoal);
        etSleepGoal = findViewById(R.id.etSleepGoal);

        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);

        spGoal = findViewById(R.id.spGoal);
        spSkinType = findViewById(R.id.spSkinType);

        btnSave = findViewById(R.id.btnSave);
    }

    private void setupSpinners() {

        String[] goals = {
                "Lose Weight",
                "Gain Weight",
                "Stay Healthy",
                "Build Muscle",
                "Improve Sleep"
        };

        ArrayAdapter<String> goalAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                goals
        );

        spGoal.setAdapter(goalAdapter);

        String[] skinTypes = {
                "Oily",
                "Dry",
                "Combination",
                "Sensitive"
        };

        ArrayAdapter<String> skinAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                skinTypes
        );

        spSkinType.setAdapter(skinAdapter);
    }

    private void loadUserData() {

        etName.setText(preferences.getString(PreferenceKeys.USER_NAME, ""));
        etWaterGoal.setText(preferences.getString(PreferenceKeys.WATER_GOAL, ""));
        etSleepGoal.setText(preferences.getString(PreferenceKeys.SLEEP_HOURS, ""));

        String gender = preferences.getString(PreferenceKeys.GENDER, "");

        if (gender.equals("Male")) {
            rbMale.setChecked(true);
        } else if (gender.equals("Female")) {
            rbFemale.setChecked(true);
        }
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> saveProfile());
    }

    private void saveProfile() {

        String name = etName.getText().toString().trim();
        String waterGoal = etWaterGoal.getText().toString().trim();
        String sleepGoal = etSleepGoal.getText().toString().trim();

        String gender = "";

        if (rbMale.isChecked()) {
            gender = "Male";
        } else if (rbFemale.isChecked()) {
            gender = "Female";
        }

        if (gender.isEmpty()) {
            Toast.makeText(this,
                    "Please select your gender",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String goal = spGoal.getSelectedItem().toString();
        String skinType = spSkinType.getSelectedItem().toString();

        preferences.putString(PreferenceKeys.USER_NAME, name);
        preferences.putString(PreferenceKeys.WATER_GOAL, waterGoal);
        preferences.putString(PreferenceKeys.SLEEP_HOURS, sleepGoal);
        preferences.putString(PreferenceKeys.GENDER, gender);
        preferences.putString(PreferenceKeys.MAIN_GOALS, goal);
        preferences.putString(PreferenceKeys.SKIN_TYPE, skinType);

        Toast.makeText(
                this,
                "Profile updated successfully!",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }
}