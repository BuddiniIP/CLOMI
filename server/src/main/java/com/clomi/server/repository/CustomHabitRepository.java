package com.clomi.server.repository;

import com.clomi.server.model.CustomHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomHabitRepository extends JpaRepository<CustomHabit, Long> {
    List<CustomHabit> findByUserId(Long userId);
}