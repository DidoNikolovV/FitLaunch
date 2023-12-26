package com.softuni.fitlaunch.model.dto.user;

import com.softuni.fitlaunch.model.dto.comment.CommentCreationDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWorkoutExerciseDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutExerciseDTO;
import com.softuni.fitlaunch.model.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String email;

    private List<UserRoleDTO> roles;

    private String membership;


    private List<CommentCreationDTO> comments;

    private List<ProgramWeekWorkoutDTO> workoutsCompleted;

    private List<ProgramWeekWorkoutDTO> workoutStarted;

    private List<ProgramWeekWorkoutDTO> workoutsLiked;


    private List<ProgramWorkoutExerciseDTO> exercisesCompleted;

    private boolean activated = false;



    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<UserRoleDTO> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<UserRoleDTO> roles) {
        this.roles = roles;
        return this;
    }

    public String getMembership() {
        return membership;
    }

    public UserDTO setMembership(String membership) {
        this.membership = membership;
        return this;
    }

    public List<CommentCreationDTO> getComments() {
        return comments;
    }

    public UserDTO setComments(List<CommentCreationDTO> comments) {
        this.comments = comments;
        return this;
    }


    public List<ProgramWeekWorkoutDTO> getWorkoutsCompleted() {
        return workoutsCompleted;
    }

    public UserDTO setWorkoutsCompleted(List<ProgramWeekWorkoutDTO> workoutsCompleted) {
        this.workoutsCompleted = workoutsCompleted;
        return this;
    }

    public List<ProgramWeekWorkoutDTO> getWorkoutStarted() {
        return workoutStarted;
    }

    public UserDTO setWorkoutStarted(List<ProgramWeekWorkoutDTO> workoutStarted) {
        this.workoutStarted = workoutStarted;
        return this;
    }

    public List<ProgramWeekWorkoutDTO> getWorkoutsLiked() {
        return workoutsLiked;
    }

    public UserDTO setWorkoutsLiked(List<ProgramWeekWorkoutDTO> workoutsLiked) {
        this.workoutsLiked = workoutsLiked;
        return this;
    }

    public boolean isActivated() {
        return activated;
    }

    public UserDTO setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public List<ProgramWorkoutExerciseDTO> getExercisesCompleted() {
        return exercisesCompleted;
    }

    public UserDTO setExercisesCompleted(List<ProgramWorkoutExerciseDTO> exercisesCompleted) {
        this.exercisesCompleted = exercisesCompleted;
        return this;
    }

    public boolean workoutCompleted(ProgramWeekWorkoutDTO programWeekWorkoutDTO) {
        for (ProgramWeekWorkoutDTO weekWorkoutDTO : workoutsCompleted) {
            if(weekWorkoutDTO.getId().equals(programWeekWorkoutDTO.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean exerciseCompleted(ProgramWorkoutExerciseDTO workoutExerciseDTO) {
        for (ProgramWorkoutExerciseDTO exercise : exercisesCompleted) {
            if(exercise.getId().equals(workoutExerciseDTO.getId())) {
                return true;
            }
        }

        return false;
    }
}