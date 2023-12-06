package com.softuni.fitlaunch.model.entity;


import com.softuni.fitlaunch.model.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity {


    @ManyToOne
    private UserEntity author;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LevelEnum level;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExerciseEntity> workoutExercises = new ArrayList<>();


    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @Column(nullable = false)
    private Integer likes = 0;


    @ManyToMany
    @JoinTable(
            name = "workouts_likes",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> usersLiked = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "workouts_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private List<ExerciseEntity> exercises;


    @Column(nullable = false)
    private boolean isCompleted = false;

    @Column
    private String dateCompleted;

    @Column(nullable = false)
    private boolean hasStarted = false;

    @ManyToOne
    @JoinColumn(name = "week_id")
    private WeekEntity week;

    public String getImgUrl() {
        return imgUrl;
    }

    public WorkoutEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkoutEntity setName(String name) {
        this.name = name;
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

    public List<WorkoutExerciseEntity> getWorkoutExercises() {
        return workoutExercises;
    }

    public WorkoutEntity setWorkoutExercises(List<WorkoutExerciseEntity> workoutExercise) {
        this.workoutExercises = workoutExercise;
        return this;
    }


    public List<CommentEntity> getComments() {
        return comments;
    }

    public WorkoutEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public WorkoutEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public WorkoutEntity setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public List<UserEntity> getUsersLiked() {
        return usersLiked;
    }

    public WorkoutEntity setUsersLiked(List<UserEntity> usersLiked) {
        this.usersLiked = usersLiked;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public WorkoutEntity setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public WorkoutEntity setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
        return this;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public WorkoutEntity setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
        return this;
    }

    public List<ExerciseEntity> getExercises() {
        return exercises;
    }

    public WorkoutEntity setExercises(List<ExerciseEntity> exercises) {
        this.exercises = exercises;
        return this;
    }

    public WeekEntity getWeek() {
        return week;
    }

    public WorkoutEntity setWeek(WeekEntity week) {
        this.week = week;
        return this;
    }

    //    public DayEntity getDay() {
//        return day;
//    }
//
//    public WorkoutEntity setDay(DayEntity day) {
//        this.day = day;
//        return this;
//    }
}
