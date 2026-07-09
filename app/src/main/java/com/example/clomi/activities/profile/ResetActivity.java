package com.example.clomi.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.onboarding.SplashActivity;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.google.android.material.button.MaterialButton;

public class ResetActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnResetNow;
    private MaterialButton btnCancel;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnResetNow = findViewById(R.id.btnResetNow);
        btnCancel = findViewById(R.id.btnCancel);

    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnCancel.setOnClickListener(v -> finish());

        btnResetNow.setOnClickListener(v -> {

            preferences.clear();

            Toast.makeText(
                    this,
                    "All data has been reset successfully.",
                    Toast.LENGTH_LONG
            ).show();

            Intent intent = new Intent(
                    ResetActivity.this,
                    SplashActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);

            startActivity(intent);

            finishAffinity();

        });

    }
}