package com.clomi.server.repository;

import com.clomi.server.model.CustomHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomHabitRepository extends JpaRepository<CustomHabit, Long> {
    List<CustomHabit> findByUsername(String username);
    Optional<CustomHabit> findByUsernameAndHabitName(String username, String habitName);
}