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

public class WellnessSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgMoodCheck;
    private SwitchCompat switchMotivation;

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
        btnContinue = findViewById(R.id.btnContinue);

        rgMoodCheck = findViewById(R.id.rgMoodCheck);
        switchMotivation = findViewById(R.id.switchMotivation);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateAndContinue());
    }

    private void validateAndContinue() {

        if (rgMoodCheck.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please choose an option.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        saveData();

        String gender = preferences.getString(PreferenceKeys.GENDER);

        Intent intent;

        if ("Female".equalsIgnoreCase(gender)) {

            intent = new Intent(
                    WellnessSetupActivity.this,
                    PeriodSetupActivity.class
            );

        } else {

            preferences.putBoolean(
                    PreferenceKeys.PERIOD_ENABLED,
                    false
            );

            intent = new Intent(
                    WellnessSetupActivity.this,
                    AboutYouActivity.class
            );
        }

        startActivity(intent);
        finish();
    }

    private void saveData() {

        RadioButton selectedMood =
                findViewById(rgMoodCheck.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.MOOD_CHECK,
                selectedMood.getText().toString()
        );

        preferences.putBoolean(
                PreferenceKeys.MOTIVATION_ENABLED,
                switchMotivation.isChecked()
        );
    }
}