package com.softuni.fitlaunch.model.entity;

import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public UserProfileEntity userProfile;

    @Column(nullable = false)
    private String password;



    @Column(nullable = false)
    private String membership;

    @OneToMany(mappedBy = "author")
    private List<CommentEntity> comments;

    @ManyToMany
    @JoinTable(
            name = "program_workouts_completed",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private List<ProgramWeekWorkoutEntity> workoutsCompleted = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "program_workouts_started",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private List<ProgramWeekWorkoutEntity> workoutsStarted = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "program_workouts_liked",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private List<ProgramWeekWorkoutEntity> workoutsLiked = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "program_workout_exercises_completed",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private List<ProgramWorkoutExerciseEntity> programExercisesCompleted = new ArrayList<>();


    private boolean activated = false;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "activation_code_expiration")
    private LocalDateTime activationCodeExpiration;



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserProfileEntity getUserProfile() {
        return userProfile;
    }

    public UserEntity setUserProfile(UserProfileEntity userProfile) {
        this.userProfile = userProfile;
        return this;
    }


    public String getMembership() {
        return membership;
    }

    public UserEntity setMembership(String membership) {
        this.membership = membership;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public UserEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public List<ProgramWeekWorkoutEntity> getWorkoutsCompleted() {
        return workoutsCompleted;
    }

    public UserEntity setWorkoutsCompleted(List<ProgramWeekWorkoutEntity> workoutsCompleted) {
        this.workoutsCompleted = workoutsCompleted;
        return this;
    }

    public List<ProgramWeekWorkoutEntity> getWorkoutsStarted() {
        return workoutsStarted;
    }

    public UserEntity setWorkoutsStarted(List<ProgramWeekWorkoutEntity> workoutsStarted) {
        this.workoutsStarted = workoutsStarted;
        return this;
    }

    public boolean isActivated() {
        return activated;
    }

    public UserEntity setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public UserEntity setActivationCode(String activationCode) {
        this.activationCode = activationCode;
        return this;
    }

    public LocalDateTime getActivationCodeExpiration() {
        return activationCodeExpiration;
    }

    public UserEntity setActivationCodeExpiration(LocalDateTime activationCodeExpiration) {
        this.activationCodeExpiration = activationCodeExpiration;
        return this;
    }

    public List<ProgramWeekWorkoutEntity> getWorkoutsLiked() {
        return workoutsLiked;
    }

    public UserEntity setWorkoutsLiked(List<ProgramWeekWorkoutEntity> workoutsLiked) {
        this.workoutsLiked = workoutsLiked;
        return this;
    }

    public List<ProgramWorkoutExerciseEntity> getProgramExercisesCompleted() {
        return programExercisesCompleted;
    }

    public UserEntity setProgramExercisesCompleted(List<ProgramWorkoutExerciseEntity> programExercisesCompleted) {
        this.programExercisesCompleted = programExercisesCompleted;
        return this;
    }
}



