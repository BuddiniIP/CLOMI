package com.example.clomi.activities.habits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clomi.R;
import com.example.clomi.adapters.CustomHabitAdapter;
import com.example.clomi.db.ClomiDatabase;
import com.example.clomi.model.CustomHabit;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;

import java.util.List;

public class CustomHabitListActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvEmptyMessage;
    private RecyclerView rvCustomHabitsList;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_habit_list);

        preferences = new ClomiPreferenceManager(this);

        btnBack = findViewById(R.id.btnBack);
        tvEmptyMessage = findViewById(R.id.tvEmptyMessage);
        rvCustomHabitsList = findViewById(R.id.rvCustomHabitsList);

        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHabits();
    }

    private void loadHabits() {
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");
        List<CustomHabit> habits = ClomiDatabase.getInstance(this)
                .customHabitDao()
                .getCustomHabits(username);

        if (habits == null || habits.isEmpty()) {
            tvEmptyMessage.setVisibility(View.VISIBLE);
            rvCustomHabitsList.setVisibility(View.GONE);
            return;
        }

        tvEmptyMessage.setVisibility(View.GONE);
        rvCustomHabitsList.setVisibility(View.VISIBLE);

        rvCustomHabitsList.setLayoutManager(new LinearLayoutManager(this));
        rvCustomHabitsList.setAdapter(new CustomHabitAdapter(habits, habit -> {
            Intent intent = new Intent(CustomHabitListActivity.this, CustomHabitDetailsActivity.class);
            intent.putExtra("HABIT_NAME", habit.getHabitName());
            startActivity(intent);
        }));
    }
}