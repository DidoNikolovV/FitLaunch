package com.softuni.fitlaunch.model.dto.comment;

public class CommentMessageDTO {
    private String message;

    public CommentMessageDTO() {
    }

    public CommentMessageDTO(String message) {
        this.message = message;
    }

    public String getContent() {
        return message;
    }

    public CommentMessageDTO setContent(String message) {
        this.message = message;
        return this;
    }
}
