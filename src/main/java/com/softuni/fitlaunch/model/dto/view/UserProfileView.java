package com.softuni.fitlaunch.model.dto.view;

public class UserProfileView {

    private String username;
    private String email;
    private String membership;

    public UserProfileView(String username, String email, String membership) {
        this.username = username;
        this.email = email;
        this.membership = membership;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMembership() {
        return membership;
    }

    public UserProfileView setMembership(String membership) {
        this.membership = membership;
        return this;
    }
}
