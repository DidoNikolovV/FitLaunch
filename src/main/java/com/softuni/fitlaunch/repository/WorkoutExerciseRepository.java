package com.softuni.fitlaunch.repository;


import com.softuni.fitlaunch.model.entity.WorkoutExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExerciseEntity, Long> {


//    @Query("SELECT we FROM WorkoutExerciseEntity we WHERE we.workout.id = :workoutId")
    List<WorkoutExerciseEntity> findByWorkoutId(Long workoutId);

    List<WorkoutExerciseEntity> findByExerciseId(Long exerciseId);
}
