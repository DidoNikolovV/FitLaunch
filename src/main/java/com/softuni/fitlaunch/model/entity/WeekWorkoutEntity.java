package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "week_workouts")
public class WeekWorkoutEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "week_id")
    private WeekEntity week;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutEntity workout;

    public WeekEntity getWeek() {
        return week;
    }

    public WeekWorkoutEntity setWeek(WeekEntity week) {
        this.week = week;
        return this;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public WeekWorkoutEntity setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }
}
