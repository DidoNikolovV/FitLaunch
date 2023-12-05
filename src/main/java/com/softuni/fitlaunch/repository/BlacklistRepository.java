package com.softuni.fitlaunch.repository;

import com.softuni.fitlaunch.model.entity.BlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlacklistRepository extends JpaRepository<BlacklistEntity, Long> {

    Optional<BlacklistEntity> findByIpAddress(String ipAddress);
}
