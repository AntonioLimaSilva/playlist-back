package com.example.playlist.gateway.h2db.repository;

import com.example.playlist.gateway.h2db.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String username);
}
