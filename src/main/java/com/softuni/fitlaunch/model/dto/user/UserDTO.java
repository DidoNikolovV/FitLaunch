package com.softuni.fitlaunch.model.dto.user;

import com.softuni.fitlaunch.model.dto.comment.CommentCreationDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.entity.UserRoleEntity;

import java.util.List;

public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private List<UserRoleEntity> roles;

    private String membership;


    private List<CommentCreationDTO> comments;

    private List<WorkoutDTO> likedWorkouts;

    private List<ProgramWeekWorkoutDTO> workoutsCompleted;

    private List<ProgramWeekWorkoutDTO> workoutStarted;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String email, List<UserRoleEntity> roles, String membership,
                   List<CommentCreationDTO> comments, List<WorkoutDTO> likedWorkouts, List<ProgramWeekWorkoutDTO> workoutsCompleted, List<ProgramWeekWorkoutDTO> workoutStarted) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.membership = membership;
        this.comments = comments;
        this.likedWorkouts = likedWorkouts;
        this.workoutsCompleted = workoutsCompleted;
        this.workoutStarted = workoutStarted;
    }

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

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

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<UserRoleEntity> roles) {
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

    public List<WorkoutDTO> getLikedWorkouts() {
        return likedWorkouts;
    }

    public UserDTO setLikedWorkouts(List<WorkoutDTO> likedWorkouts) {
        this.likedWorkouts = likedWorkouts;
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

    public boolean workoutCompleted(ProgramWeekWorkoutDTO programWeekWorkoutDTO) {
        for (ProgramWeekWorkoutDTO weekWorkoutDTO : workoutsCompleted) {
            if(weekWorkoutDTO.getId().equals(programWeekWorkoutDTO.getId())) {
                return true;
            }
        }
        return false;
    }
}