package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.workout.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.service.CustomUserDetails;
import com.softuni.fitlaunch.service.ExerciseService;
import com.softuni.fitlaunch.service.WorkoutScheduleService;
import com.softuni.fitlaunch.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {


    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    private final WorkoutScheduleService workoutScheduleService;

    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService, WorkoutScheduleService workoutScheduleService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.workoutScheduleService = workoutScheduleService;
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


    @PostMapping("/schedule")
    public String scheduleWorkout(
            @RequestParam Long workoutId,
            @RequestParam String scheduleTime) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();

        workoutScheduleService.scheduleWorkout(userId, workoutId, scheduleTime);

        return "redirect:/";
    }


    @GetMapping("/{id}")
    public String details(@PathVariable("id") Long id, Model model) {

        WorkoutDetailsDTO workoutDetailsDTO = workoutService.getWorkoutDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("Workout with id " + id + " not found!" ));

        model.addAttribute("workout", workoutDetailsDTO);

        return "details";
    }

}
