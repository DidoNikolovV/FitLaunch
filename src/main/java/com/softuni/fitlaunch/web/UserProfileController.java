package com.softuni.fitlaunch.web;

import com.softuni.fitlaunch.model.entity.UserProfileEntity;
import com.softuni.fitlaunch.model.entity.WorkoutScheduleEntity;
import com.softuni.fitlaunch.service.CustomUserDetails;
import com.softuni.fitlaunch.service.UserProfileService;
import com.softuni.fitlaunch.service.WorkoutScheduleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final WorkoutScheduleService workoutScheduleService;

    public UserProfileController(UserProfileService userProfileService, WorkoutScheduleService workoutScheduleService) {
        this.userProfileService = userProfileService;
        this.workoutScheduleService = workoutScheduleService;
    }

    @GetMapping("/users/profile")
    public String userProfile(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
            UserProfileEntity userProfile = userProfileService.getUserProfileById(userId);
            List<WorkoutScheduleEntity> scheduledWorkouts = workoutScheduleService.getScheduledWorkouts();
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("scheduledWorkouts", scheduledWorkouts);
        } else {
            return "redirect:/users/login";
        }
        return "profile";
    }
}
