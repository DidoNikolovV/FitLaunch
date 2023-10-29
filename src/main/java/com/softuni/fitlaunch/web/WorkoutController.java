package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.enums.LevelEnum;
import com.softuni.fitlaunch.service.LoggedUser;
import com.softuni.fitlaunch.service.WorkoutService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final LoggedUser loggedUser;

    public WorkoutController(WorkoutService workoutService, LoggedUser loggedUser) {
        this.workoutService = workoutService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("")
    public ModelAndView allWorkouts() {
        return new ModelAndView("workout");
    }

    @GetMapping("/beginner")
    public ModelAndView viewBeginnerWorkout() {
        return new ModelAndView("beginner-workout"); // Redirect to the specific workout page
    }

    @GetMapping("/intermediate")
    public ModelAndView viewIntermediateWorkout() {
        return new ModelAndView("redirect:/workouts/intermediate-workout"); // Redirect to the specific workout page
    }

    @GetMapping("/advanced")
    public ModelAndView viewAdvancedWorkout() {
        return new ModelAndView("redirect:/workouts/advanced-workout"); // Redirect to the specific workout page
    }
}
