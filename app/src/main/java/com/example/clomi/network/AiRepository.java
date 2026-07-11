package com.example.clomi.network;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.clomi.db.ChatMessageDao;
import com.example.clomi.db.ClomiDatabase;
import com.example.clomi.model.ChatMessage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AiRepository {

    private final ChatMessageDao chatMessageDao;
    private final LiveData<List<ChatMessage>> allMessages;
    private final ExecutorService executorService;

    public AiRepository(Application application) {
        ClomiDatabase db = ClomiDatabase.getInstance(application);
        chatMessageDao = db.chatMessageDao();
        allMessages = chatMessageDao.getAllMessages();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<ChatMessage>> getAllMessages() {
        return allMessages;
    }

    public void insert(ChatMessage message) {
        executorService.execute(() -> chatMessageDao.insert(message));
    }

    public void clearHistory() {
        executorService.execute(chatMessageDao::deleteAll);
    }
    
    // In a real app, you would add a method here to call Retrofit 
    // and then save the AI response to the database.
}
