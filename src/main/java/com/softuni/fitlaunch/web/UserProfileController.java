package com.softuni.fitlaunch.web;

import com.softuni.fitlaunch.model.dto.view.UserProfileView;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.UserProfileEntity;
import com.softuni.fitlaunch.model.entity.WorkoutScheduleEntity;
import com.softuni.fitlaunch.service.CustomUserDetails;
import com.softuni.fitlaunch.service.UserProfileService;
import com.softuni.fitlaunch.service.UserService;
import com.softuni.fitlaunch.service.WorkoutScheduleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final WorkoutScheduleService workoutScheduleService;

    private final UserService userService;

    public UserProfileController(UserProfileService userProfileService, WorkoutScheduleService workoutScheduleService, UserService userService) {
        this.userProfileService = userProfileService;
        this.workoutScheduleService = workoutScheduleService;
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
