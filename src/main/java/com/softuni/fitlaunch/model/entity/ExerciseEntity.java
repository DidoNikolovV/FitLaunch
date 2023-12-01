package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "exercises")
public class ExerciseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer sets;


    @Column(nullable = false)
    private Integer reps;

    @Column(name = "video_url")
    private String videoUrl;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExerciseEntity> workoutExercise = new ArrayList<>();




    public Long getId() {
        return id;
    }

    public ExerciseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExerciseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSets() {
        return sets;
    }

    public ExerciseEntity setSets(Integer sets) {
        this.sets = sets;
        return this;
    }

    public Integer getReps() {
        return reps;
    }

    public ExerciseEntity setReps(Integer reps) {
        this.reps = reps;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public ExerciseEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public List<WorkoutExerciseEntity> getWorkoutExercise() {
        return workoutExercise;
    }

    public ExerciseEntity setWorkoutExercise(List<WorkoutExerciseEntity> workoutExercise) {
        this.workoutExercise = workoutExercise;
        return this;
    }


}
