
package com.example.clomi.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.activities.dashboard.DashboardActivity;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2800;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();

        startSplashTimer();
    }

    private void initializeViews() {

        TextView logo = findViewById(R.id.tvLogo);
        TextView appName = findViewById(R.id.tvAppName);
        TextView tagline = findViewById(R.id.tvTagline);
        TextView loading = findViewById(R.id.tvLoading);

        AlphaAnimation fade = new AlphaAnimation(0f, 1f);
        fade.setDuration(1200);
        fade.setFillAfter(true);

        logo.startAnimation(fade);
        appName.startAnimation(fade);
        tagline.startAnimation(fade);
        loading.startAnimation(fade);
    }

    private void startSplashTimer() {

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            boolean onboardingCompleted =
                    preferences.getBoolean(
                            PreferenceKeys.ONBOARDING_COMPLETED
                    );

            Intent intent;

            if (onboardingCompleted) {

                intent = new Intent(
                        SplashActivity.this,
                        DashboardActivity.class
                );

            } else {

                intent = new Intent(
                        SplashActivity.this,
                        WelcomeActivity.class
                );
            }

            startActivity(intent);
            finish();

        }, SPLASH_DURATION);
    }
}