package com.example.clomi.activities.habits;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;

public class HabitDetailsActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private TextView tvHabitIcon;
    private TextView tvHabitTitle;
    private TextView tvHabitDescription;
    private TextView tvGoal;
    private TextView tvProgress;

    private MaterialButton btnComplete;

    private ClomiPreferenceManager preferences;

    private String habitType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();

        habitType = getIntent().getStringExtra("HABIT_TYPE");

        if (habitType == null) {
            habitType = "Habit";
        }

        loadHabit();

        btnBack.setOnClickListener(v -> finish());

        btnComplete.setOnClickListener(v -> completeHabit());
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);

        tvHabitIcon = findViewById(R.id.tvHabitIcon);
        tvHabitTitle = findViewById(R.id.tvHabitTitle);
        tvHabitDescription = findViewById(R.id.tvHabitDescription);

        tvGoal = findViewById(R.id.tvGoal);
        tvProgress = findViewById(R.id.tvProgress);

        btnComplete = findViewById(R.id.btnComplete);
    }

    private void loadHabit() {

        switch (habitType) {

            case "Water":

                tvHabitIcon.setText("💧");
                tvHabitTitle.setText("Water");

                tvHabitDescription.setText(
                        "Stay hydrated by reaching your daily water goal."
                );

                tvGoal.setText(
                        "Goal : " +
                                preferences.getString(
                                        PreferenceKeys.WATER_GOAL,
                                        "2.5 L"
                                )
                );

                tvProgress.setText("Today's Progress : 0%");
                break;

            case "Sleep":

                tvHabitIcon.setText("😴");
                tvHabitTitle.setText("Sleep");

                tvHabitDescription.setText(
                        "Maintain a healthy sleep routine."
                );

                tvGoal.setText(
                        "Goal : " +
                                preferences.getString(
                                        PreferenceKeys.SLEEP_HOURS,
                                        "8 Hours"
                                )
                );

                tvProgress.setText("Today's Progress : 0%");
                break;

            case "Mood":

                tvHabitIcon.setText("😊");
                tvHabitTitle.setText("Mood");

                tvHabitDescription.setText(
                        "Record how you feel today."
                );

                tvGoal.setText("Goal : Daily Mood Check");

                tvProgress.setText("Today's Progress : Not Checked");
                break;

            case "Skincare":

                tvHabitIcon.setText("🧴");
                tvHabitTitle.setText("Skincare");

                tvHabitDescription.setText(
                        "Complete your skincare routine."
                );

                tvGoal.setText(
                        "Routine : " +
                                preferences.getString(
                                        PreferenceKeys.SKINCARE_ROUTINE,
                                        "Basic Routine"
                                )
                );

                tvProgress.setText("Today's Progress : Not Completed");
                break;
        }
    }

    private void completeHabit() {

        int xp = preferences.getInt(PreferenceKeys.XP);

        xp += 10;

        preferences.putInt(
                PreferenceKeys.XP,
                xp
        );

        Toast.makeText(
                this,
                habitType + " completed! +10 XP 🎉",
                Toast.LENGTH_SHORT
        ).show();

        btnComplete.setEnabled(false);

        btnComplete.setText("Completed ✓");
    }
}