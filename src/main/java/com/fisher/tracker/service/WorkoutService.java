package com.fisher.tracker.service;

import com.fisher.tracker.model.Workout;
import com.fisher.tracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    public void addWorkout(LocalDate date) {
        Workout workout = new Workout();
        workout.setDate(date);
        workoutRepository.save(workout);
    }

    public List<Workout> getWorkoutsForYear(int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        return workoutRepository.findByDateBetween(start, end);
    }

    public void deleteWorkout(LocalDate date) {
        List<Workout> workouts = workoutRepository.findByDateBetween(date, date);
        workoutRepository.deleteAll(workouts);
    }
}