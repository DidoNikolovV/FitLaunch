package com.softuni.fitlaunch.model.entity;

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

}
