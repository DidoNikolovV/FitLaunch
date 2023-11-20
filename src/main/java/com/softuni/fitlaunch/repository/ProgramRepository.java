package com.softuni.fitlaunch.repository;


import com.softuni.fitlaunch.model.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {
}
