package com.example.clomi.activities.plant;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class PlantActivity extends AppCompatActivity {

    private TextView tvPlantStage;
    private TextView tvPlantLevel;
    private TextView tvHealth;
    private TextView tvXP;
    private TextView tvWaterCount;
    private TextView tvSleepCount;

    private LinearProgressIndicator progressPlant;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        loadPlantData();
    }

    private void initializeViews() {

        tvPlantStage = findViewById(R.id.tvPlantStage);
        tvPlantLevel = findViewById(R.id.tvPlantLevel);
        tvHealth = findViewById(R.id.tvHealth);
        tvXP = findViewById(R.id.tvXP);
        tvWaterCount = findViewById(R.id.tvWaterCount);
        tvSleepCount = findViewById(R.id.tvSleepCount);

        progressPlant = findViewById(R.id.progressPlant);
    }

    private void loadPlantData() {

        int xp = preferences.getInt(PreferenceKeys.XP, 0);
        int level = preferences.getInt(PreferenceKeys.LEVEL, 1);

        tvPlantLevel.setText("Level " + level);
        tvXP.setText("⭐ XP : " + xp);

        progressPlant.setProgress(Math.min(xp, 100));

        if (level <= 1) {
            tvPlantStage.setText("Seed 🌱");
        } else if (level == 2) {
            tvPlantStage.setText("Sprout 🌿");
        } else if (level == 3) {
            tvPlantStage.setText("Young Plant 🌳");
        } else {
            tvPlantStage.setText("Blooming 🌸");
        }
    }
}