package com.example.clomi.activities.onboarding;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class PeriodSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;
    private TextView tvSkip;

    private TextInputEditText etLastPeriodDate;
    private TextInputEditText etCycleLength;
    private TextInputEditText etPeriodLength;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_setup);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);
        tvSkip = findViewById(R.id.tvSkip);

        etLastPeriodDate = findViewById(R.id.etLastPeriodDate);
        etCycleLength = findViewById(R.id.etCycleLength);
        etPeriodLength = findViewById(R.id.etPeriodLength);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        etLastPeriodDate.setOnClickListener(v -> showDatePicker());

        btnContinue.setOnClickListener(v -> savePeriodData());

        tvSkip.setOnClickListener(v -> {

            preferences.putBoolean(
                    PreferenceKeys.PERIOD_ENABLED,
                    false
            );

            startActivity(new Intent(
                    PeriodSetupActivity.this,
                    AboutYouActivity.class
            ));

            finish();
        });
    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog picker = new DatePickerDialog(
                this,
                (view, year, month, day) -> {

                    String date = String.format(
                            "%02d/%02d/%04d",
                            day,
                            month + 1,
                            year
                    );

                    etLastPeriodDate.setText(date);

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        picker.show();
    }

    private void savePeriodData() {

        String lastDate = etLastPeriodDate.getText().toString().trim();
        String cycle = etCycleLength.getText().toString().trim();
        String period = etPeriodLength.getText().toString().trim();

        if (lastDate.isEmpty()) {
            etLastPeriodDate.setError("Select last period date");
            return;
        }

        if (cycle.isEmpty()) {
            etCycleLength.setError("Enter cycle length");
            return;
        }

        if (period.isEmpty()) {
            etPeriodLength.setError("Enter period length");
            return;
        }

        int cycleLength = Integer.parseInt(cycle);
        int periodLength = Integer.parseInt(period);

        if (cycleLength < 15 || cycleLength > 60) {
            etCycleLength.setError("Enter 15 - 60 days");
            return;
        }

        if (periodLength < 2 || periodLength > 15) {
            etPeriodLength.setError("Enter 2 - 15 days");
            return;
        }

        preferences.putBoolean(
                PreferenceKeys.PERIOD_ENABLED,
                true
        );

        preferences.putString(
                PreferenceKeys.LAST_PERIOD_DATE,
                lastDate
        );

        preferences.putString(
                PreferenceKeys.CYCLE_LENGTH,
                cycle
        );

        preferences.putString(
                PreferenceKeys.PERIOD_LENGTH,
                period
        );

        startActivity(new Intent(
                PeriodSetupActivity.this,
                AboutYouActivity.class
        ));

        finish();
    }
}