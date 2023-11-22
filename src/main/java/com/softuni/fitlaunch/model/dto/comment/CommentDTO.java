package com.softuni.fitlaunch.model.dto.comment;

import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;

public class CommentDTO {

    private String authorName;

    private Long workoutId;

    private String content;

    public CommentDTO() {
    }

    public CommentDTO(String authorName, Long workoutId, String content) {
        this.authorName = authorName;
        this.workoutId = workoutId;
        this.content = content;
    }


    public String getAuthorName() {
        return authorName;
    }

    public CommentDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public CommentDTO setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
        return this;
    }
}
