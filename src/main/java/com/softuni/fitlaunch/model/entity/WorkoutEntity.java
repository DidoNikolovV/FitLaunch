package com.softuni.fitlaunch.model.entity;


import com.softuni.fitlaunch.model.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity {
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LevelEnum level;

    @Column(nullable = false)
    private String description;

//    @ManyToMany(mappedBy = "workouts", fetch = FetchType.EAGER)
//    private List<ExerciseEntity> exercises;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name= "workouts_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<ExerciseEntity> exercises = new ArrayList<>();

    @OneToMany(mappedBy = "workout")
    private List<WorkoutScheduleEntity> schedules;

    public String getImgUrl() {
        return imgUrl;
    }



    public WorkoutEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public WorkoutEntity setId(Long id) {
        this.id = id;
        return this;
    }

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

    public LevelEnum getLevel() {
        return level;
    }

    public WorkoutEntity setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkoutEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<WorkoutScheduleEntity> getSchedules() {
        return schedules;
    }

    public WorkoutEntity setSchedules(List<WorkoutScheduleEntity> schedules) {
        this.schedules = schedules;
        return this;
    }
}
