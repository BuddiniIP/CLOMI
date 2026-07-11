package com.clomi.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String email;

    private String gender;
    private String age;
    private String mainGoals;
    private String activityLevel;

    private String waterGoal;
    private String sleepHours;
    private boolean sleepReminder;

    private String skinType;
    private boolean skincareReminder;

    private boolean periodEnabled;
    private String lastPeriodDate;
    private String cycleLength;
    private String periodLength;

    private String moodCheck;
    private boolean motivationEnabled;

    private int xp = 0;
    private int streak = 0;

    // getters and setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getMainGoals() { return mainGoals; }
    public void setMainGoals(String mainGoals) { this.mainGoals = mainGoals; }

    public String getActivityLevel() { return activityLevel; }
    public void setActivityLevel(String activityLevel) { this.activityLevel = activityLevel; }

    public String getWaterGoal() { return waterGoal; }
    public void setWaterGoal(String waterGoal) { this.waterGoal = waterGoal; }

    public String getSleepHours() { return sleepHours; }
    public void setSleepHours(String sleepHours) { this.sleepHours = sleepHours; }

    public boolean isSleepReminder() { return sleepReminder; }
    public void setSleepReminder(boolean sleepReminder) { this.sleepReminder = sleepReminder; }

    public String getSkinType() { return skinType; }
    public void setSkinType(String skinType) { this.skinType = skinType; }

    public boolean isSkincareReminder() { return skincareReminder; }
    public void setSkincareReminder(boolean skincareReminder) { this.skincareReminder = skincareReminder; }

    public boolean isPeriodEnabled() { return periodEnabled; }
    public void setPeriodEnabled(boolean periodEnabled) { this.periodEnabled = periodEnabled; }

    public String getLastPeriodDate() { return lastPeriodDate; }
    public void setLastPeriodDate(String lastPeriodDate) { this.lastPeriodDate = lastPeriodDate; }

    public String getCycleLength() { return cycleLength; }
    public void setCycleLength(String cycleLength) { this.cycleLength = cycleLength; }

    public String getPeriodLength() { return periodLength; }
    public void setPeriodLength(String periodLength) { this.periodLength = periodLength; }

    public String getMoodCheck() { return moodCheck; }
    public void setMoodCheck(String moodCheck) { this.moodCheck = moodCheck; }

    public boolean isMotivationEnabled() { return motivationEnabled; }
    public void setMotivationEnabled(boolean motivationEnabled) { this.motivationEnabled = motivationEnabled; }

    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }

    public int getStreak() { return streak; }
    public void setStreak(int streak) { this.streak = streak; }
}