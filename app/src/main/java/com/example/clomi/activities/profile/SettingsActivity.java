package com.example.clomi.activities.profile;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import com.example.clomi.activities.profile.PrivacyPolicyActivity;
import com.example.clomi.activities.profile.ResetActivity;
import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private SwitchCompat switchNotifications;

    private MaterialCardView cardPrivacy;
    private MaterialCardView cardAbout;
    private MaterialCardView cardDarkMode;

    private MaterialButton btnReset;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);

        switchNotifications = findViewById(R.id.switchNotifications);

        cardPrivacy = findViewById(R.id.cardPrivacy);
        cardAbout = findViewById(R.id.cardAbout);
        cardDarkMode = findViewById(R.id.cardDarkMode);

        btnReset = findViewById(R.id.btnReset);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {

            Toast.makeText(
                    this,
                    isChecked ? "Notifications Enabled" : "Notifications Disabled",
                    Toast.LENGTH_SHORT
            ).show();

        });

        cardDarkMode.setOnClickListener(v ->

                Toast.makeText(
                        this,
                        "Dark Mode Coming Soon",
                        Toast.LENGTH_SHORT
                ).show());

        cardPrivacy.setOnClickListener(v ->

                startActivity(new Intent(
                        SettingsActivity.this,
                        PrivacyPolicyActivity.class
                )));

        cardAbout.setOnClickListener(v ->

                startActivity(new Intent(
                        SettingsActivity.this,
                        AboutActivity.class
                )));

        btnReset.setOnClickListener(v ->

                startActivity(new Intent(
                        SettingsActivity.this,
                        ResetActivity.class
                )));
    }
}