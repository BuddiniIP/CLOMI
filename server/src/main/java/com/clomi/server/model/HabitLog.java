package com.clomi.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "habit_logs")
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;      // ← changed from username
    private String date;
    private String habitType;
    private double value;
    private boolean completed;

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getHabitType() { return habitType; }
    public void setHabitType(String habitType) { this.habitType = habitType; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}