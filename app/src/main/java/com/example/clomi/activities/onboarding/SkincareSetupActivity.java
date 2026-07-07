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

public class SkincareSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgSkinType;
    private SwitchCompat switchReminder;

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

        rgSkinType = findViewById(R.id.rgSkinType);
        switchReminder = findViewById(R.id.switchReminder);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateAndContinue());
    }

    private void validateAndContinue() {

        if (rgSkinType.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please select your skin type.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        saveData();

        startActivity(new Intent(
                SkincareSetupActivity.this,
                WellnessSetupActivity.class
        ));
    }

    private void saveData() {

        RadioButton selectedSkinType =
                findViewById(rgSkinType.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.SKIN_TYPE,
                selectedSkinType.getText().toString()
        );

        preferences.putBoolean(
                PreferenceKeys.SKINCARE_REMINDER,
                switchReminder.isChecked()
        );
    }
}