package com.softuni.fitlaunch.repository;

import com.softuni.fitlaunch.model.entity.ProgramWorkoutExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramWorkoutExerciseRepository extends JpaRepository<ProgramWorkoutExerciseEntity, Long> {
}
