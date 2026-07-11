package com.example.clomi.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.clomi.model.HabitLog;

import java.util.List;

@Dao
public interface HabitLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(HabitLog log);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(List<HabitLog> logs);

    @Query("SELECT * FROM habit_logs WHERE username = :username AND date = :date")
    List<HabitLog> getLogsForDate(String username, String date);

    @Query("SELECT * FROM habit_logs WHERE username = :username AND date = :date AND habitType = :habitType LIMIT 1")
    HabitLog getLog(String username, String date, String habitType);

    @Query("SELECT * FROM habit_logs WHERE username = :username AND synced = 0")
    List<HabitLog> getUnsyncedLogs(String username);

    @Query("UPDATE habit_logs SET synced = 1 WHERE username = :username AND date = :date AND habitType = :habitType")
    void markSynced(String username, String date, String habitType);
}
