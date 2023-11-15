package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "workout_schedules")
public class WorkoutScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private WorkoutEntity workout;

    @Column(nullable = false)
    private String scheduledDate;

    public Long getId() {
        return id;
    }

    public WorkoutScheduleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public WorkoutScheduleEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public WorkoutScheduleEntity setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public WorkoutScheduleEntity setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
        return this;
    }
}
