package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
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

public class SleepSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgSleepHours;
    private SwitchCompat switchReminder;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_setup);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgSleepHours = findViewById(R.id.rgSleepHours);
        switchReminder = findViewById(R.id.switchReminder);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateAndContinue());
    }

    private void validateAndContinue() {

        if (rgSleepHours.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please select your sleep goal.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        saveData();

        startActivity(new Intent(
                SleepSetupActivity.this,
                SkincareSetupActivity.class
        ));
    }

    private void saveData() {

        RadioButton selectedSleep =
                findViewById(rgSleepHours.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.SLEEP_HOURS,
                selectedSleep.getText().toString()
        );

        preferences.putBoolean(
                PreferenceKeys.SLEEP_REMINDER,
                switchReminder.isChecked()
        );
    }
}