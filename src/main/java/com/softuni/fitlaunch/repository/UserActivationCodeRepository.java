package com.softuni.fitlaunch.repository;

import com.softuni.fitlaunch.model.entity.UserActivationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserActivationCodeRepository extends JpaRepository<UserActivationCodeEntity, Long> {

    @Query("SELECT a.activationCode FROM UserActivationCodeEntity  a WHERE a.user.id = :userId")
    Optional<String> findUserActivationCodeByUserId(@Param("userId") Long userId);

    Optional<UserActivationCodeEntity> findByActivationCode(String activationCode);
}
