package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "weeks")
public class WeekEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "program_id")
    private ProgramEntity program;

    @ManyToMany
    @JoinTable(
            name = "week_workouts",
            joinColumns =  @JoinColumn(name = "week_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private List<WorkoutEntity> workouts = new ArrayList<>();


    public ProgramEntity getProgram() {
        return program;
    }

    public WeekEntity setProgram(ProgramEntity program) {
        this.program = program;
        return this;
    }

    public void addWorkout(WorkoutEntity workout) {
        this.workouts.add(workout);
        workout.setWeek(this);
    }

    public List<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public WeekEntity setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
        return this;
    }

}
