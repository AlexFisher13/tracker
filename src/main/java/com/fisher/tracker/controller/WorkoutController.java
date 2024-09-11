package com.fisher.tracker.controller;

import com.fisher.tracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/")
    public String showCalendar(Model model) {
        int currentYear = LocalDate.now().getYear();
        List<LocalDate> workoutDates = workoutService.getWorkoutsForYear(currentYear)
                .stream()
                .map(workout -> workout.getDate())
                .collect(Collectors.toList());
        model.addAttribute("workouts", workoutDates);
        model.addAttribute("currentYear", currentYear);
        return "calendar";
    }

    @PostMapping("/add-workout")
    public String addWorkout(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        workoutService.addWorkout(date);
        return "redirect:/";
    }
}