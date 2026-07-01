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

public class LifestyleActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgGender;
    private RadioGroup rgActivity;
    private RadioGroup rgGoal;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifestyle);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateInputs());
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgGender = findViewById(R.id.rgGender);
        rgActivity = findViewById(R.id.rgActivity);
        rgGoal = findViewById(R.id.rgGoal);
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

        if (rgGoal.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select your main goal.", Toast.LENGTH_SHORT).show();
            return;
        }

        // ---------- Save Gender ----------

        RadioButton selectedGender = findViewById(rgGender.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.GENDER,
                selectedGender.getText().toString()
        );

        // ---------- Save Activity Level ----------

        RadioButton selectedActivity = findViewById(rgActivity.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.ACTIVITY_LEVEL,
                selectedActivity.getText().toString()
        );

        // ---------- Save Goal ----------

        RadioButton selectedGoal = findViewById(rgGoal.getCheckedRadioButtonId());

        preferences.putString(
                PreferenceKeys.MAIN_GOAL,
                selectedGoal.getText().toString()
        );

        Intent intent = new Intent(
                LifestyleActivity.this,
                WaterSetupActivity.class
        );

        startActivity(intent);
    }
}