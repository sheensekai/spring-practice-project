package com.example.repository;

import com.example.entities.UserStatusName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatusNameRepository extends JpaRepository<UserStatusName, Integer> {
    Optional<UserStatusName> findByStatusName(String statusName);
    boolean existsByStatusName(String userStatusName);
}
