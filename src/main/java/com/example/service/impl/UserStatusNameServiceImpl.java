package com.example.service.impl;

import com.example.entities.UserStatusName;
import com.example.exception.exists.UserStatusNameAlreadyExistsException;
import com.example.exception.notfound.UserStatusNameNotFoundException;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusNameRepository;
import com.example.service.UserStatusNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStatusNameServiceImpl implements UserStatusNameService {
    private final UserStatusNameRepository userStatusNameRepository;

    public UserStatusNameServiceImpl(@Autowired UserStatusNameRepository userStatusNameRepository) {
        this.userStatusNameRepository = userStatusNameRepository;
    }

    public UserStatusNameModel addUserStatusName(UserStatusNameModel userStatusNameModel)
        throws UserStatusNameAlreadyExistsException {
        UserStatusName userStatusName = new UserStatusName(userStatusNameModel);
        if (this.userStatusNameRepository.existsByStatusName(userStatusName.getStatusName())) {
            throw new UserStatusNameAlreadyExistsException(
                    "UserStatusName with StatusName " + userStatusName.getStatusName() + " already exists");
        }

        userStatusName = this.userStatusNameRepository.save(userStatusName);
        return new UserStatusNameModel(userStatusName);
    }

    public List<UserStatusNameModel> getAllUserStatusNames() {
        List<UserStatusName> userStatusList = this.userStatusNameRepository.findAll();
        List<UserStatusNameModel> answer = new ArrayList<>();
        for (UserStatusName userStatusName : userStatusList) {
            answer.add(new UserStatusNameModel(userStatusName));
        }

        return answer;
    }

    public UserStatusNameModel getUserStatusNameByStatusId(int statusId)
        throws UserStatusNameNotFoundException {
        UserStatusName userStatusName = this.userStatusNameRepository.findById(statusId)
                .orElseThrow(() -> new UserStatusNameNotFoundException("UserStatusName with id " + statusId + " doesn't exist"));
        return new UserStatusNameModel(userStatusName);
    }

    public UserStatusNameModel getUserStatusNameByStatusName(String statusName)
        throws UserStatusNameNotFoundException {
        UserStatusName userStatusName = this.userStatusNameRepository.findByStatusName(statusName)
                .orElseThrow(() -> new UserStatusNameNotFoundException("UserStatusName with name " + statusName + " doesn't exist"));
        return new UserStatusNameModel(userStatusName);
    }

    public boolean existsByStatusId(int statusId) {
        return this.userStatusNameRepository.existsById(statusId);
    }

    public boolean existsByStatusName(String statusName) { return this.userStatusNameRepository.existsByStatusName(statusName); }
}
