package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.dashboard.DashboardActivity;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AboutYouActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private MaterialButton btnStartJourney;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_you);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        etName = findViewById(R.id.etName);
        btnStartJourney = findViewById(R.id.btnStartJourney);

    }

    private void setupListeners() {

        btnStartJourney.setOnClickListener(v -> saveProfile());

    }

    private void saveProfile() {

        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {

            etName.setError("Please enter your name");
            return;

        }

        preferences.putString(
                PreferenceKeys.USER_NAME,
                name
        );

        preferences.putBoolean(
                PreferenceKeys.ONBOARDING_COMPLETED,
                true
        );

        Toast.makeText(
                this,
                "Welcome to CLOMI, " + name + " 🌱",
                Toast.LENGTH_SHORT
        ).show();

        navigateNext();

    }

    private void navigateNext() {

        Intent intent = new Intent(
                AboutYouActivity.this,
                DashboardActivity.class
        );

        startActivity(intent);

        finishAffinity();

    }
}