package com.softuni.fitlaunch.model.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDetailsDTO {
    private Double weight;
    private Double height;
    private String targetGoals;
    private String dietaryPreferences;
}
