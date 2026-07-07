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

public class WaterSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgWater;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_setup);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgWater = findViewById(R.id.rgWater);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateAndContinue());

    }

    private void validateAndContinue() {

        if (rgWater.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please select your daily water goal.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        RadioButton selectedWater =
                findViewById(rgWater.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.WATER_GOAL,
                selectedWater.getText().toString()
        );

        startActivity(new Intent(
                WaterSetupActivity.this,
                SleepSetupActivity.class
        ));
    }

}