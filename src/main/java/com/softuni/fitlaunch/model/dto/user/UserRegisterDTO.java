package com.softuni.fitlaunch.model.dto.user;

import com.softuni.fitlaunch.model.enums.UserTitleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long!")
    private String username;

    @Email(message = "Invalid email!")
    @NotBlank(message = "Invalid email!")
    private String email;

    @NotNull
    @Size(min = 5, max = 10, message = "Password must be between 5 and 10 characters long!")
    private String password;

    @NotNull
    @Size(min = 5, max = 10, message = "Confirm password must be between 5 and 10 characters long!")
    private String confirmPassword;

    private String title;

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UserRegisterDTO setTitle(String title) {
        this.title = title;
        return this;
    }
}
