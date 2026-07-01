package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView logo = findViewById(R.id.tvLogo);
        TextView appName = findViewById(R.id.tvAppName);
        TextView tagline = findViewById(R.id.tvTagline);
        TextView description = findViewById(R.id.tvDescription);
        TextView loading = findViewById(R.id.tvLoading);

        AlphaAnimation fade = new AlphaAnimation(0f, 1f);
        fade.setDuration(1200);
        fade.setFillAfter(true);

        logo.startAnimation(fade);
        appName.startAnimation(fade);
        tagline.startAnimation(fade);
        description.startAnimation(fade);
        loading.startAnimation(fade);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            Intent intent = new Intent(
                    SplashActivity.this,
                    WelcomeActivity.class
            );

            startActivity(intent);
            finish();

        }, SPLASH_DURATION);
    }
}