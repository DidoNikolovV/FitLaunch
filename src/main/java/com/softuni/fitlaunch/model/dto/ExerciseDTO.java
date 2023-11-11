package com.softuni.fitlaunch.model.dto;

import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;

import java.util.List;

public class ExerciseDTO {

    private Long id;

    private String name;

    List<WorkoutDTO> workouts;

    public ExerciseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public ExerciseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExerciseDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<WorkoutDTO> getWorkouts() {
        return workouts;
    }

    public ExerciseDTO setWorkouts(List<WorkoutDTO> workouts) {
        this.workouts = workouts;
        return this;
    }
}
