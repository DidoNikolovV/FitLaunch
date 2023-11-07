package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.WorkoutDTO;
import com.softuni.fitlaunch.service.WorkoutService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workouts")
public class WorkoutsController {

    private final WorkoutService workoutService;

    public WorkoutsController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/all")
    public String all(Model model,
                      @PageableDefault(
            size = 3,
            sort = "id"
    ) Pageable pageable) {

        Page<WorkoutDTO> allWorkouts = workoutService.getAllWorkouts(pageable);

        model.addAttribute("workouts", allWorkouts);

        return "workouts";
    }
}
