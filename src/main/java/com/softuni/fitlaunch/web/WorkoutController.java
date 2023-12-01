package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.dto.workout.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.entity.ExerciseEntity;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.model.entity.WorkoutExerciseEntity;
import com.softuni.fitlaunch.service.*;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class WorkoutController {


    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    private final UserService userService;

    private final WorkoutScheduleService workoutScheduleService;

    private final WorkoutExerciseService workoutExerciseService;

    private final ModelMapper modelMapper;

    private final FileUpload fileUpload;

    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService, UserService userService, WorkoutScheduleService workoutScheduleService, WorkoutExerciseService workoutExerciseService, ModelMapper modelMapper, FileUpload fileUpload) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.workoutScheduleService = workoutScheduleService;
        this.workoutExerciseService = workoutExerciseService;
        this.modelMapper = modelMapper;
        this.fileUpload = fileUpload;
    }

    @GetMapping("/workouts/add")
    public String add(Model model) {

        if(!model.containsAttribute("createWorkoutDTO")) {
            model.addAttribute("createWorkoutDTO", new CreateWorkoutDTO());
        }

        List<ExerciseDTO> allExercises = workoutService.getAllExercises();

        model.addAttribute("exercises", allExercises);

        return "workout-add";
    }

    @PostMapping("/workouts/add")
    public String add(@ModelAttribute CreateWorkoutDTO createWorkoutDTO, Principal principal,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt) throws IOException {

        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createWorkoutDTO", createWorkoutDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createWorkoutDTO", createWorkoutDTO);
            return "redirect:/workout/add";
        }


        String imageUrl = fileUpload.uploadFile(createWorkoutDTO.getImgUrl());
        WorkoutEntity workout = new WorkoutEntity();
        workout
                .setAuthor(userService.getUserByUsername(principal.getName()))
                .setName(createWorkoutDTO.getName())
                .setLevel(createWorkoutDTO.getLevel())
                .setDescription(createWorkoutDTO.getDescription())
                .setImgUrl(imageUrl);


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


    @PostMapping("/workouts/schedule")
    public String scheduleWorkout(
            @RequestParam Long workoutId,
            @RequestParam String scheduleTime) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();

        workoutScheduleService.scheduleWorkout(userId, workoutId, scheduleTime);

        return "redirect:/";
    }


    @GetMapping("/workouts/{id}")
    public String details(@PathVariable("id") Long id, Model model, Principal principal) {

        UserEntity currentLoggedUser = userService.getUserByUsername(principal.getName());

        WorkoutDetailsDTO workout = workoutService.getWorkoutDetails(id).orElseThrow(() -> new ObjectNotFoundException("Workout with id " + id + " not found!" ));;
        List<WorkoutExerciseEntity> allWorkoutExercises = workoutExerciseService.getAllWorkoutExercisesByWorkoutId(workout.getId());

        boolean hasLiked = false;
        boolean isCompleted = false;
        boolean hasStarted = false;

        for (UserDTO userDTO : workout.getWorkoutsCompleted()) {
            if(userDTO.getId().equals(currentLoggedUser.getId())) {
                isCompleted = true;
                break;
            }
        }

        for (UserDTO userDTO : workout.getUsersLiked()) {
            if(userDTO.getUsername().equals(principal.getName())) {
                hasLiked = true;
                break;
            }
        }

        for (UserDTO userDTO : workout.getWorkoutsStarted()) {
            if(userDTO.getId().equals(currentLoggedUser.getId())) {
                hasStarted = true;
                break;
            }
        }

        model.addAttribute("workout", workout);
        model.addAttribute("allWorkoutExercises", allWorkoutExercises);
        model.addAttribute("hasLiked", hasLiked);
        model.addAttribute("hasStarted", hasStarted);
        model.addAttribute("isCompleted", isCompleted);

        return "workout-details";
    }


    @PostMapping("/workouts/{id}")
    public String details(@PathVariable("id") Long id,
                          Principal principal) {

        String currentUserUsername = principal.getName();
        WorkoutDetailsDTO workoutDetails = workoutService.getWorkoutDetails(id).orElseThrow(() -> new RuntimeException("Workout not found"));

        boolean hasLiked = false;

        for (UserDTO userDTO : workoutDetails.getUsersLiked()) {
            if(userDTO.getUsername().equals(currentUserUsername)) {
                hasLiked = true;
                break;
            }
        }

        if(hasLiked) {
            workoutService.dislike(currentUserUsername, workoutDetails.getId());
        } else {
            workoutService.like(currentUserUsername, workoutDetails.getId());
        }



        return "redirect:/workouts/" + id;
    }

    @PostMapping("/workouts/start/{id}")
    public String workoutStart(@PathVariable("id") Long id, Principal principal) {

        workoutService.startWorkout(id, principal.getName());

        return "redirect:/workouts/" + id;
    }

    @PostMapping("/workouts/complete/{id}")
    public String workoutComplete(@PathVariable("id") Long id, Principal principal) {

        WorkoutDetailsDTO workoutDetails = workoutService.getWorkoutDetails(id).orElseThrow(() -> new RuntimeException("Workout not found"));
        String currentUserUsername = principal.getName();

        workoutService.completeWorkout(workoutDetails.getId(), currentUserUsername);

        return "redirect:/workouts/" + id;
    }

    @PostMapping("/workouts/{workoutId}/complete/{exerciseId}")
    public String exerciseComplete(@PathVariable("workoutId") Long workoutId, @PathVariable("exerciseId") Long exerciseId) {
        workoutService.completeExercise(workoutId, exerciseId);

        return "redirect:/workouts/" + workoutId;
    }

}
