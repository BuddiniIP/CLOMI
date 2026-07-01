package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class WaterSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgWater;
    private RadioGroup rgExercise;

    private TextInputEditText etCustomGoal;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_setup);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateAndContinue());
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgWater = findViewById(R.id.rgWater);
        rgExercise = findViewById(R.id.rgExercise);

        etCustomGoal = findViewById(R.id.etCustomGoal);
    }

    private void validateAndContinue() {

        if (rgWater.getCheckedRadioButtonId() == -1) {

            Toast.makeText(this,
                    "Please select your daily water intake.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (rgExercise.getCheckedRadioButtonId() == -1) {

            Toast.makeText(this,
                    "Please select your exercise frequency.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String waterGoal;

        if (!etCustomGoal.getText().toString().trim().isEmpty()) {

            try {

                double custom =
                        Double.parseDouble(
                                etCustomGoal.getText().toString().trim());

                if (custom <= 0) {
                    etCustomGoal.setError("Enter a valid value");
                    return;
                }

                waterGoal = custom + " L";

            } catch (Exception e) {

                etCustomGoal.setError("Invalid value");
                return;
            }

        } else {

            RadioButton selectedWater =
                    findViewById(rgWater.getCheckedRadioButtonId());

            waterGoal = selectedWater.getText().toString();
        }

        RadioButton selectedExercise =
                findViewById(rgExercise.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.WATER_GOAL,
                waterGoal
        );

        preferences.putString(
                PreferenceKeys.EXERCISE_LEVEL,
                selectedExercise.getText().toString()
        );

        Intent intent = new Intent(
                WaterSetupActivity.this,
                SleepSetupActivity.class
        );

        startActivity(intent);
        finish();
    }
}