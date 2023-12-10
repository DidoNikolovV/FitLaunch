package com.softuni.fitlaunch.repository;

import com.softuni.fitlaunch.model.entity.ProgramWeekEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramWeekRepository extends JpaRepository<ProgramWeekEntity, Long> {
    Optional<List<ProgramWeekEntity>> findAllByProgramId(Long programId);
}
