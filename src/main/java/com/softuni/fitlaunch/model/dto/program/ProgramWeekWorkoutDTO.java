package com.softuni.fitlaunch.model.dto.program;

import com.softuni.fitlaunch.model.dto.comment.CommentCreationDTO;
import com.softuni.fitlaunch.model.enums.LevelEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public class ProgramWeekWorkoutDTO {

    @NotNull
    private Long id;


    @NotNull
    private String name;

    @NotNull
    private ProgramWeekDTO programWeek;

    @NotNull
    private boolean hasStarted;

    @NotNull
    private boolean isCompleted;

    private List<CommentCreationDTO> comments;

    private List<ProgramWorkoutExerciseDTO> exercises;

    private Long likes = 0L;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LevelEnum level;

    @NotNull
    private String description;

    public Long getId() {
        return id;
    }

    public ProgramWeekWorkoutDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProgramWeekDTO getProgramWeek() {
        return programWeek;
    }

    public ProgramWeekWorkoutDTO setProgramWeek(ProgramWeekDTO programWeek) {
        this.programWeek = programWeek;
        return this;
    }


    public boolean hasStarted() {
        return hasStarted;
    }

    public ProgramWeekWorkoutDTO setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ProgramWeekWorkoutDTO setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public List<CommentCreationDTO> getComments() {
        return comments;
    }

    public ProgramWeekWorkoutDTO setComments(List<CommentCreationDTO> comments) {
        this.comments = comments;
        return this;
    }


    public Long getLikes() {
        return likes;
    }

    public ProgramWeekWorkoutDTO setLikes(Long likes) {
        this.likes = likes;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public ProgramWeekWorkoutDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProgramWeekWorkoutDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public List<ProgramWorkoutExerciseDTO> getExercises() {
        return exercises;
    }

    public ProgramWeekWorkoutDTO setExercises(List<ProgramWorkoutExerciseDTO> exercises) {
        this.exercises = exercises;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProgramWeekWorkoutDTO setName(String name) {
        this.name = name;
        return this;
    }
}
