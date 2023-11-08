package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {


    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if(!model.containsAttribute("createWorkoutDTO")) {
            model.addAttribute("createWorkoutDTO", new CreateWorkoutDTO());
        }

        List<ExerciseDTO> allExercises = workoutService.getAllExercises();

        model.addAttribute("exercises", allExercises);

        return "workout-add";
    }

    @PostMapping("/add")
    public String add(@Valid CreateWorkoutDTO createWorkoutDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createWorkoutDTO", createWorkoutDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createWorkoutDTO", createWorkoutDTO);
            return "redirect:/workout/add";
        }

        long newWorkoutID = workoutService.createWorkout(createWorkoutDTO);

        return "redirect:/workout/" + newWorkoutID;
    }

    @GetMapping("/{id}")
    public String details(@PathVariable("id") Long id) {
        return "details";
    }

}
