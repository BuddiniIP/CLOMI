package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    private MaterialButton btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {

            Intent intent = new Intent(
                    WelcomeActivity.this,
                    LifestyleActivity.class
            );

            startActivity(intent);
            finish();

        });

    }
}