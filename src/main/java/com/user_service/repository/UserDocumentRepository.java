package com.user_service.repository;

import com.user_service.entity.UserDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocumentEntity, Integer> {
    Optional<UserDocumentEntity> findByUserAccountId(String userAccountId);
}
