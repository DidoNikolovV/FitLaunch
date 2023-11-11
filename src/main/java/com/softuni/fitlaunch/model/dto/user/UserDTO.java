package com.softuni.fitlaunch.model.dto.user;

import com.softuni.fitlaunch.model.entity.UserRoleEntity;

import java.util.List;

public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private List<UserRoleEntity> roles;


    public UserDTO(Long id, String username, String email, List<UserRoleEntity> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

}
