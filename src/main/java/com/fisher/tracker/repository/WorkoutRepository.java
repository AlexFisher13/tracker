package com.fisher.tracker.repository;

import com.fisher.tracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByDateBetween(LocalDate start, LocalDate end);
}