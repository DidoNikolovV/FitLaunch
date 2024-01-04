package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "program_workouts_exercises")
@Getter
@Setter
public class ProgramWorkoutExerciseEntity extends BaseEntity {


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


}
