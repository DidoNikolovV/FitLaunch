package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.workout.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutExerciseDTO;
import com.softuni.fitlaunch.model.entity.ExerciseEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.model.entity.WorkoutExerciseEntity;
import com.softuni.fitlaunch.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final WorkoutScheduleService workoutScheduleService;

    private final WorkoutExerciseService workoutExerciseService;

    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService, WorkoutScheduleService workoutScheduleService, WorkoutExerciseService workoutExerciseService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.workoutScheduleService = workoutScheduleService;
        this.workoutExerciseService = workoutExerciseService;
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
    public String add(@ModelAttribute CreateWorkoutDTO createWorkoutDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createWorkoutDTO", createWorkoutDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createWorkoutDTO", createWorkoutDTO);
            return "redirect:/workout/add";
        }


        WorkoutEntity workout = new WorkoutEntity();
        workout
                .setName(createWorkoutDTO.getName())
                .setLevel(createWorkoutDTO.getLevel())
                .setDescription(createWorkoutDTO.getDescription());

        workoutService.createWorkout(workout, createWorkoutDTO);

        List<Integer> sets = createWorkoutDTO.getSets();
        List<Integer> reps = createWorkoutDTO.getReps();


        List<Long> selectedExercisesIds = createWorkoutDTO.getSelectedExerciseIds();
        List<ExerciseEntity> selectedExercises = exerciseService.getExercisesByIds(selectedExercisesIds);

        for (ExerciseEntity selectedExercise : selectedExercises) {
            int selectedExerciseId = Integer.parseInt(String.valueOf(selectedExercisesIds.get(selectedExercisesIds.indexOf(selectedExercise.getId()))));

            Integer selectedExerciseSets = sets.get(selectedExerciseId - 1);
            Integer selectedExerciseReps = reps.get(selectedExerciseId - 1);

            WorkoutExerciseEntity exercise = new WorkoutExerciseEntity()
                    .setWorkout(workout)
                    .setExercise(selectedExercise)
                    .setSets(selectedExerciseSets)
                    .setReps(selectedExerciseReps)
                            .setVideoUrl(selectedExercise.getVideoUrl());

            workoutExerciseService.saveWorkoutExercise(exercise);
        }

        long newWorkoutID =  workout.getId();

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

        WorkoutDetailsDTO workout = workoutService.getWorkoutDetails(id).orElse(null);
//                .orElseThrow(() -> new ObjectNotFoundException("Workout with id " + id + " not found!" ));
        List<WorkoutExerciseEntity> allWorkoutExercises = workoutExerciseService.getAllWorkoutExercisesByWorkoutId(workout.getId());


        model.addAttribute("workout", workout);
        model.addAttribute("allWorkoutExercises", allWorkoutExercises);

        return "details";
    }

}
