package com.softuni.fitlaunch.web.rest;


import com.softuni.fitlaunch.model.dto.user.CoachDTO;
import com.softuni.fitlaunch.model.dto.view.ScheduledWorkoutView;
import com.softuni.fitlaunch.model.dto.workout.ScheduledWorkoutDTO;
import com.softuni.fitlaunch.service.CoachService;
import com.softuni.fitlaunch.service.ScheduleWorkoutService;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class ScheduledWorkoutsRestController {

    private final ScheduleWorkoutService scheduleWorkoutService;

    private final CoachService coachService;

    public ScheduledWorkoutsRestController(ScheduleWorkoutService scheduleWorkoutService, CoachService coachService) {
        this.scheduleWorkoutService = scheduleWorkoutService;
        this.coachService = coachService;
    }

    @GetMapping("/{username}/calendar/scheduledWorkouts")
    public ResponseEntity<List<ScheduledWorkoutView>> getAllScheduledWorkouts(@PathVariable("username") String username) {
        CoachDTO coachByUsername = coachService.getCoachByUsername(username);
        List<ScheduledWorkoutView> allCoachScheduledWorkouts = scheduleWorkoutService.getAllCoachScheduledWorkouts(coachByUsername);
        System.out.println("Scheduled workouts: " + allCoachScheduledWorkouts);

        return ResponseEntity.ok(allCoachScheduledWorkouts);
    }


    @DeleteMapping("/{username}/calendar/scheduledWorkouts/{eventId}")
    public ResponseEntity<List<ScheduledWorkoutView>> deleteScheduledWorkout(@PathVariable("username") String username, @PathVariable("eventId") Long eventId) throws ObjectNotFoundException {

        scheduleWorkoutService.deleteScheduledWorkout(username, eventId);

        return ResponseEntity.ok().build();

    }
}
