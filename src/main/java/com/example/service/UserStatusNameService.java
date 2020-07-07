package com.example.service;

import com.example.entities.UserStatusName;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStatusNameService {
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

    public UserStatusNameModel getStatusById(int statusId) {
        UserStatusName userStatusName = this.userStatusNameRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("UserStatusName with id " + statusId + " doesn't exist"));
        return new UserStatusNameModel(userStatusName);
    }

    public UserStatusNameModel getStatusByStatusName(String statusName) {
        UserStatusName userStatusName = this.userStatusNameRepository.findByStatusName(statusName)
                .orElseThrow(() -> new ResourceNotFoundException("UserStatusName with name " + statusName + " doesn't exist"));
        return new UserStatusNameModel(userStatusName);
    }

    public boolean existsbyId(int statusId) {
        return this.userStatusNameRepository.existsById(statusId);
    }
}
