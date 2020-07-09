package com.example.service;

import com.example.model.UserStatusNameModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserStatusNameService {
    List<UserStatusNameModel> getAllUserStatusNames();
    UserStatusNameModel getUserStatusNameByStatusId(int statusId);
    UserStatusNameModel getUserStatusNameByStatusName(String statusName);
    UserStatusNameModel addUserStatusName(UserStatusNameModel userStatusNameModel);

    boolean existsByStatusId(int statusId);
}
