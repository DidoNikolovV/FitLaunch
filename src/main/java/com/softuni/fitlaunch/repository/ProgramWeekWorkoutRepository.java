package com.softuni.fitlaunch.repository;


import com.softuni.fitlaunch.model.entity.ProgramWeekWorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramWeekWorkoutRepository extends JpaRepository<ProgramWeekWorkoutEntity, Long> {
}
