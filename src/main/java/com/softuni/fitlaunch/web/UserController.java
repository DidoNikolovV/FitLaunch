package com.softuni.fitlaunch.web;

import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.dto.user.UserRegisterDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.UserRoleEntity;
import com.softuni.fitlaunch.model.enums.UserRoleEnum;
import com.softuni.fitlaunch.service.CustomUserDetails;
import com.softuni.fitlaunch.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
            UserEntity user = userService.getUserById(userId);
            model.addAttribute("user", user);
        }
        return "profile";
    }

    @PostMapping("/users/login-error")
    public String onFailure(@ModelAttribute("email") String email,
                            Model model) {

        model.addAttribute("email", email);
        model.addAttribute("bad_credentials", "true");

        return "login";
    }


    @GetMapping("/users/register")
    public ModelAndView register(@ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO) {
        return new ModelAndView("register");
    }

    @PostMapping("/users/register")
    public ModelAndView register(@ModelAttribute("userRegisterDTO") @Valid UserRegisterDTO userRegisterDTO,
                                 BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }


        boolean hasSuccessfullyRegistered = userService.register(userRegisterDTO);
        if(!hasSuccessfullyRegistered) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("hasRegisterError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/users/all")
    public String allUsers(Model model) {

        List<UserEntity> allUsers = userService.getAllUsers();

        model.addAttribute("users", allUsers);

        return "users";
    }

    @PostMapping("/users/all")
    public String allUsers(@RequestParam("userId") Long userId, @RequestParam("role") String role) {
        Long roleId = role.equals("ADMIN") ? 1L : 2L;

        UserRoleEntity newRole = new UserRoleEntity()
                .setId(roleId)
                .setRole(UserRoleEnum.valueOf(role));
        userService.changeUserRole(userId, newRole);

        return "redirect:/users/all";
    }

    @GetMapping("/workouts/history")
    public String workoutHistory(Model model, Principal principal) {


        List<WorkoutDTO> workoutsCompleted = userService.getCompletedWorkouts(principal.getName());



        model.addAttribute("workoutsCompleted", workoutsCompleted);

        return "workout-history";
    }

    @GetMapping("/upgrade")
    public String membershipPlans() {
        return "upgrade";
    }


}
