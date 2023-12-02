package com.softuni.fitlaunch.model.dto.workout;

import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.entity.WorkoutExerciseEntity;
import com.softuni.fitlaunch.model.enums.LevelEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDTO {

    private Long id;

    private String imgUrl;

    private String name;

    private LevelEnum level;
    private String description;

    private List<WorkoutExerciseDTO> workoutExercises = new ArrayList<>();

    private List<ExerciseDTO> exercises;

    private boolean isCompleted = false;

    public WorkoutDTO() {
        this.exercises = new ArrayList<>();
    }

    public WorkoutDTO(Long id, String name, String imgUrl, LevelEnum level, String description, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.level = level;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public WorkoutDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkoutDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public WorkoutDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public WorkoutDTO setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkoutDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<WorkoutExerciseDTO> getWorkoutExercises() {
        return workoutExercises;
    }

    public WorkoutDTO setWorkoutExercises(List<WorkoutExerciseDTO> workoutExercises) {
        this.workoutExercises = workoutExercises;
        return this;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public WorkoutDTO setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public WorkoutDTO setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }
}
