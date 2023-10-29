package com.softuni.fitlaunch.model.dto;

import com.softuni.fitlaunch.model.enums.LevelEnum;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDTO {

    private LevelEnum level;
    private String name;
    private List<ExerciseDTO> exercises;

    public WorkoutDTO() {
        this.exercises = new ArrayList<>();
    }

    public WorkoutDTO(LevelEnum level, String name, List<ExerciseDTO> exercises) {
        this.level = level;
        this.name = name;
        this.exercises = exercises;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public WorkoutDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkoutDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public WorkoutDTO setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
        return this;
    }
}
