package com.example.clomi.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clomi.model.ChatMessage;

import java.util.List;

@Dao
public interface ChatMessageDao {

    @Insert
    void insert(ChatMessage message);

    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    LiveData<List<ChatMessage>> getAllMessages();

    @Query("DELETE FROM chat_messages")
    void deleteAll();
}
