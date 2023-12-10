package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.program.ProgramWeekDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.entity.ProgramEntity;
import com.softuni.fitlaunch.model.entity.ProgramWeekEntity;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.service.ProgramService;
import com.softuni.fitlaunch.service.UserService;
import com.softuni.fitlaunch.service.WorkoutExerciseService;
import com.softuni.fitlaunch.service.WorkoutService;
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

    private final WorkoutService workoutService;
    private final WorkoutExerciseService workoutExerciseService;

    public ProgramController(ProgramService programService, UserService userService, WorkoutService workoutService, WorkoutExerciseService workoutExerciseService) {
        this.programService = programService;
        this.userService = userService;
        this.workoutService = workoutService;
        this.workoutExerciseService = workoutExerciseService;
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
    public String loadProgramById(@PathVariable("programId") Long programId, Model model) {
        ProgramEntity program = programService.getById(programId);
        List<ProgramWeekDTO> allWeeksByProgramId = programService.getAllWeeksByProgramId(programId);


        model.addAttribute("program", program);
        model.addAttribute("allWeeks", allWeeksByProgramId);

        return "program-details";
    }

    @GetMapping("/workouts/{programId}/{weekId}/{workoutId}")
    public String programWorkoutDetails(@PathVariable("programId") Long programId,
                                        @PathVariable("weekId") Long weekId,
                                        @PathVariable("workoutId") Long workoutId,
                                        Model model) {
        ProgramEntity program = programService.getById(programId);
        ProgramWeekDTO programWeekById = programService.getProgramWeekById(weekId);
        ProgramWeekWorkoutDTO programWeekWorkoutById = programService.getProgramWeekWorkoutById(workoutId);

        model.addAttribute("workout", programWeekWorkoutById);
        model.addAttribute("program", program);
        model.addAttribute("week", programWeekById);

        return "workout-details";
    }


//    @PostMapping("/workouts/complete/{programId}/{weekId}/{id}")
//    public String workoutComplete(@PathVariable("programId") Long programId, @PathVariable("weekId") Long weekId, @PathVariable("id") Long id, Principal principal) {
//        // ... existing code ...
//        String currentUserUsername = principal.getName();
//
//        programWorkoutService.completeWorkoutForTheWeek(id, weekId, programId, currentUserUsername);
//
//
//        return "redirect:/workouts/{programId}/{weekId}/{id}";
//
//    }

//    @PostMapping("/workouts/start/{programId}/{weekId}/{id}")
//    public String workoutStart(@PathVariable("programId") Long programId, @PathVariable("weekId") Long weekId, @PathVariable("id") Long id, Principal principal) {
//
////        workoutService.startWorkout(id, principal.getName());
//        programWorkoutService.startWorkoutForTheWeek(id, weekId, programId, principal.getName());
//
//
//        return String.format("redirect:/workouts/%d/%d/%d", programId, weekId, id);
//    }

}
