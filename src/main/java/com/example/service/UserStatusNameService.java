package com.example.service;

import com.example.model.UserStatusNameModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserStatusNameService {
    List<UserStatusNameModel> getAllStatuses();
    UserStatusNameModel getStatusByStatusId(int statusId);
    UserStatusNameModel getStatusByStatusName(String statusName);

    boolean existsByStatusId(int statusId);
}
