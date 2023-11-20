package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "days")
public class DayEntity extends BaseEntity{

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<WorkoutExerciseEntity> workoutExercises = new ArrayList<>();

    @ManyToOne
    private WeekEntity week;


    public List<WorkoutExerciseEntity> getWorkoutExercises() {
        return workoutExercises;
    }

    public DayEntity setWorkoutExercises(List<WorkoutExerciseEntity> workoutExercises) {
        this.workoutExercises = workoutExercises;
        return this;
    }

    public WeekEntity getWeek() {
        return week;
    }

    public DayEntity setWeek(WeekEntity week) {
        this.week = week;
        return this;
    }
}
