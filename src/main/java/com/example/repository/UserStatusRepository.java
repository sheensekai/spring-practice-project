package com.example.repository;

import com.example.entities.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    List<UserStatus> findByUserIdEqualsAndStatusIdEqualsAndUpdateTimeGreaterThan
            (int userId, int statusId, long updateTime);

    List<UserStatus> findByUserIdAndUpdateTimeGreaterThan(int userId, long updateTime);
    List<UserStatus> findByStatusIdAndUpdateTimeGreaterThan(int statusId, long updateTime);
    List<UserStatus> findByUpdateTimeGreaterThan(long updateTime);
}
