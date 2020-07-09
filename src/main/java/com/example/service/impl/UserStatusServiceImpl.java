package com.example.service.impl;

import com.example.UserStatusEnum;
import com.example.entities.User;
import com.example.entities.UserStatus;
import com.example.exception.notfound.ResourceNotFoundException;
import com.example.exception.notfound.UserNotFoundException;
import com.example.model.UserModel;
import com.example.model.UserStatusModel;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusRepository;
import com.example.service.UserService;
import com.example.service.UserStatusNameService;
import com.example.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserStatusServiceImpl implements UserStatusService {
    private final UserStatusRepository userStatusRepository;
    private final UserStatusNameService userStatusNameService;
    private final UserService userService;

    public UserStatusServiceImpl(
            @Autowired UserStatusRepository userStatusRepository,
            @Autowired UserStatusNameService userStatusNameService,
            @Autowired UserService userService) {
        this.userStatusRepository = userStatusRepository;
        this.userStatusNameService = userStatusNameService;
        this.userService = userService;
    }

    public UserStatusModel updateUserStatus(int userId, UserStatusEnum onlineStatus)
        throws UserNotFoundException {
        if (!this.userService.existsByUserId(userId)) {
            throw new UserNotFoundException("User with id " + userId + " doesn't exist");
        }

        UserStatus userStatus = this.updateUserStatusStatisticsImpl(userId, onlineStatus);
        if (userStatus.getUserId() == 0) {
            throw new RuntimeException("Something went wrong");
        }
        this.updateUserStatusImpl(userId, userStatus);

        return new UserStatusModel(userStatus, onlineStatus);
    }

    private void updateUserStatusImpl(int userId, UserStatus newStatus) {
        UserModel userModel = this.userService.getUserByUserId(userId);
        if (userModel.getUserId() == 0) {
            throw new RuntimeException("Somethign went wrong");
        }
        User user = new User(userModel);
        user.updateStatus(newStatus);
        this.userService.updateUser(new UserModel(user));
    }

    private UserStatus updateUserStatusStatisticsImpl(int userId, UserStatusEnum onlineStatus) {
        UserStatusNameModel userStatusName = this.userStatusNameService
                .getUserStatusNameByStatusName(onlineStatus.toString().toLowerCase());

        int statusId = userStatusName.getStatusId();
        long updateTime = System.currentTimeMillis();
        UserStatus userStatus = new UserStatus(updateTime, userId, statusId);
        return this.userStatusRepository.save(userStatus);
    }

    public List<UserStatusModel> getStatistics(Integer userId, UserStatusEnum onlineStatus, Long updateTime) {
        Integer statusId = this.makeStatusIdValue(onlineStatus);
        updateTime = this.makeUpdateTimeValue(updateTime);

        List<UserStatus> userStatusList = this.getStatisticsQueryImpl(userId, statusId, updateTime);
        return this.getStatisticsMakingAnswerImpl(userStatusList);
    }

    private Integer makeStatusIdValue(UserStatusEnum onlineStatus) {
        if (onlineStatus == null) {
            return null;
        } else {
            UserStatusNameModel userStatusNameModel = this.userStatusNameService
                    .getUserStatusNameByStatusName(onlineStatus.toString().toLowerCase());
            return userStatusNameModel.getStatusId();
        }
    }

    private Long makeUpdateTimeValue(Long updateTime) {
        if (updateTime == null) {
            updateTime = 0L;
        }
        return updateTime;
    }

    private List<UserStatus> getStatisticsQueryImpl(Integer userId, Integer statusId, Long updateTime) {
        if (userId != null && statusId != null) {
            return this.userStatusRepository
                    .findByUserIdEqualsAndStatusIdEqualsAndUpdateTimeGreaterThan(userId, statusId, updateTime);
        } else if (userId == null && statusId != null) {
            return this.userStatusRepository
                    .findByStatusIdAndUpdateTimeGreaterThan(statusId, updateTime);
        } else if (userId != null) {
            return this.userStatusRepository
                    .findByUserIdAndUpdateTimeGreaterThan(userId, updateTime);
        } else {
            return this.userStatusRepository
                    .findByUpdateTimeGreaterThan(updateTime);
        }
    }

    private List<UserStatusModel> getStatisticsMakingAnswerImpl(List<UserStatus> userStatusList) {
        List<UserStatusModel> answer = new ArrayList<>();
        HashMap<Integer, UserStatusEnum> idToName = new HashMap<>();
        List<UserStatusNameModel> allStatusNames = this.userStatusNameService.getAllUserStatusNames();
        for (UserStatusNameModel userStatusNameModel : allStatusNames) {
            UserStatusEnum userStatusEnum = UserStatusEnum.findEnum(userStatusNameModel.getStatusName());
            if (userStatusEnum == null) {
                throw new ResourceNotFoundException("Enum for " + userStatusNameModel.getStatusName() + " doesn't exist");
            }

            idToName.put(userStatusNameModel.getStatusId(), userStatusEnum);
        }

        for (UserStatus userStatus : userStatusList) {
            UserStatusEnum userStatusEnum = idToName.get(userStatus.getStatusId());
            answer.add(new UserStatusModel(userStatus, userStatusEnum));
        }

        return answer;
    }

}