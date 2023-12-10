package com.softuni.fitlaunch.model.dto.workout;

import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import jakarta.validation.constraints.NotNull;


public class WorkoutExerciseDTO {

    @NotNull
    private Long id;

    @NotNull
    private WorkoutDTO workout;
    @NotNull
    private ExerciseDTO exercise;

    @NotNull
    private int sets;

    @NotNull
    private int reps;

    @NotNull
    private String videoUrl;

    @NotNull
    private boolean isCompleted;

    public Long getId() {
        return id;
    }

    public WorkoutExerciseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public WorkoutDTO getWorkout() {
        return workout;
    }

    public WorkoutExerciseDTO setWorkout(WorkoutDTO workout) {
        this.workout = workout;
        return this;
    }

    public ExerciseDTO getExercise() {
        return exercise;
    }

    public WorkoutExerciseDTO setExercise(ExerciseDTO exercise) {
        this.exercise = exercise;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public WorkoutExerciseDTO setSets(int sets) {
        this.sets = sets;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public WorkoutExerciseDTO setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public WorkoutExerciseDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public WorkoutExerciseDTO setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }
}
