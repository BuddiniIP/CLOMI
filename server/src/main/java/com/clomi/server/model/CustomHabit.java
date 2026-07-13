package com.clomi.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "custom_habits")
public class CustomHabit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String habitName;
    private String target;
    private String category;
    private boolean reminderEnabled;
    private String notes;

    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getHabitName() { return habitName; }
    public void setHabitName(String habitName) { this.habitName = habitName; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isReminderEnabled() { return reminderEnabled; }
    public void setReminderEnabled(boolean reminderEnabled) { this.reminderEnabled = reminderEnabled; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}