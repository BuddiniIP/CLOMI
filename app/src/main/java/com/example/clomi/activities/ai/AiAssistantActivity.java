package com.example.clomi.activities.ai;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

public class AiAssistantActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageButton btnSend;

    private TextInputEditText etMessage;

    private TextView tvWelcome;
    private TextView tvUserMessage;
    private TextView tvAiReply;

    private Chip chipWater;
    private Chip chipSleep;
    private Chip chipSkin;
    private Chip chipMotivation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_assistant);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        btnSend = findViewById(R.id.btnSend);

        etMessage = findViewById(R.id.etMessage);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvUserMessage = findViewById(R.id.tvUserMessage);
        tvAiReply = findViewById(R.id.tvAiReply);

        chipWater = findViewById(R.id.chipWater);
        chipSleep = findViewById(R.id.chipSleep);
        chipSkin = findViewById(R.id.chipSkin);
        chipMotivation = findViewById(R.id.chipMotivation);
    }

    private void setupListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnSend.setOnClickListener(v -> sendMessage());

        chipWater.setOnClickListener(v -> {
            etMessage.setText("How much water should I drink today?");
            sendMessage();
        });

        chipSleep.setOnClickListener(v -> {
            etMessage.setText("Give me some sleep improvement tips.");
            sendMessage();
        });

        chipSkin.setOnClickListener(v -> {
            etMessage.setText("Recommend a skincare routine.");
            sendMessage();
        });

        chipMotivation.setOnClickListener(v -> {
            etMessage.setText("Motivate me to complete my habits.");
            sendMessage();
        });
    }

    private void sendMessage() {

        String message = etMessage.getText().toString().trim();

        if (message.isEmpty()) {
            etMessage.setError("Please enter a message");
            return;
        }

        tvUserMessage.setText(message);

        String lower = message.toLowerCase();

        if (lower.contains("water")) {

            tvAiReply.setText(
                    "💧 Based on your wellness journey, aim to drink about 2–2.5 liters of water today. Drink consistently throughout the day instead of all at once."
            );

        } else if (lower.contains("sleep")) {

            tvAiReply.setText(
                    "😴 Try to sleep 7–9 hours each night. Avoid screens 30 minutes before bedtime and keep a consistent sleep schedule."
            );

        } else if (lower.contains("skin")) {

            tvAiReply.setText(
                    "🌿 Cleanse your face twice a day, moisturize daily, and always apply sunscreen when going outdoors."
            );

        } else if (lower.contains("motivate")
                || lower.contains("motivation")
                || lower.contains("habit")) {

            tvAiReply.setText(
                    "❤️ Every healthy habit you complete brings you closer to your goals. Small daily actions create big long-term results. Keep going!"
            );

        } else if (lower.contains("exercise")
                || lower.contains("workout")) {

            tvAiReply.setText(
                    "🏃 Try at least 30 minutes of moderate exercise today. Even a brisk walk can improve your physical and mental health."
            );

        } else if (lower.contains("food")
                || lower.contains("diet")) {

            tvAiReply.setText(
                    "🥗 Eat balanced meals with vegetables, protein, fruits, and enough water. Avoid skipping meals whenever possible."
            );

        } else {

            tvAiReply.setText(
                    "🤖 Thank you for your question! CLOMI AI is currently running in offline mode. Future updates will provide more personalized AI-powered wellness advice."
            );
        }

        etMessage.setText("");
    }
}