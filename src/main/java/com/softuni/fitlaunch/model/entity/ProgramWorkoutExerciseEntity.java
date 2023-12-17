package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "program_workouts_exercises")
public class ProgramWorkoutExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private ProgramWeekWorkoutEntity workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @Column(columnDefinition = "int default 0")
    private int sets;

    @Column(columnDefinition = "int default 0")
    private int reps;

    @Column(name = "is_completed")
    private boolean isCompleted;

    public Long getId() {
        return id;
    }

    public ProgramWorkoutExerciseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ProgramWeekWorkoutEntity getWorkout() {
        return workout;
    }

    public ProgramWorkoutExerciseEntity setWorkout(ProgramWeekWorkoutEntity workout) {
        this.workout = workout;
        return this;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public ProgramWorkoutExerciseEntity setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public ProgramWorkoutExerciseEntity setSets(int sets) {
        this.sets = sets;
        return this;
    }

    public int getReps() {
        return reps;
    }

    public ProgramWorkoutExerciseEntity setReps(int reps) {
        this.reps = reps;
        return this;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
