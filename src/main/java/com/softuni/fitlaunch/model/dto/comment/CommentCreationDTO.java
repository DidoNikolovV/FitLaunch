package com.softuni.fitlaunch.model.dto.comment;

public class CommentCreationDTO {

    private String authorName;

    private Long workoutId;

    private String content;

    public CommentCreationDTO() {
    }

    public CommentCreationDTO(String authorName, String message) {
        this.authorName = authorName;
        this.content = message;
    }

    public String getAuthorName() {
        return authorName;
    }

    public CommentCreationDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentCreationDTO setContent(String content) {
        this.content = content;
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
