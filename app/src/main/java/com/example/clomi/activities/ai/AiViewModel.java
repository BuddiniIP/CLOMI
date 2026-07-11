package com.example.clomi.activities.ai;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.clomi.model.ChatMessage;
import com.example.clomi.network.AiRepository;

import java.util.List;

public class AiViewModel extends AndroidViewModel {

    private final AiRepository repository;
    private final LiveData<List<ChatMessage>> allMessages;

    public AiViewModel(@NonNull Application application) {
        super(application);
        repository = new AiRepository(application);
        allMessages = repository.getAllMessages();
    }

    public LiveData<List<ChatMessage>> getAllMessages() {
        return allMessages;
    }

    public void sendMessage(String text) {
        // Save user message
        ChatMessage userMsg = new ChatMessage(text, System.currentTimeMillis(), true);
        repository.insert(userMsg);

        // Here you would trigger a network call to your backend
        // For now, let's simulate a local AI response
        simulateAiResponse(text);
    }

    private void simulateAiResponse(String userText) {
        // Simple logic for demonstration
        String responseText = "🤖 I received: " + userText + ". I'll be connected to a real backend soon!";
        
        ChatMessage aiMsg = new ChatMessage(responseText, System.currentTimeMillis(), false);
        repository.insert(aiMsg);
    }
}
