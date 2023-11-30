package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.workout.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.model.entity.WorkoutExerciseEntity;
import com.softuni.fitlaunch.model.enums.LevelEnum;
import com.softuni.fitlaunch.repository.ExerciseRepository;
import com.softuni.fitlaunch.repository.UserRepository;
import com.softuni.fitlaunch.repository.WorkoutExerciseRepository;
import com.softuni.fitlaunch.repository.WorkoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private static final String BASE_IMAGES_PATH = "/images/";
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Long createWorkout(WorkoutEntity workout, CreateWorkoutDTO createWorkoutDTO) {

        WorkoutEntity newWorkout = workoutRepository.save(workout);
        return newWorkout.getId();

    }

    public Page<WorkoutDTO> getAllWorkouts(Pageable pageable) {
        return workoutRepository
                .findAll(pageable)
                .map(entity -> modelMapper.map(entity, WorkoutDTO.class));
    }

    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ExerciseDTO.class))
                .toList();
    }

    public List<LevelEnum> getAllLevels() {
        return Arrays.stream(LevelEnum.values()).collect(Collectors.toList());
    }

    public Optional<WorkoutDetailsDTO> getWorkoutDetails(Long workoutId) {
        Optional<WorkoutDetailsDTO> workoutDetailsDTO = workoutRepository.findById(workoutId)
                .map(this::mapAsDetails);

        return workoutDetailsDTO;
    }


    private WorkoutDetailsDTO mapAsDetails(WorkoutEntity workoutEntity) {
        List<WorkoutExerciseEntity> exercises = workoutExerciseRepository.findByWorkoutId(workoutEntity.getId()).stream().toList();

        return new WorkoutDetailsDTO(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getLevel(),
                workoutEntity.getDescription(),
                workoutEntity.getImgUrl(),
                exercises,
                workoutEntity.getLikes()
        );
    }

    public void addLike(WorkoutDetailsDTO workoutDetailsDTO, UserEntity user) {
        WorkoutEntity workout = workoutRepository.findById(workoutDetailsDTO.getId()).orElseThrow(() -> new RuntimeException("Workout not found"));
        Integer oldLikes = workout.getLikes();
        Integer newLikes = oldLikes + 1;
        workout.setLikes(newLikes);
        workoutDetailsDTO.setLikes(newLikes);
        user.setHasLikedWorkout(true);
        userRepository.save(user);
        workoutRepository.save(workout);


    }

    public void unlike(WorkoutDetailsDTO workoutDetailsDTO, UserEntity user) {
        WorkoutEntity workout = workoutRepository.findById(workoutDetailsDTO.getId()).orElseThrow(() -> new RuntimeException("Workout not found"));
        Integer oldLikes = workout.getLikes();
        Integer newLikes = oldLikes - 1;
        workout.setLikes(newLikes);
        workoutDetailsDTO.setLikes(newLikes);
        user.setHasLikedWorkout(false);
        userRepository.save(user);
        workoutRepository.save(workout);

    }

}
