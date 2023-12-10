package com.softuni.fitlaunch.model.dto.program;

import com.softuni.fitlaunch.model.dto.comment.CommentCreationDTO;
import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public class ProgramWeekWorkoutDTO {

    @NotNull
    private Long id;


    @NotNull
    private ProgramWeekDTO programWeek;

    @NotNull
    private WorkoutDetailsDTO workout;

    @NotNull
    private boolean hasStarted;

    @NotNull
    private boolean isCompleted;

    private List<CommentCreationDTO> comments;

    private List<UserDTO> usersCompleted;
    public Long getId() {
        return id;
    }

    public ProgramWeekWorkoutDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProgramWeekDTO getProgramWeek() {
        return programWeek;
    }

    public ProgramWeekWorkoutDTO setProgramWeek(ProgramWeekDTO programWeek) {
        this.programWeek = programWeek;
        return this;
    }

    public WorkoutDetailsDTO getWorkout() {
        return workout;
    }

    public ProgramWeekWorkoutDTO setWorkout(WorkoutDetailsDTO workout) {
        this.workout = workout;
        return this;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public ProgramWeekWorkoutDTO setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ProgramWeekWorkoutDTO setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public List<CommentCreationDTO> getComments() {
        return comments;
    }

    public ProgramWeekWorkoutDTO setComments(List<CommentCreationDTO> comments) {
        this.comments = comments;
        return this;
    }

    public List<UserDTO> getUsersCompleted() {
        return usersCompleted;
    }

    public ProgramWeekWorkoutDTO setUsersCompleted(List<UserDTO> usersCompleted) {
        this.usersCompleted = usersCompleted;
        return this;
    }
}
