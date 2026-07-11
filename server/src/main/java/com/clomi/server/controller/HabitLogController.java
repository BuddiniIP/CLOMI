package com.clomi.server.controller;

import com.clomi.server.model.HabitLog;
import com.clomi.server.repository.HabitLogRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitLogController {

    private final HabitLogRepository habitLogRepository;

    public HabitLogController(HabitLogRepository habitLogRepository) {
        this.habitLogRepository = habitLogRepository;
    }

    @GetMapping("/{userId}")
    public List<HabitLog> getAllLogs(@PathVariable Long userId) {
        return habitLogRepository.findByUserId(userId);
    }

    @GetMapping("/{userId}/date/{date}")
    public List<HabitLog> getLogsByDate(@PathVariable Long userId, @PathVariable String date) {
        return habitLogRepository.findByUserIdAndDate(userId, date);
    }

    @PostMapping("/sync")
    public List<HabitLog> syncLogs(@RequestBody List<HabitLog> logs) {
        return habitLogRepository.saveAll(logs);
    }
}