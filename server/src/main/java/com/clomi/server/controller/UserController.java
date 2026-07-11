package com.clomi.server.controller;

import com.clomi.server.model.User;
import com.clomi.server.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/sync")
    public User syncProfile(@RequestBody User incomingUser) {
        User existing = userRepository.findByUsername(incomingUser.getUsername()).orElse(null);

        if (existing == null) {
            // New user — save as-is, userId auto-generated
            return userRepository.save(incomingUser);
        }

        // Existing user — update fields, keep the same userId
        existing.setEmail(incomingUser.getEmail());
        existing.setGender(incomingUser.getGender());
        existing.setAge(incomingUser.getAge());
        existing.setMainGoals(incomingUser.getMainGoals());
        existing.setActivityLevel(incomingUser.getActivityLevel());
        existing.setWaterGoal(incomingUser.getWaterGoal());
        existing.setSleepHours(incomingUser.getSleepHours());
        existing.setSleepReminder(incomingUser.isSleepReminder());
        existing.setSkinType(incomingUser.getSkinType());
        existing.setSkincareReminder(incomingUser.isSkincareReminder());
        existing.setPeriodEnabled(incomingUser.isPeriodEnabled());
        existing.setLastPeriodDate(incomingUser.getLastPeriodDate());
        existing.setCycleLength(incomingUser.getCycleLength());
        existing.setPeriodLength(incomingUser.getPeriodLength());
        existing.setMoodCheck(incomingUser.getMoodCheck());
        existing.setMotivationEnabled(incomingUser.isMotivationEnabled());
        // xp/streak are intentionally NOT overwritten here — see updateXp/updateStreak below

        return userRepository.save(existing);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @PostMapping("/{username}/xp")
    public User updateXp(@PathVariable String username, @RequestParam int xp) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return null;
        user.setXp(xp);
        return userRepository.save(user);
    }

    @PostMapping("/{username}/streak")
    public User updateStreak(@PathVariable String username, @RequestParam int streak) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return null;
        user.setStreak(streak);
        return userRepository.save(user);
    }
}