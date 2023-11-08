package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.WorkoutDTO;
import com.softuni.fitlaunch.model.entity.ExerciseEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.model.enums.LevelEnum;
import com.softuni.fitlaunch.repository.ExerciseRepository;
import com.softuni.fitlaunch.repository.WorkoutRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public Page<WorkoutDTO> getAllWorkouts(Pageable pageable) {
        return workoutRepository
                .findAll(pageable)
                .map(WorkoutService::mapAsSummary);
    }

    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(WorkoutService::mapAsDTO)
                .toList();
    }

    public List<LevelEnum> getAllLevels() {
        return Arrays.stream(LevelEnum.values()).collect(Collectors.toList());
    }

//    public Long createWorkout(CreateWorkoutDTO createWorkoutDTO) {
//
//    }

    private static WorkoutDTO mapAsSummary(WorkoutEntity workoutEntity) {
        return new WorkoutDTO(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getImgUrl(),
                workoutEntity.getLevel(),
                workoutEntity.getDescription()
        );
    }


    private static ExerciseDTO mapAsDTO(ExerciseEntity exerciseEntity) {
        return new ExerciseDTO(
                exerciseEntity.getId(),
                exerciseEntity.getName()
        );
    }

}
