package com.example.clomi.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.clomi.model.ChatMessage;
import com.example.clomi.model.CustomHabit;
import com.example.clomi.model.HabitLog;

@Database(entities = {CustomHabit.class, HabitLog.class, ChatMessage.class}, version = 2, exportSchema = false)
public abstract class ClomiDatabase extends RoomDatabase {

    private static volatile ClomiDatabase INSTANCE;

    public abstract CustomHabitDao customHabitDao();
    public abstract HabitLogDao habitLogDao();
    public abstract ChatMessageDao chatMessageDao();

    public static ClomiDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ClomiDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ClomiDatabase.class,
                            "clomi_local_db"
                    )
                    .allowMainThreadQueries() // Allow main thread queries for quick local UI state updates
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
