package com.softuni.fitlaunch.model.dto.view;

public class CommentView {

    private Long id;
    private String authorUsername;
    private String message;


    public CommentView(Long id, String authorUsername, String message) {
        this.id = id;
        this.authorUsername = authorUsername;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public CommentView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public CommentView setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentView setMessage(String message) {
        this.message = message;
        return this;
    }
}
