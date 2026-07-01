package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;

public class WellnessSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnFinish;

    private RadioGroup rgMoodFrequency;

    private SwitchCompat switchMotivation;

    private CheckBox cbConfidence;
    private CheckBox cbStress;
    private CheckBox cbProductivity;
    private CheckBox cbSelfCare;
    private CheckBox cbMental;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_setup);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnFinish = findViewById(R.id.btnFinish);

        rgMoodFrequency = findViewById(R.id.rgMoodFrequency);

        switchMotivation = findViewById(R.id.switchMotivation);

        cbConfidence = findViewById(R.id.cbConfidence);
        cbStress = findViewById(R.id.cbStress);
        cbProductivity = findViewById(R.id.cbProductivity);
        cbSelfCare = findViewById(R.id.cbSelfCare);
        cbMental = findViewById(R.id.cbMental);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnFinish.setOnClickListener(v -> validateAndContinue());
    }

    private void validateAndContinue() {

        if (rgMoodFrequency.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please select your mood check frequency.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (!cbConfidence.isChecked()
                && !cbStress.isChecked()
                && !cbProductivity.isChecked()
                && !cbSelfCare.isChecked()
                && !cbMental.isChecked()) {

            Toast.makeText(
                    this,
                    "Please select at least one area to improve.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        saveData();

        navigateNext();
    }

    private void saveData() {

        RadioButton selectedMood =
                findViewById(rgMoodFrequency.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.MOOD_FREQUENCY,
                selectedMood.getText().toString()
        );

        preferences.putBoolean(
                PreferenceKeys.MOTIVATION_ENABLED,
                switchMotivation.isChecked()
        );

        StringBuilder goals = new StringBuilder();

        if (cbConfidence.isChecked()) {
            goals.append("Confidence,");
        }

        if (cbStress.isChecked()) {
            goals.append("Stress,");
        }

        if (cbProductivity.isChecked()) {
            goals.append("Productivity,");
        }

        if (cbSelfCare.isChecked()) {
            goals.append("Self Care,");
        }

        if (cbMental.isChecked()) {
            goals.append("Mental Health,");
        }

        preferences.putString(
                PreferenceKeys.WELLNESS_GOALS,
                goals.toString()
        );
    }

    private void navigateNext() {

        Intent intent = new Intent(
                WellnessSetupActivity.this,
                SetupCompleteActivity.class
        );

        startActivity(intent);
    }
}