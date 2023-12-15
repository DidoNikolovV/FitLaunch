package com.softuni.fitlaunch.model.dto.comment;

public class CommentCreationDTO {

    private String authorName;

    private Long programId;

    private Long weekId;

    private Long workoutId;

    private String message;

    public CommentCreationDTO() {
    }

    public CommentCreationDTO(String authorName, Long programId, Long weekId, Long workoutId, String message) {
        this.authorName = authorName;
        this.programId = programId;
        this.weekId = weekId;
        this.workoutId = workoutId;
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

    public Long getProgramId() {
        return programId;
    }

    public CommentCreationDTO setProgramId(Long programId) {
        this.programId = programId;
        return this;
    }

    public Long getWeekId() {
        return weekId;
    }

    public CommentCreationDTO setWeekId(Long weekId) {
        this.weekId = weekId;
        return this;
    }
}
