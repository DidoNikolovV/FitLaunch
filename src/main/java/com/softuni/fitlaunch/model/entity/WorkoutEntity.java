package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "workout")
    private List<ExerciseEntity> exercises;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    public String getName() {
        return name;
    }

    public WorkoutEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ExerciseEntity> getExercises() {
        return exercises;
    }

    public WorkoutEntity setExercises(List<ExerciseEntity> exercises) {
        this.exercises = exercises;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public WorkoutEntity setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }
}
