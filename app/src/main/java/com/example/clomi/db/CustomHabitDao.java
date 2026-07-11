package com.example.clomi.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.clomi.model.CustomHabit;

import java.util.List;

@Dao
public interface CustomHabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(CustomHabit habit);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(List<CustomHabit> habits);

    @Query("SELECT * FROM custom_habits WHERE username = :username")
    List<CustomHabit> getCustomHabits(String username);

    @Query("SELECT * FROM custom_habits WHERE username = :username AND habitName = :habitName LIMIT 1")
    CustomHabit getCustomHabit(String username, String habitName);

    @Query("DELETE FROM custom_habits WHERE username = :username AND habitName = :habitName")
    void deleteCustomHabit(String username, String habitName);

    @Query("DELETE FROM custom_habits WHERE username = :username")
    void clearCustomHabits(String username);
}
