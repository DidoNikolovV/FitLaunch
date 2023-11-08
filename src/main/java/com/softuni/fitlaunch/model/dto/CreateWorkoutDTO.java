package com.softuni.fitlaunch.model.dto;

import com.softuni.fitlaunch.model.enums.LevelEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class CreateWorkoutDTO {

    @NotEmpty
    private String name;

    @NotNull
    private LevelEnum level;

    @NotEmpty @Size(min = 5, max = 512)
    private String description;

    @NotEmpty
    private String imgUrl;

    @NotNull
    private List<ExerciseDTO> exercises;

    public CreateWorkoutDTO() {
        this.name = null;
        this.level = null;
        this.description = null;
        this.imgUrl = null;
        this.exercises = new ArrayList<>();
    }

    public CreateWorkoutDTO(String name, LevelEnum level, String description, String imgUrl, List<ExerciseDTO> exercises) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.imgUrl = imgUrl;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public CreateWorkoutDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public CreateWorkoutDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateWorkoutDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public CreateWorkoutDTO setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public CreateWorkoutDTO setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
        return this;
    }

}
