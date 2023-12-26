package com.softuni.fitlaunch.model.dto.user;

import com.softuni.fitlaunch.model.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    private Long id;

    private UserRoleEnum role;


    public Long getId() {
        return id;
    }

    public UserRoleDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleDTO setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
