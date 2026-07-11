package com.example.clomi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Index;

@Entity(tableName = "custom_habits", indices = {@Index(value = {"username", "habitName"}, unique = true)})
public class CustomHabit {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String username;
    private String habitName;
    private String target;
    private String category;
    private boolean reminderEnabled;
    private String notes;

    // Constructors
    public CustomHabit() {}

    public CustomHabit(String username, String habitName, String target, String category, boolean reminderEnabled, String notes) {
        this.username = username;
        this.habitName = habitName;
        this.target = target;
        this.category = category;
        this.reminderEnabled = reminderEnabled;
        this.notes = notes;
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

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isReminderEnabled() {
        return reminderEnabled;
    }

    public void setReminderEnabled(boolean reminderEnabled) {
        this.reminderEnabled = reminderEnabled;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
