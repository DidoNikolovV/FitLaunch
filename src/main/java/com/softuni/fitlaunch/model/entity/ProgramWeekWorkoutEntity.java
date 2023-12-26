package com.softuni.fitlaunch.model.entity;


import com.softuni.fitlaunch.model.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "programs_weeks_workouts")
public class ProgramWeekWorkoutEntity extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "program_week_id")
    private ProgramWeekEntity programWeek;

    @Column(name = "has_started")
    private boolean hasStarted;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<ProgramWorkoutExerciseEntity> exercises;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private Long likes;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LevelEnum level;

    @Column(nullable = false)
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ProgramWeekWorkoutEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ProgramWeekEntity getProgramWeek() {
        return programWeek;
    }

    public ProgramWeekWorkoutEntity setProgramWeek(ProgramWeekEntity programWeek) {
        this.programWeek = programWeek;
        return this;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public ProgramWeekWorkoutEntity setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ProgramWeekWorkoutEntity setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public ProgramWeekWorkoutEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public Long getLikes() {
        return likes;
    }

    public ProgramWeekWorkoutEntity setLikes(Long likes) {
        this.likes = likes;
        return this;
    }

    public List<ProgramWorkoutExerciseEntity> getExercises() {
        return exercises;
    }

    public ProgramWeekWorkoutEntity setExercises(List<ProgramWorkoutExerciseEntity> exercises) {
        this.exercises = exercises;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProgramWeekWorkoutEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public ProgramWeekWorkoutEntity setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProgramWeekWorkoutEntity setDescription(String description) {
        this.description = description;
        return this;
    }




}
