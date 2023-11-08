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
                .map(WorkoutService::mapAsWorkoutDTO);
    }

    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(WorkoutService::mapAsExerciseDTO)
                .toList();
    }

    public List<LevelEnum> getAllLevels() {
        return Arrays.stream(LevelEnum.values()).collect(Collectors.toList());
    }

    public Long createWorkout(CreateWorkoutDTO createWorkoutDTO) {
        WorkoutEntity newWorkout = map(createWorkoutDTO);

        newWorkout = workoutRepository.save(newWorkout);

        return newWorkout.getId();
    }

    private static WorkoutDTO mapAsWorkoutDTO(WorkoutEntity workoutEntity) {
        return new WorkoutDTO(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getImgUrl(),
                workoutEntity.getLevel(),
                workoutEntity.getDescription()
        );
    }

    private static WorkoutEntity map(CreateWorkoutDTO workoutDTO) {

        List<ExerciseEntity> exercises = workoutDTO.getExercises().stream().map(WorkoutService::mapAsExerciseEntity).toList();

        return new WorkoutEntity()
                .setName(workoutDTO.getName())
                .setLevel(workoutDTO.getLevel())
                .setDescription(workoutDTO.getDescription())
                .setImgUrl(workoutDTO.getImgUrl())
                .setExercises(exercises);
    }


    private static ExerciseDTO mapAsExerciseDTO(ExerciseEntity exerciseEntity) {
        return new ExerciseDTO(
                exerciseEntity.getId(),
                exerciseEntity.getName()
        );
    }

    private static ExerciseEntity mapAsExerciseEntity(ExerciseDTO exerciseDTO) {
        return new ExerciseEntity()
                .setId(exerciseDTO.getId())
                .setName(exerciseDTO.getName());
    }

}
