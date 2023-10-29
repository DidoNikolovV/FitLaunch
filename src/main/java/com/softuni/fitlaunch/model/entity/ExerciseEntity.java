package com.softuni.fitlaunch.model.entity;

import com.softuni.fitlaunch.model.enums.MusclesEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int reps;

    @ManyToOne
    private WorkoutEntity workout;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_muscles", nullable = false)
    private List<MusclesEnum> targetMuscle;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public ExerciseEntity setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExerciseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public ExerciseEntity setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public List<MusclesEnum> getTargetMuscle() {
        return targetMuscle;
    }

    public ExerciseEntity setTargetMuscle(List<MusclesEnum> targetMuscle) {
        this.targetMuscle = targetMuscle;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ExerciseEntity setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }
}
