package com.softuni.fitlaunch.repository;

import com.softuni.fitlaunch.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findById(Long id);
}
