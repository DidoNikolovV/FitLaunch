package com.softuni.fitlaunch.model.dto.view;

public class CommentView {

    private Long id;
    private String authorName;
    private String message;

    public CommentView(Long id, String authorName, String message) {
        this.id = id;
        this.authorName = authorName;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public CommentView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public CommentView setAuthorName(String authorName) {
        this.authorName = authorName;
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
