package com.example.service;

import com.example.UserStatusEnum;
import com.example.model.UserStatusModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserStatusService {
    UserStatusModel updateUserStatus(int userId, UserStatusEnum onlineStatus);
    List<UserStatusModel> getStatistics(Integer userId, UserStatusEnum onlineStatus, Long updateTime);
}
