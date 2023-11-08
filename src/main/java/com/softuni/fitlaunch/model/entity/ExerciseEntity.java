package com.softuni.fitlaunch.model.entity;

import com.softuni.fitlaunch.model.enums.MusclesEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity{

    private Long id;



    @Override
    public ExerciseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    @Column(nullable = false, unique = true)
    private String name;


    @ManyToOne
    private WorkoutEntity workout;

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ExerciseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public ExerciseEntity setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }
}
