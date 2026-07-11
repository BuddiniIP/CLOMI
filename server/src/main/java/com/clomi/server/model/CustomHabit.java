package com.clomi.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "custom_habits")
public class CustomHabit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;      // ← changed from username
    private String habitName;
    private String category;
    private double targetValue;

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getHabitName() { return habitName; }
    public void setHabitName(String habitName) { this.habitName = habitName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getTargetValue() { return targetValue; }
    public void setTargetValue(double targetValue) { this.targetValue = targetValue; }
}