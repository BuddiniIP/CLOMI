package com.example.clomi.manager;

import android.content.Context;
import android.util.Log;

import com.example.clomi.db.ClomiDatabase;
import com.example.clomi.model.CustomHabit;
import com.example.clomi.model.HabitLog;
import com.example.clomi.model.User;
import com.example.clomi.network.RetrofitClient;
import com.example.clomi.preferences.ClomiPreferenceManager;
import com.example.clomi.preferences.PreferenceKeys;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncManager {

    private static final String TAG = "SyncManager";

    public static void syncProfile(Context context) {
        ClomiPreferenceManager preferences = new ClomiPreferenceManager(context);
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");
        if (username.isEmpty()) return;

        User user = new User();
        user.setUsername(username);
        user.setEmail(preferences.getString("EMAIL", ""));
        user.setGender(preferences.getString(PreferenceKeys.GENDER, ""));
        user.setAge(preferences.getString(PreferenceKeys.AGE, ""));
        user.setMainGoals(preferences.getString(PreferenceKeys.MAIN_GOALS, ""));
        user.setActivityLevel(preferences.getString(PreferenceKeys.ACTIVITY_LEVEL, ""));
        user.setWaterGoal(preferences.getString(PreferenceKeys.WATER_GOAL, "2.5 L"));
        user.setSleepHours(preferences.getString(PreferenceKeys.SLEEP_HOURS, "8 Hours"));
        user.setSleepReminder(preferences.getBoolean(PreferenceKeys.SLEEP_REMINDER, false));
        user.setSkinType(preferences.getString(PreferenceKeys.SKIN_TYPE, ""));
        user.setSkincareReminder(preferences.getBoolean(PreferenceKeys.SKINCARE_REMINDER, false));
        user.setPeriodEnabled(preferences.getBoolean(PreferenceKeys.PERIOD_ENABLED, false));
        user.setLastPeriodDate(preferences.getString(PreferenceKeys.LAST_PERIOD_DATE, ""));
        user.setCycleLength(preferences.getString(PreferenceKeys.CYCLE_LENGTH, ""));
        user.setPeriodLength(preferences.getString(PreferenceKeys.PERIOD_LENGTH, ""));
        user.setMoodCheck(preferences.getString(PreferenceKeys.MOOD_CHECK, ""));
        user.setMotivationEnabled(preferences.getBoolean(PreferenceKeys.MOTIVATION_ENABLED, false));
        user.setXp(preferences.getInt(PreferenceKeys.XP, 0));
        user.setStreak(preferences.getInt(PreferenceKeys.STREAK, 0));

        RetrofitClient.getApiService().syncProfile(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User syncedUser = response.body();
                    if (syncedUser.getXp() != null && syncedUser.getXp() > preferences.getInt(PreferenceKeys.XP, 0)) {
                        preferences.putInt(PreferenceKeys.XP, syncedUser.getXp());
                    }
                    if (syncedUser.getStreak() != null && syncedUser.getStreak() > preferences.getInt(PreferenceKeys.STREAK, 0)) {
                        preferences.putInt(PreferenceKeys.STREAK, syncedUser.getStreak());
                    }
                    Log.d(TAG, "Profile synced successfully!");
                } else {
                    Log.e(TAG, "Profile sync failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Profile sync error", t);
            }
        });
    }

    public static void syncHabitLogs(Context context) {
        ClomiPreferenceManager preferences = new ClomiPreferenceManager(context);
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");
        if (username.isEmpty()) return;

        ClomiDatabase db = ClomiDatabase.getInstance(context);
        List<HabitLog> unsynced = db.habitLogDao().getUnsyncedLogs(username);
        if (unsynced == null || unsynced.isEmpty()) return;

        RetrofitClient.getApiService().syncHabitLogs(unsynced).enqueue(new Callback<List<HabitLog>>() {
            @Override
            public void onResponse(Call<List<HabitLog>> call, Response<List<HabitLog>> response) {
                if (response.isSuccessful()) {
                    new Thread(() -> {
                        for (HabitLog log : unsynced) {
                            db.habitLogDao().markSynced(log.getUsername(), log.getDate(), log.getHabitType());
                        }
                        Log.d(TAG, "Habit logs synced successfully!");
                    }).start();
                } else {
                    Log.e(TAG, "Habit logs sync failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<HabitLog>> call, Throwable t) {
                Log.e(TAG, "Habit logs sync error", t);
            }
        });
    }

    public static void syncCustomHabits(Context context) {
        ClomiPreferenceManager preferences = new ClomiPreferenceManager(context);
        String username = preferences.getString(PreferenceKeys.USER_NAME, "");
        if (username.isEmpty()) return;

        ClomiDatabase db = ClomiDatabase.getInstance(context);
        List<CustomHabit> habits = db.customHabitDao().getCustomHabits(username);
        if (habits == null || habits.isEmpty()) return;

        RetrofitClient.getApiService().syncCustomHabits(habits).enqueue(new Callback<List<CustomHabit>>() {
            @Override
            public void onResponse(Call<List<CustomHabit>> call, Response<List<CustomHabit>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Custom habits synced successfully!");
                } else {
                    Log.e(TAG, "Custom habits sync failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CustomHabit>> call, Throwable t) {
                Log.e(TAG, "Custom habits sync error", t);
            }
        });
    }

    public static void performFullSync(Context context) {
        syncProfile(context);
        syncHabitLogs(context);
        syncCustomHabits(context);
    }
}
