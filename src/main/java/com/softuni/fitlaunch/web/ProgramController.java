package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.program.ProgramWeekDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.user.UserDTO;
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

    private final ModelMapper modelMapper;

    public ProgramController(ProgramService programService, UserService userService, ModelMapper modelMapper) {
        this.programService = programService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/programs")
    public String loadAllPrograms(Model model, Principal principal) {

        List<ProgramEntity> allPrograms = programService.loadAllPrograms();
        UserEntity loggedUser = userService.getUserByUsername(principal.getName());

        model.addAttribute("membership", loggedUser.getMembership());
        model.addAttribute("allPrograms", allPrograms);

        return "programs";
    }

    @GetMapping("/programs/{programId}")
    public String loadProgramById(@PathVariable("programId") Long programId, Model model, Principal principal) {
        ProgramEntity program = programService.getById(programId);
        List<ProgramWeekDTO> allWeeksByProgramId = programService.getAllWeeksByProgramId(programId);

        UserDTO user = modelMapper.map(userService.getUserByUsername(principal.getName()), UserDTO.class);

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
        ProgramEntity program = programService.getById(programId);
        ProgramWeekDTO programWeekById = programService.getProgramWeekById(weekId);
        ProgramWeekWorkoutDTO programWeekWorkoutById = programService.getProgramWeekWorkoutById(workoutId);

        boolean hasStarted = userService.isWorkoutStarted(principal.getName(), programWeekWorkoutById);
        boolean isCompleted = userService.isWorkoutCompleted(principal.getName(), programWeekWorkoutById);

        model.addAttribute("workout", programWeekWorkoutById);
        model.addAttribute("program", program);
        model.addAttribute("week", programWeekById);
        model.addAttribute("hasStarted", hasStarted);
        model.addAttribute("isCompleted", isCompleted);


        return "workout-details";
    }



    @PostMapping("/workouts/start/{programId}/{weekId}/{workoutId}")
    public String workoutStart(@PathVariable("programId") Long programId, @PathVariable("weekId") Long weekId, @PathVariable("workoutId") Long workoutId, Principal principal) {

        ProgramWeekWorkoutDTO programWorkout = programService.getProgramWorkout(programId, weekId, workoutId, principal.getName());
        userService.startProgramWorkout(principal.getName(), programWorkout);

        return String.format("redirect:/workouts/%d/%d/%d", programId, weekId, workoutId);
    }


    @PostMapping("/workouts/complete/{programId}/{weekId}/{workoutId}")
    public String workoutComplete(@PathVariable("programId") Long programId, @PathVariable("weekId") Long weekId, @PathVariable("workoutId") Long workoutId, Principal principal) {
        String currentUserUsername = principal.getName();


        ProgramWeekWorkoutDTO programWorkout = programService.getProgramWorkout(programId, weekId, workoutId, principal.getName());
        userService.completeProgramWorkout(principal.getName(), programWorkout);


        return String.format("redirect:/workouts/%d/%d/%d", programId, weekId, workoutId);

    }

}
