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

public class SkincareSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgRoutine;
    private RadioGroup rgSkinType;
    private RadioGroup rgReminder;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skincare_setup);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgRoutine = findViewById(R.id.rgRoutine);
        rgSkinType = findViewById(R.id.rgSkinType);
        rgReminder = findViewById(R.id.rgReminder);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateAndContinue());
    }

    private void validateAndContinue() {

        if (rgRoutine.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please choose a skincare routine option.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (rgSkinType.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please select your skin type.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (rgReminder.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please choose a reminder preference.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        saveData();

        navigateNext();
    }

    private void saveData() {

        RadioButton selectedRoutine =
                findViewById(rgRoutine.getCheckedRadioButtonId());

        RadioButton selectedSkinType =
                findViewById(rgSkinType.getCheckedRadioButtonId());

        RadioButton selectedReminder =
                findViewById(rgReminder.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.SKINCARE_ROUTINE,
                selectedRoutine.getText().toString()
        );

        preferences.putString(
                PreferenceKeys.SKIN_TYPE,
                selectedSkinType.getText().toString()
        );

        preferences.putString(
                PreferenceKeys.SKINCARE_REMINDER,
                selectedReminder.getText().toString()
        );
    }

    private void navigateNext() {

        Intent intent = new Intent(
                SkincareSetupActivity.this,
                WellnessSetupActivity.class
        );

        startActivity(intent);
    }
}