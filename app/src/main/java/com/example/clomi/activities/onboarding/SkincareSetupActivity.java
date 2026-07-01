package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.google.android.material.button.MaterialButton;

public class SkincareSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgRoutine;
    private RadioGroup rgSkinType;
    private RadioGroup rgReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skincare_setup);

        initializeViews();

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> validateInputs());
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgRoutine = findViewById(R.id.rgRoutine);
        rgSkinType = findViewById(R.id.rgSkinType);
        rgReminder = findViewById(R.id.rgReminder);

    }

    private void validateInputs() {

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

        Intent intent = new Intent(
                SkincareSetupActivity.this,
                WellnessSetupActivity.class
        );

        startActivity(intent);
        finish();
    }
}