package com.softuni.fitlaunch.model.dto.program;

import com.softuni.fitlaunch.model.dto.ExerciseDTO;

public class ProgramWorkoutExerciseDTO {

    private Long id;

    private ProgramWeekWorkoutDTO workout;

    private ExerciseDTO exercise;

    private int sets;

    private int reps;

    private boolean isCompleted;


    public Long getId() {
        return id;
    }

    public ProgramWorkoutExerciseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProgramWeekWorkoutDTO getWorkout() {
        return workout;
    }

    public ProgramWorkoutExerciseDTO setWorkout(ProgramWeekWorkoutDTO workout) {
        this.workout = workout;
        return this;
    }

    public ExerciseDTO getExercise() {
        return exercise;
    }

    public ProgramWorkoutExerciseDTO setExercise(ExerciseDTO exercise) {
        this.exercise = exercise;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public ProgramWorkoutExerciseDTO setSets(int sets) {
        this.sets = sets;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public ProgramWorkoutExerciseDTO setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ProgramWorkoutExerciseDTO setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }
}
