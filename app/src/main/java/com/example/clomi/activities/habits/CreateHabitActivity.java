package com.example.clomi.activities.habits;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.clomi.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.example.clomi.db.ClomiDatabase;
import com.example.clomi.model.CustomHabit;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;

public class CreateHabitActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private TextInputEditText etHabitName;
    private TextInputEditText etTarget;
    private TextInputEditText etNotes;

    private Spinner spCategory;

    private SwitchCompat switchReminder;

    private MaterialButton btnSaveHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

        initializeViews();
        setupSpinner();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);

        etHabitName = findViewById(R.id.etHabitName);
        etTarget = findViewById(R.id.etTarget);
        etNotes = findViewById(R.id.etNotes);

        spCategory = findViewById(R.id.spCategory);

        switchReminder = findViewById(R.id.switchReminder);

        btnSaveHabit = findViewById(R.id.btnSaveHabit);
    }

    private void setupSpinner() {

        String[] categories = {
                "Health",
                "Fitness",
                "Study",
                "Productivity",
                "Wellness",
                "Personal"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                categories
        );

        spCategory.setAdapter(adapter);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnSaveHabit.setOnClickListener(v -> saveHabit());
    }

    private void saveHabit() {

        String habitName = etHabitName.getText().toString().trim();
        String target = etTarget.getText().toString().trim();

        if (habitName.isEmpty()) {
            etHabitName.setError("Enter habit name");
            return;
        }

        if (target.isEmpty()) {
            etTarget.setError("Enter daily target");
            return;
        }

        String category = spCategory.getSelectedItem().toString();
        boolean reminderEnabled = switchReminder.isChecked();
        String notes = etNotes.getText().toString().trim();

        ClomiPreferenceManager preferences = new ClomiPreferenceManager(this);
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");

        CustomHabit habit = new CustomHabit(
                username,
                habitName,
                target,
                category,
                reminderEnabled,
                notes



        );

        ClomiDatabase.getInstance(this).customHabitDao().insertOrUpdate(habit);

        Toast.makeText(this, "Habit created successfully!", Toast.LENGTH_SHORT).show();
        finish();

    }
}