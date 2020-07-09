package com.example.repository;

import com.example.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
    boolean existsByGenderName(String genderName);
    Optional<Gender> findByGenderName(String genderName);
}