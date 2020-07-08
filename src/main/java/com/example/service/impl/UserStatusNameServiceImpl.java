package com.example.service.impl;

import com.example.entities.UserStatusName;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusNameRepository;
import com.example.service.UserStatusNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStatusNameServiceImpl implements UserStatusNameService {
    @Autowired
    UserStatusNameRepository userStatusNameRepository;

    public List<UserStatusNameModel> getAllStatuses() {
        List<UserStatusName> userStatusList = this.userStatusNameRepository.findAll();
        List<UserStatusNameModel> answer = new ArrayList<>();
        for (UserStatusName userStatusName : userStatusList) {
            answer.add(new UserStatusNameModel(userStatusName));
        }

        return answer;
    }

    public UserStatusNameModel getStatusByStatusId(int statusId) {
        UserStatusName userStatusName = this.userStatusNameRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("UserStatusName with id " + statusId + " doesn't exist"));
        return new UserStatusNameModel(userStatusName);
    }

    public UserStatusNameModel getStatusByStatusName(String statusName) {
        UserStatusName userStatusName = this.userStatusNameRepository.findByStatusName(statusName)
                .orElseThrow(() -> new ResourceNotFoundException("UserStatusName with name " + statusName + " doesn't exist"));
        return new UserStatusNameModel(userStatusName);
    }

    public boolean existsByStatusId(int statusId) {
        return this.userStatusNameRepository.existsById(statusId);
    }
}
