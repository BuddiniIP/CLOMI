package com.example.clomi.model;

public class User {

    private Long id;
    private String username;
    private String email;
    private String gender;
    private String age;
    private String mainGoals;
    private String activityLevel;
    private String waterGoal;
    private String sleepHours;
    private Boolean sleepReminder;
    private String skinType;
    private Boolean skincareReminder;
    private Boolean periodEnabled;
    private String lastPeriodDate;
    private String cycleLength;
    private String periodLength;
    private String moodCheck;
    private Boolean motivationEnabled;
    private Integer xp = 0;
    private Integer streak = 0;

    // Constructors
    public User() {}

    public User(String username) {
        this.username = username;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMainGoals() {
        return mainGoals;
    }

    public void setMainGoals(String mainGoals) {
        this.mainGoals = mainGoals;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getWaterGoal() {
        return waterGoal;
    }

    public void setWaterGoal(String waterGoal) {
        this.waterGoal = waterGoal;
    }

    public String getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(String sleepHours) {
        this.sleepHours = sleepHours;
    }

    public boolean isSleepReminder() {
        return sleepReminder;
    }

    public void setSleepReminder(boolean sleepReminder) {
        this.sleepReminder = sleepReminder;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public boolean isSkincareReminder() {
        return skincareReminder;
    }

    public void setSkincareReminder(boolean skincareReminder) {
        this.skincareReminder = skincareReminder;
    }

    public Boolean getPeriodEnabled() {
        return periodEnabled;
    }

    public void setPeriodEnabled(Boolean periodEnabled) {
        this.periodEnabled = periodEnabled;
    }

    public String getLastPeriodDate() {
        return lastPeriodDate;
    }

    public void setLastPeriodDate(String lastPeriodDate) {
        this.lastPeriodDate = lastPeriodDate;
    }

    public String getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(String cycleLength) {
        this.cycleLength = cycleLength;
    }

    public String getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(String periodLength) {
        this.periodLength = periodLength;
    }

    public String getMoodCheck() { return moodCheck; }

    public void setMoodCheck(String moodCheck) { this.moodCheck = moodCheck; }

    public Boolean getMotivationEnabled() {
        return motivationEnabled;
    }

    public void setMotivationEnabled(Boolean motivationEnabled) {
        this.motivationEnabled = motivationEnabled;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getStreak() {
        return streak;
    }

    public void setStreak(Integer streak) {
        this.streak = streak;
    }
}
