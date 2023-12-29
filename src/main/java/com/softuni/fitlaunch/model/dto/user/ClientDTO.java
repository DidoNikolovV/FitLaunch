package com.softuni.fitlaunch.model.dto.user;

import com.softuni.fitlaunch.model.entity.CoachEntity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDTO {

    private Long id;
    private String username;

    @Email
    private String email;

    private String imgUrl;

    private Double weight;
    private Double height;
    private String targetGoals;

    private String dietaryPreferences;

    private CoachDTO coach;
}