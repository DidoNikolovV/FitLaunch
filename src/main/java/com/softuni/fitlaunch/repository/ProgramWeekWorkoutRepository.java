package com.softuni.fitlaunch.repository;


import com.softuni.fitlaunch.model.entity.ProgramWeekWorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramWeekWorkoutRepository extends JpaRepository<ProgramWeekWorkoutEntity, Long> {
    Optional<ProgramWeekWorkoutEntity> findByProgramWeekIdAndId(Long weekId, Long workoutId);

    Optional<List<ProgramWeekWorkoutEntity>> findAllByProgramId(Long programId);
}
