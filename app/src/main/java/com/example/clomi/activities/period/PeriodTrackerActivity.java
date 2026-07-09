package com.example.clomi.activities.period;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.clomi.R;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PeriodTrackerActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private TextView tvCurrentDay;
    private TextView tvNextPeriod;
    private TextView tvCycleLength;
    private TextView tvPrediction;
    private TextView tvFertility;

    private TextInputEditText etLastPeriod;
    private TextInputEditText etCycleLength;
    private TextInputEditText etPeriodLength;

    private MaterialButton btnSave;

    private ClomiPreferenceManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_tracker);

        preferences = new ClomiPreferenceManager(this);

        initializeViews();
        loadPeriodData();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);

        tvCurrentDay = findViewById(R.id.tvCurrentDay);
        tvNextPeriod = findViewById(R.id.tvNextPeriod);
        tvCycleLength = findViewById(R.id.tvCycleLength);
        tvPrediction = findViewById(R.id.tvPrediction);
        tvFertility = findViewById(R.id.tvFertility);

        etLastPeriod = findViewById(R.id.etLastPeriod);
        etCycleLength = findViewById(R.id.etCycleLength);
        etPeriodLength = findViewById(R.id.etPeriodLength);

        btnSave = findViewById(R.id.btnSave);
    }

    private void loadPeriodData() {

        etLastPeriod.setText(
                preferences.getString(
                        PreferenceKeys.LAST_PERIOD_DATE,
                        ""
                )
        );

        etCycleLength.setText(
                preferences.getString(
                        PreferenceKeys.CYCLE_LENGTH,
                        "28"
                )
        );

        etPeriodLength.setText(
                preferences.getString(
                        PreferenceKeys.PERIOD_LENGTH,
                        "5"
                )
        );
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> savePeriodData());
    }

    private void savePeriodData() {

        preferences.putString(
                PreferenceKeys.LAST_PERIOD_DATE,
                etLastPeriod.getText().toString().trim()
        );

        preferences.putString(
                PreferenceKeys.CYCLE_LENGTH,
                etCycleLength.getText().toString().trim()
        );

        preferences.putString(
                PreferenceKeys.PERIOD_LENGTH,
                etPeriodLength.getText().toString().trim()
        );

        Toast.makeText(
                this,
                "Period information saved successfully!",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }
}