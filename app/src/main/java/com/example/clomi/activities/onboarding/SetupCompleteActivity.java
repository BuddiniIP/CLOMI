package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.google.android.material.button.MaterialButton;

public class SetupCompleteActivity extends AppCompatActivity {

    private MaterialButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_complete);

        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SetupCompleteActivity.this,
                    AboutYouActivity.class
            );

            startActivity(intent);
        });

    }
}