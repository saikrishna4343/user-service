package com.user_service.repository;

import com.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailOrPhone(String email, String phone);

    Optional<UserEntity> findByEmailOrUserAccountId(String email, String userAccountId);
}
