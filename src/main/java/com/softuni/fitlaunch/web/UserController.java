package com.softuni.fitlaunch.web;

import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.dto.user.UserRegisterDTO;
import com.softuni.fitlaunch.model.dto.view.UserCoachDetailsView;
import com.softuni.fitlaunch.model.dto.view.UserCoachView;
import com.softuni.fitlaunch.model.dto.view.UserProfileView;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.UserRoleEntity;
import com.softuni.fitlaunch.model.enums.UserRoleEnum;
import com.softuni.fitlaunch.service.BlackListService;
import com.softuni.fitlaunch.service.CustomUserDetails;
import com.softuni.fitlaunch.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.security.Principal;

@Controller()
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final BlackListService blackListService;

    public UserController(UserService userService, BlackListService blackListService) {
        this.userService = userService;
        this.blackListService = blackListService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String userProfile(Principal principal, Model model) {
        UserDTO user = userService.getUserByUsername(principal.getName());
        UserProfileView userProfileView = new UserProfileView(
                user.getUsername(),
                user.getEmail(),
                user.getMembership()
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }

    @PostMapping("/login-error")
    public String onFailure(@ModelAttribute("username") String username,
                            Model model) {

        UserDTO user = userService.getUserByUsername(username);

        if(user != null) {
            if(user.isActivated()) {
                model.addAttribute("username", username);
                model.addAttribute("bad_credentials", "true");
            } else {
                model.addAttribute("user_not_active", "true");

            }
        } else {
            model.addAttribute("username", username);
            model.addAttribute("bad_credentials", "true");
        }

        return "login";
    }


    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO) {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
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

        return new ModelAndView("redirect:/users/login");
    }

    @GetMapping("/all")
    public String allUsers(HttpServletRequest request, Model model) {
        String ipAddress = request.getRemoteAddr();
        request.getSession().setAttribute("userIpAddress", ipAddress);

        List<UserDTO> allUsers = userService.getAllUsers();

        model.addAttribute("users", allUsers);

        return "users";
    }

    @PostMapping("/all")
    public String allUsers(@RequestParam("username") String username, @RequestParam("role") String role) {
        Long roleId = role.equals("ADMIN") ? 1L : 2L;

        UserRoleEntity newRole = new UserRoleEntity()
                .setId(roleId)
                .setRole(UserRoleEnum.valueOf(role));
        userService.changeUserRole(username, newRole);

        return "redirect:/users/all";
    }

    @GetMapping("/coaches/all")
    public String allCoaches(Model model) {
        List<UserCoachView> allCoaches = userService.getAllCoaches();

        model.addAttribute("allCoaches", allCoaches);

        return "coaches";
    }

    @GetMapping("/coaches/{id}")
    public String coachDetails(@PathVariable("id") Long id, Model model) {
        UserCoachDetailsView coachDetailsView = userService.getCoachById(id);

        model.addAttribute("coachDetails", coachDetailsView);

        return "coach-details";
    }

    @PostMapping("/ban")
    @Secured("ROLE_ADMIN")
    public String banUser(@RequestParam("ipAddress") String ipAddress) {
        blackListService.banUser(ipAddress);

        return "redirect:/users/all";
    }


    @GetMapping("/contact-us")
    public String contactUs() {
        return "contact-us";
    }

    @GetMapping("/upgrade")
    public String membershipPlans() {
        return "upgrade";
    }

    @PostMapping("/upgrade/{membership}")
    public String membershipPlans(@PathVariable("membership") String membership, Principal principal) {
        UserDTO loggedUser = userService.getUserByUsername(principal.getName());

        userService.changeMembership(loggedUser, membership);

        return "upgrade";
    }


    @GetMapping("/activate/{activationCode}")
    public String activateAccount(@PathVariable("activationCode") String activationCode, Model model) {

        System.out.println("Received activation code: " + activationCode);

        if(activationCode == null || activationCode.isEmpty()) {
            model.addAttribute("activationError", "Invalid activation code");
            return "email/activation-failed";
        }

        boolean activationSuccess = userService.activateUser(activationCode);
        if(activationSuccess) {
            return "email/activation-success";
        } else {
            return "email/activation-failed";
        }

    }

    @GetMapping("/progress")
    public String progress(Principal principal, Model model) {
        UserDTO loggedUser = userService.getUserByUsername(principal.getName());

        List<ProgramWeekWorkoutDTO> workoutsCompleted = loggedUser.getWorkoutsCompleted();

        model.addAttribute("workoutsCompleted", workoutsCompleted);
        return "workout-history";
    }

}
