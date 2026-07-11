package com.example.clomi.network;

import com.example.clomi.model.CustomHabit;
import com.example.clomi.model.HabitLog;
import com.example.clomi.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClomiApiService {

    @POST("api/users/sync")
    Call<User> syncProfile(@Body User userProfile);

    @GET("api/users/{username}")
    Call<User> getUser(@Path("username") String username);

    @POST("api/users/{username}/xp")
    Call<User> updateXp(@Path("username") String username, @Query("xp") int xp);

    @POST("api/users/{username}/streak")
    Call<User> updateStreak(@Path("username") String username, @Query("streak") int streak);

    @POST("api/habits/sync")
    Call<List<HabitLog>> syncHabitLogs(@Body List<HabitLog> logs);

    @GET("api/habits/{username}/date/{date}")
    Call<List<HabitLog>> getHabitLogsByDate(@Path("username") String username, @Path("date") String date);

    @POST("api/custom-habits/sync")
    Call<List<CustomHabit>> syncCustomHabits(@Body List<CustomHabit> habits);

    @GET("api/custom-habits/{username}")
    Call<List<CustomHabit>> getCustomHabits(@Path("username") String username);

    @POST("api/ai/chat")
    Call<AiResponse> getAiResponse(@Body ChatRequest request);

    class ChatRequest {
        String message;
        public ChatRequest(String message) { this.message = message; }
    }

    class AiResponse {
        String response;
        public String getResponse() { return response; }
    }
}
