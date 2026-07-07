package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;

public class LifestyleActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgGender;
    private RadioGroup rgActivity;

    private CheckBox cbHabit;
    private CheckBox cbCare;
    private CheckBox cbWellness;
    private CheckBox cbProductivity;
    private CheckBox cbStress;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifestyle);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgGender = findViewById(R.id.rgGender);
        rgActivity = findViewById(R.id.rgActivity);

        cbHabit = findViewById(R.id.cbHabit);
        cbCare = findViewById(R.id.cbCare);
        cbWellness = findViewById(R.id.cbWellness);
        cbProductivity = findViewById(R.id.cbProductivity);
        cbStress = findViewById(R.id.cbStress);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateInputs());
    }

    private void validateInputs() {

        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select your gender.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rgActivity.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select your activity level.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isAnyGoalSelected()) {
            Toast.makeText(this, "Please select at least one focus area.", Toast.LENGTH_SHORT).show();
            return;
        }

        saveSelections();

        startActivity(new Intent(
                LifestyleActivity.this,
                WaterSetupActivity.class
        ));
    }

    private boolean isAnyGoalSelected() {

        return cbHabit.isChecked()
                || cbCare.isChecked()
                || cbWellness.isChecked()
                || cbProductivity.isChecked()
                || cbStress.isChecked();
    }

    private void saveSelections() {

        RadioButton selectedGender =
                findViewById(rgGender.getCheckedRadioButtonId());

        RadioButton selectedActivity =
                findViewById(rgActivity.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.GENDER,
                selectedGender.getText().toString()
        );

        preferences.putString(
                PreferenceKeys.ACTIVITY_LEVEL,
                selectedActivity.getText().toString()
        );

        StringBuilder goals = new StringBuilder();

        if (cbHabit.isChecked())
            goals.append("Build Better Habits,");

        if (cbCare.isChecked())
            goals.append("Improve Self Care,");

        if (cbWellness.isChecked())
            goals.append("Improve Wellness,");

        if (cbProductivity.isChecked())
            goals.append("Increase Productivity,");

        if (cbStress.isChecked())
            goals.append("Reduce Stress,");

        if (goals.length() > 0) {
            goals.deleteCharAt(goals.length() - 1);
        }

        preferences.putString(
                PreferenceKeys.MAIN_GOALS,
                goals.toString()
        );
    }
}