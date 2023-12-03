package com.softuni.fitlaunch.web;

import com.softuni.fitlaunch.model.dto.view.UserProfileView;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.service.UserProfileService;
import com.softuni.fitlaunch.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserProfileController {

    private final UserProfileService userProfileService;


    private final UserService userService;

    public UserProfileController(UserProfileService userProfileService,  UserService userService) {
        this.userProfileService = userProfileService;
        this.userService = userService;
    }

    @GetMapping("/users/profile")
    public String userProfile(Principal principal, Model model) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        UserProfileView userProfileView = new UserProfileView(
                user.getUsername(),
                user.getEmail(),
                user.getMembership()
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }
}
