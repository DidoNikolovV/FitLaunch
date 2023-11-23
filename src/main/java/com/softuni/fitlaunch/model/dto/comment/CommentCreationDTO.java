package com.softuni.fitlaunch.model.dto.comment;

public class CommentCreationDTO {

    private String authorName;

    private Long workoutId;

    private String message;

    public CommentCreationDTO() {
    }

    public CommentCreationDTO(String authorName, String message) {
        this.authorName = authorName;
        this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }

    public CommentCreationDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentCreationDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public CommentCreationDTO setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
        return this;
    }
}
