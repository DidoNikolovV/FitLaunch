package com.softuni.fitlaunch.repository;

import com.softuni.fitlaunch.model.entity.WorkoutScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutScheduleRepository extends JpaRepository<WorkoutScheduleEntity, Long> {
}
