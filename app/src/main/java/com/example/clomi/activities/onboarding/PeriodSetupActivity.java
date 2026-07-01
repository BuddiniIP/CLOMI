package com.example.clomi.activities.onboarding;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

public class PeriodSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgPeriod;

    private RadioButton rbEnable;
    private RadioButton rbSkip;

    private TextInputEditText etLastPeriod;
    private TextInputEditText etCycleLength;
    private TextInputEditText etPeriodLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_setup);

        initializeViews();

        btnBack.setOnClickListener(v -> finish());

        etLastPeriod.setOnClickListener(v -> showDatePicker());

        btnContinue.setOnClickListener(v -> validateInputs());

    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgPeriod = findViewById(R.id.rgPeriod);

        rbEnable = findViewById(R.id.rbEnable);
        rbSkip = findViewById(R.id.rbSkip);

        etLastPeriod = findViewById(R.id.etLastPeriod);
        etCycleLength = findViewById(R.id.etCycleLength);
        etPeriodLength = findViewById(R.id.etPeriodLength);

    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {

                    String date = String.format(
                            Locale.getDefault(),
                            "%02d/%02d/%04d",
                            dayOfMonth,
                            month + 1,
                            year
                    );

                    etLastPeriod.setText(date);

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        dialog.show();

    }

    private void validateInputs() {

        if (rgPeriod.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please choose an option.",
                    Toast.LENGTH_SHORT
            ).show();

            return;

        }

        if (rbEnable.isChecked()) {

            if (etLastPeriod.getText().toString().trim().isEmpty()) {

                etLastPeriod.setError("Select last period date");
                return;

            }

            if (etCycleLength.getText().toString().trim().isEmpty()) {

                etCycleLength.setError("Enter cycle length");
                return;

            }

            if (etPeriodLength.getText().toString().trim().isEmpty()) {

                etPeriodLength.setError("Enter period length");
                return;

            }

        }

        Intent intent = new Intent(
                PeriodSetupActivity.this,
                SkincareSetupActivity.class
        );

        startActivity(intent);
        finish();

    }

}