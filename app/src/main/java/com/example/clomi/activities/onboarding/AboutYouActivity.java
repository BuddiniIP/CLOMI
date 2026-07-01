package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.dashboard.DashboardActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AboutYouActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private MaterialButton btnStartJourney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_you);

        etName = findViewById(R.id.etName);
        btnStartJourney = findViewById(R.id.btnStartJourney);

        btnStartJourney.setOnClickListener(v -> saveProfile());
    }

    private void saveProfile() {

        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Please enter your name");
            return;
        }

        SharedPreferences preferences = getSharedPreferences("CLOMI_PREFS", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("USER_NAME", name);
        editor.putBoolean("ONBOARDING_COMPLETED", true);

        editor.apply();

        Toast.makeText(
                this,
                "Welcome to CLOMI, " + name + " 🌱",
                Toast.LENGTH_SHORT
        ).show();

        Intent intent = new Intent(
                AboutYouActivity.this,
                DashboardActivity.class
        );

        startActivity(intent);
        finishAffinity();
    }
}