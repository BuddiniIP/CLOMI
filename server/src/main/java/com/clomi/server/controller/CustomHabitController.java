package com.clomi.server.controller;

import com.clomi.server.model.CustomHabit;
import com.clomi.server.repository.CustomHabitRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/custom-habits")
public class CustomHabitController {

    private final CustomHabitRepository customHabitRepository;

    public CustomHabitController(CustomHabitRepository customHabitRepository) {
        this.customHabitRepository = customHabitRepository;
    }

    @GetMapping("/{userId}")
    public List<CustomHabit> getCustomHabits(@PathVariable Long userId) {
        return customHabitRepository.findByUserId(userId);
    }

    @PostMapping("/sync")
    public List<CustomHabit> syncCustomHabits(@RequestBody List<CustomHabit> habits) {
        return customHabitRepository.saveAll(habits);
    }
}