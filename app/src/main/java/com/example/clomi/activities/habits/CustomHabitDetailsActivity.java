package com.example.clomi.activities.habits;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.db.ClomiDatabase;
import com.example.clomi.model.CustomHabit;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;

public class CustomHabitDetailsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvHabitIcon;
    private TextView tvHabitTitle;
    private TextView tvHabitDescription;
    private TextView tvGoal;
    private TextView tvProgress;
    private MaterialButton btnComplete;

    private ClomiPreferenceManager preferences;
    private CustomHabit habit;
    private MaterialButton btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();

        String habitName = getIntent().getStringExtra("HABIT_NAME");
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");

        habit = ClomiDatabase.getInstance(this)
                .customHabitDao()
                .getCustomHabit(username, habitName);

        if (habit == null) {
            Toast.makeText(this, "Habit not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadHabit();

        btnBack.setOnClickListener(v -> finish());
        btnComplete.setOnClickListener(v -> completeHabit());
        btnDelete.setOnClickListener(v -> deleteHabit());
    }

    private void deleteHabit() {
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");
        String habitName = habit.getHabitName();

        // Delete locally first
        ClomiDatabase.getInstance(this)
                .customHabitDao()
                .deleteCustomHabit(username, habitName);

        // Then delete from the backend
        com.example.clomi.network.RetrofitClient.getApiService()
                .deleteCustomHabit(username, habitName)
                .enqueue(new retrofit2.Callback<Void>() {
                    @Override
                    public void onResponse(retrofit2.Call<Void> call, retrofit2.Response<Void> response) {
                        // No action needed - local delete already happened
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                        // Silently ignore - habit is still deleted locally,
                        // will just remain in DB until next successful sync attempt
                    }
                });

        Toast.makeText(this, habit.getHabitName() + " deleted", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        tvHabitIcon = findViewById(R.id.tvHabitIcon);
        tvHabitTitle = findViewById(R.id.tvHabitTitle);
        tvHabitDescription = findViewById(R.id.tvHabitDescription);
        tvGoal = findViewById(R.id.tvGoal);
        tvProgress = findViewById(R.id.tvProgress);
        btnComplete = findViewById(R.id.btnComplete);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void loadHabit() {
        tvHabitIcon.setText("⭐");
        tvHabitTitle.setText(habit.getHabitName());

        String notes = habit.getNotes();
        String reminderStatus = habit.isReminderEnabled() ? "🔔 Reminder: On" : "🔕 Reminder: Off";

        String description = (notes == null || notes.isEmpty())
                ? "Category: " + habit.getCategory()
                : notes;

        tvHabitDescription.setText(description + "\n" + reminderStatus);

        tvGoal.setText("Target : " + habit.getTarget());
        tvProgress.setText("Today's Progress : Not Completed");
    }

    private void completeHabit() {
        int xp = preferences.getInt(PreferenceKeys.XP);
        xp += 10;
        preferences.putInt(PreferenceKeys.XP, xp);

        Toast.makeText(this, habit.getHabitName() + " completed! +10 XP 🎉", Toast.LENGTH_SHORT).show();

        btnComplete.setEnabled(false);
        btnComplete.setText("Completed ✓");
    }
}