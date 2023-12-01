package com.softuni.fitlaunch.model.dto.workout;

import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.entity.WorkoutExerciseEntity;
import com.softuni.fitlaunch.model.enums.LevelEnum;

import java.util.List;

public class WorkoutDetailsDTO {

    private Long id;

    private String name;

    private LevelEnum level;

    private String description;

    private String imgUrl;

    private List<WorkoutExerciseEntity> exercises;

    private Integer likes;

    private List<UserDTO> usersLiked;

    private boolean hasStarted;

    private boolean isCompleted;



    public WorkoutDetailsDTO(Long id, String name, LevelEnum level, String description, String imgUrl, List<WorkoutExerciseEntity> exercises, Integer likes, List<UserDTO> usersLiked, boolean hasStarted, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.description = description;
        this.imgUrl = imgUrl;
        this.exercises = exercises;
        this.likes = likes;
        this.usersLiked = usersLiked;
        this.hasStarted = hasStarted;
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public WorkoutDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkoutDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public WorkoutDetailsDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkoutDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public WorkoutDetailsDTO setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public List<WorkoutExerciseEntity> getExercises() {
        return exercises;
    }

    public WorkoutDetailsDTO setExercises(List<WorkoutExerciseEntity> exercises) {
        this.exercises = exercises;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public WorkoutDetailsDTO setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public List<UserDTO> getUsersLiked() {
        return usersLiked;
    }

    public WorkoutDetailsDTO setUsersLiked(List<UserDTO> usersLiked) {
        this.usersLiked = usersLiked;
        return this;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public WorkoutDetailsDTO setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public WorkoutDetailsDTO setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }
}
