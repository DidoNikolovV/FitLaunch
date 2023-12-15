package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "programs_weeks_workouts")
public class ProgramWeekWorkoutEntity extends BaseEntity{


    @ManyToOne
    @JoinColumn(name = "program_week_id")
    private ProgramWeekEntity programWeek;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutEntity workout;

    @Column(name = "has_started")
    private boolean hasStarted;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private Long likes;


    public ProgramWeekEntity getProgramWeek() {
        return programWeek;
    }

    public ProgramWeekWorkoutEntity setProgramWeek(ProgramWeekEntity programWeek) {
        this.programWeek = programWeek;
        return this;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public ProgramWeekWorkoutEntity setWorkout(WorkoutEntity workoutId) {
        this.workout = workoutId;
        return this;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public ProgramWeekWorkoutEntity setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ProgramWeekWorkoutEntity setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public ProgramWeekWorkoutEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public Long getLikes() {
        return likes;
    }

    public ProgramWeekWorkoutEntity setLikes(Long likes) {
        this.likes = likes;
        return this;
    }
}
