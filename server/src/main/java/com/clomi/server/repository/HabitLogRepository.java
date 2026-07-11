package com.clomi.server.repository;

import com.clomi.server.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    List<HabitLog> findByUserId(Long userId);
    List<HabitLog> findByUserIdAndDate(Long userId, String date);
}