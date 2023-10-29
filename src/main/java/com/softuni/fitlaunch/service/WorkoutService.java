package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }
}
