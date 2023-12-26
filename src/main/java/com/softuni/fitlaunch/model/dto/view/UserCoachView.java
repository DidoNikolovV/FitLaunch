package com.softuni.fitlaunch.model.dto.view;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCoachView {

    private Long id;
    private String imgUrl;
    private String username;
    private String email;
    private String description;

}
