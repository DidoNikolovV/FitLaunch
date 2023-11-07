package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.WorkoutDTO;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.model.enums.LevelEnum;
import com.softuni.fitlaunch.repository.WorkoutRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Page<WorkoutDTO> getAllWorkouts(Pageable pageable) {
        return workoutRepository
                .findAll(pageable)
                .map(WorkoutService::mapAsSummary);
    }

    private static WorkoutDTO mapAsSummary(WorkoutEntity workoutEntity) {
        return new WorkoutDTO(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getImgUrl(),
                workoutEntity.getLevel(),
                workoutEntity.getDescription()
        );
    }
}
