package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.WorkoutDetailsDTO;
import com.softuni.fitlaunch.service.ExerciseService;
import com.softuni.fitlaunch.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {


    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
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
    public String add(@ModelAttribute @Valid CreateWorkoutDTO createWorkoutDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt) {

        List<Long> selectedExercisesIds = createWorkoutDTO.getSelectedExerciseIds();
        List<ExerciseDTO> selectedExercises = exerciseService.getExercisesByIds(selectedExercisesIds);

        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createWorkoutDTO", createWorkoutDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createWorkoutDTO", createWorkoutDTO);
            return "redirect:/workout/add";
        }



        createWorkoutDTO.setExercises(selectedExercises);

        long newWorkoutID = workoutService.createWorkout(createWorkoutDTO);

        return "redirect:/workouts/" + newWorkoutID;
    }


    @GetMapping("/{id}")
    public String details(@PathVariable("id") Long id, Model model) {

        WorkoutDetailsDTO workoutDetailsDTO = workoutService.getWorkoutDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("Workout with id " + id + " not found!" ));

        model.addAttribute("workout", workoutDetailsDTO);

        return "details";
    }

}
