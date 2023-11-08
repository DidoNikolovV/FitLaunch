package com.softuni.fitlaunch.model.dto;

import com.softuni.fitlaunch.model.enums.LevelEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WorkoutDetailsDTO {

    private Long id;

    private String name;

    private LevelEnum level;

    private String description;

    private String imgUrl;

    private List<ExerciseDTO> exercises;

    public WorkoutDetailsDTO(Long id, String name, LevelEnum level, String description, String imgUrl, List<ExerciseDTO> exercises) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.description = description;
        this.imgUrl = imgUrl;
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public WorkoutDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkoutDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public WorkoutDetailsDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkoutDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public WorkoutDetailsDTO setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public WorkoutDetailsDTO setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
        return this;
    }
}
