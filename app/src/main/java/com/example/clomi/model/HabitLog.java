package com.example.clomi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Index;

@Entity(tableName = "habit_logs", indices = {@Index(value = {"username", "date", "habitType"}, unique = true)})
public class HabitLog {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String username;
    private String date; // Format: YYYY-MM-DD
    private String habitType; // "Water", "Sleep", "Mood", "Skincare", or custom name
    private int value;
    private int target;
    private boolean completed;
    private boolean synced; // Custom field to track backend syncing status

    // Constructors
    public HabitLog() {}

    public HabitLog(String username, String date, String habitType, int value, int target, boolean completed, boolean synced) {
        this.username = username;
        this.date = date;
        this.habitType = habitType;
        this.value = value;
        this.target = target;
        this.completed = completed;
        this.synced = synced;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHabitType() {
        return habitType;
    }

    public void setHabitType(String habitType) {
        this.habitType = habitType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }
}
