package com.example.clomi.activities.onboarding;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class SleepSetupActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnContinue;

    private RadioGroup rgSleepHours;

    private TextInputEditText etBedTime;
    private TextInputEditText etWakeTime;

    private SwitchCompat switchReminder;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_setup);

        initializeViews();

        btnBack.setOnClickListener(v -> finish());

        etBedTime.setOnClickListener(v -> showTimePicker(etBedTime));

        etWakeTime.setOnClickListener(v -> showTimePicker(etWakeTime));

        btnContinue.setOnClickListener(v -> validateInputs());
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);

        rgSleepHours = findViewById(R.id.rgSleepHours);

        etBedTime = findViewById(R.id.etBedTime);
        etWakeTime = findViewById(R.id.etWakeTime);

        switchReminder = findViewById(R.id.switchReminder);
    }

    private void showTimePicker(TextInputEditText editText) {

        TimePickerDialog picker = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {

                    String time = String.format(
                            Locale.getDefault(),
                            "%02d:%02d",
                            hourOfDay,
                            minute
                    );

                    editText.setText(time);

                },
                22,
                0,
                false
        );

        picker.show();
    }

    private void validateInputs() {

        if (rgSleepHours.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please select your sleep duration.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (etBedTime.getText().toString().trim().isEmpty()) {

            etBedTime.setError("Select your bedtime");
            return;
        }

        if (etWakeTime.getText().toString().trim().isEmpty()) {

            etWakeTime.setError("Select your wake-up time");
            return;
        }

        Intent intent = new Intent(
                SleepSetupActivity.this,
                PeriodSetupActivity.class
        );

        startActivity(intent);
        finish();

    }
}