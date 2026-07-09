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

public class LogoutActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnLogoutNow;
    private MaterialButton btnStay;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnLogoutNow = findViewById(R.id.btnLogoutNow);
        btnStay = findViewById(R.id.btnStay);

    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnStay.setOnClickListener(v -> finish());

        btnLogoutNow.setOnClickListener(v -> {

            preferences.clear();

            Toast.makeText(
                    this,
                    "Logged out successfully",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(
                    LogoutActivity.this,
                    SplashActivity.class
            );

            startActivity(intent);

            finishAffinity();

        });

    }
}