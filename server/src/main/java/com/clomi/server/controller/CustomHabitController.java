package com.clomi.server.controller;

import com.clomi.server.model.CustomHabit;
import com.clomi.server.repository.CustomHabitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/custom-habits")
public class CustomHabitController {

    private final CustomHabitRepository customHabitRepository;
    private static final Object LOCK = new Object();

    public CustomHabitController(CustomHabitRepository customHabitRepository) {
        this.customHabitRepository = customHabitRepository;
    }

    @GetMapping("/{username}")
    public List<CustomHabit> getCustomHabits(@PathVariable String username) {
        return customHabitRepository.findByUsername(username);
    }

    @PostMapping("/sync")
    public List<CustomHabit> syncCustomHabits(@RequestBody List<CustomHabit> incomingHabits) {
        synchronized (LOCK) {
            List<CustomHabit> savedHabits = new ArrayList<>();

            for (CustomHabit incoming : incomingHabits) {
                CustomHabit existing = customHabitRepository
                        .findByUsernameAndHabitName(incoming.getUsername(), incoming.getHabitName())
                        .orElse(null);

                if (existing == null) {
                    CustomHabit fresh = new CustomHabit();
                    fresh.setUsername(incoming.getUsername());
                    fresh.setHabitName(incoming.getHabitName());
                    fresh.setTarget(incoming.getTarget());
                    fresh.setCategory(incoming.getCategory());
                    fresh.setReminderEnabled(incoming.isReminderEnabled());
                    fresh.setNotes(incoming.getNotes());
                    savedHabits.add(customHabitRepository.save(fresh));
                } else {
                    existing.setTarget(incoming.getTarget());
                    existing.setCategory(incoming.getCategory());
                    existing.setReminderEnabled(incoming.isReminderEnabled());
                    existing.setNotes(incoming.getNotes());
                    savedHabits.add(customHabitRepository.save(existing));
                }
            }

            return savedHabits;
        }
    }

    @DeleteMapping("/{username}/{habitName}")
    public void deleteCustomHabit(@PathVariable String username, @PathVariable String habitName) {
        customHabitRepository.findByUsernameAndHabitName(username, habitName)
                .ifPresent(customHabitRepository::delete);
    }
}