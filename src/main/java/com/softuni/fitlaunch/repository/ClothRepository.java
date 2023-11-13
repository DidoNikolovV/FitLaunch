package com.softuni.fitlaunch.repository;

import com.softuni.fitlaunch.model.entity.ClothEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<ClothEntity, Long> {
}
