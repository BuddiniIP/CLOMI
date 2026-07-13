package com.example.clomi.activities.ai;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clomi.R;
import com.example.clomi.network.ClomiApiService;
import com.example.clomi.network.RetrofitClient;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        tvAiReply.setText("Thinking...");
        etMessage.setText("");

        ClomiApiService.ChatRequest request = new ClomiApiService.ChatRequest(message);

        RetrofitClient.getApiService().getAiResponse(request).enqueue(new Callback<ClomiApiService.AiResponse>() {
            @Override
            public void onResponse(Call<ClomiApiService.AiResponse> call, Response<ClomiApiService.AiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tvAiReply.setText(response.body().getResponse());
                } else {
                    tvAiReply.setText("Sorry, something went wrong. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<ClomiApiService.AiResponse> call, Throwable t) {
                tvAiReply.setText("🤖 Couldn't reach the AI assistant. Please check your connection and try again.");
            }
        });
    }
}