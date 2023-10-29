package com.softuni.fitlaunch.model.dto;

import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.model.enums.MusclesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.util.List;

public class ExerciseDTO {
    private String name;
    private int reps;

    private WorkoutEntity workout;

    private List<MusclesEnum> targetMuscle;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    public String getName() {
        return name;
    }

    public ExerciseDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public ExerciseDTO setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public ExerciseDTO setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }

    public List<MusclesEnum> getTargetMuscle() {
        return targetMuscle;
    }

    public ExerciseDTO setTargetMuscle(List<MusclesEnum> targetMuscle) {
        this.targetMuscle = targetMuscle;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ExerciseDTO setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }
}
