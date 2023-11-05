package com.softuni.fitlaunch.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkoutController {

    @GetMapping("/workouts")
    public String allWorkouts() {
        return "workouts";
    }
}
