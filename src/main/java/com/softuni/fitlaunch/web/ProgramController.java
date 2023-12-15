package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.program.ProgramWeekDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.entity.ProgramEntity;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.service.ProgramService;
import com.softuni.fitlaunch.service.UserService;
import com.softuni.fitlaunch.service.WorkoutExerciseService;
import com.softuni.fitlaunch.service.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProgramController {

    private final ProgramService programService;

    private final UserService userService;


    public ProgramController(ProgramService programService, UserService userService) {
        this.programService = programService;
        this.userService = userService;
    }

    @GetMapping("/programs")
    public String loadAllPrograms(Model model, Principal principal) {

        List<ProgramEntity> allPrograms = programService.loadAllPrograms();
        UserDTO loggedUser = userService.getUserByUsername(principal.getName());

        model.addAttribute("membership", loggedUser.getMembership());
        model.addAttribute("allPrograms", allPrograms);

        return "programs";
    }

    @GetMapping("/programs/{programId}")
    public String loadProgramById(@PathVariable("programId") Long programId, Model model, Principal principal) {
        ProgramEntity program = programService.getById(programId);
        List<ProgramWeekDTO> allWeeksByProgramId = programService.getAllWeeksByProgramId(programId);

        UserDTO user = userService.getUserByUsername(principal.getName());

        model.addAttribute("program", program);
        model.addAttribute("allWeeks", allWeeksByProgramId);
        model.addAttribute("user", user);


        return "program-details";
    }

    @GetMapping("/workouts/{programId}/{weekId}/{workoutId}")
    public String programWorkoutDetails(@PathVariable("programId") Long programId,
                                        @PathVariable("weekId") Long weekId,
                                        @PathVariable("workoutId") Long workoutId,
                                        Model model,
                                        Principal principal) {

        UserDTO loggedUser = userService.getUserByUsername(principal.getName());
        ProgramEntity program = programService.getById(programId);
        ProgramWeekDTO programWeekById = programService.getProgramWeekById(weekId);
        ProgramWeekWorkoutDTO programWeekWorkoutById = programService.getProgramWeekWorkoutById(workoutId);

        boolean hasStarted = userService.isWorkoutStarted(principal.getName(), programWeekWorkoutById);
        boolean isCompleted = userService.isWorkoutCompleted(principal.getName(), programWeekWorkoutById);
        boolean hasLiked = userService.isWorkoutLiked(loggedUser, programWeekWorkoutById);


        model.addAttribute("workout", programWeekWorkoutById);
        model.addAttribute("program", program);
        model.addAttribute("week", programWeekById);
        model.addAttribute("hasStarted", hasStarted);
        model.addAttribute("isCompleted", isCompleted);
        model.addAttribute("hasLiked", hasLiked);


        return "workout-details";
    }



    @PostMapping("/workouts/start/{programId}/{weekId}/{workoutId}")
    public String workoutStart(@PathVariable("programId") Long programId, @PathVariable("weekId") Long weekId, @PathVariable("workoutId") Long workoutId, Principal principal) {

        UserDTO loggedUser = userService.getUserByUsername(principal.getName());
        ProgramWeekWorkoutDTO programWorkout = programService.getProgramWorkout(programId, weekId, workoutId, loggedUser);
        userService.startProgramWorkout(principal.getName(), programWorkout);

        return String.format("redirect:/workouts/%d/%d/%d", programId, weekId, workoutId);
    }


    @PostMapping("/workouts/complete/{programId}/{weekId}/{workoutId}")
    public String workoutComplete(@PathVariable("programId") Long programId, @PathVariable("weekId") Long weekId, @PathVariable("workoutId") Long workoutId, Principal principal) {

        UserDTO loggedUser = userService.getUserByUsername(principal.getName());
        ProgramWeekWorkoutDTO programWorkout = programService.getProgramWorkout(programId, weekId, workoutId, loggedUser);
        userService.completeProgramWorkout(principal.getName(), programWorkout);


        return String.format("redirect:/workouts/%d/%d/%d", programId, weekId, workoutId);

    }

    @PostMapping("/workouts/like/{programId}/{weekId}/{workoutId}")
    public String details(@PathVariable("programId") Long programId, @PathVariable("weekId") Long weekId, @PathVariable("workoutId") Long workoutId,
                          Principal principal) {

        UserDTO loggedUser = userService.getUserByUsername(principal.getName());
        ProgramWeekWorkoutDTO programWorkout = programService.getProgramWorkout(programId, weekId, workoutId, loggedUser);

        boolean hasLiked = false;

        for (ProgramWeekWorkoutDTO likedWorkout : loggedUser.getWorkoutsLiked()) {
            if(likedWorkout.getId().equals(programWorkout.getId())) {
                hasLiked = true;
                break;
            }
        }


        if(hasLiked) {
            userService.dislike(loggedUser, programWorkout.getId());
        } else {
            userService.like(loggedUser, programWorkout.getId());
        }


        return String.format("redirect:/workouts/%d/%d/%d", programId, weekId, workoutId);
    }

}
