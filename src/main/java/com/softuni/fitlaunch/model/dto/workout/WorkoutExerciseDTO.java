package com.softuni.fitlaunch.model.dto.workout;

import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;

import java.time.LocalDate;


public class WorkoutExerciseDTO {
    private Long id;

    private WorkoutDTO workout;
    private ExerciseDTO exercise;
    private int sets;
    private int reps;


    public WorkoutExerciseDTO() {
    }

    public WorkoutExerciseDTO(Long id, WorkoutDTO workout, ExerciseDTO exercise, int sets, int reps) {
        this.id = id;
        this.workout = workout;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }

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
}
