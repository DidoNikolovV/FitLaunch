package com.softuni.fitlaunch.repository;

import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.UserRoleEntity;
import com.softuni.fitlaunch.model.enums.UserTitleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserRoleEntity> findRoleById(Long id);

    Optional<UserEntity> findByActivationCode(String activationCode);

    Optional<List<UserEntity>> findAllByTitle(UserTitleEnum userTitleEnum);

    Optional<UserEntity> findByIdAndTitle(Long id, UserTitleEnum userTitleEnum);
}
