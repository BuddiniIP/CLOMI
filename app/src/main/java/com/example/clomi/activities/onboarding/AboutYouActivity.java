package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.dashboard.DashboardActivity;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AboutYouActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private MaterialButton btnGetStarted;

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
        btnGetStarted = findViewById(R.id.btnGetStarted);

    }

    private void setupListeners() {

        btnGetStarted.setOnClickListener(v -> saveUser());

    }

    private void saveUser() {

        String name = etName.getText() != null ? etName.getText().toString().trim() : "";

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

        // Sync the profile with the remote backend
        com.example.clomi.manager.SyncManager.syncProfile(this);

        startActivity(new Intent(
                AboutYouActivity.this,
                DashboardActivity.class
        ));

        finish();
    }

}