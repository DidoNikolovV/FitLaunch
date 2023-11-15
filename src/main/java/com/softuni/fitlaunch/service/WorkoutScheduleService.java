package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.entity.WorkoutScheduleEntity;
import com.softuni.fitlaunch.repository.UserRepository;
import com.softuni.fitlaunch.repository.WorkoutRepository;
import com.softuni.fitlaunch.repository.WorkoutScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutScheduleService {

    private final WorkoutScheduleRepository workoutScheduleRepository;
    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;

    public WorkoutScheduleService(WorkoutScheduleRepository workoutScheduleRepository, UserRepository userRepository, WorkoutRepository workoutRepository) {
        this.workoutScheduleRepository = workoutScheduleRepository;
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
    }

    public void scheduleWorkout(Long userId, Long workoutId, String scheduleTime) {
        WorkoutScheduleEntity schedule = new WorkoutScheduleEntity();
        schedule.setUser(userRepository.findById(userId).orElse(null));
        schedule.setWorkout(workoutRepository.findById(workoutId).orElse(null));
        schedule.setScheduledDate(scheduleTime);

        workoutScheduleRepository.save(schedule);
    }

    public List<WorkoutScheduleEntity> getScheduledWorkouts() {
        return workoutScheduleRepository.findAll();
    }
}
