package com.example.service;

import com.example.UserStatusEnum;
import com.example.entities.UserStatus;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserStatusModel;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserStatusService {
    @Autowired
    private UserStatusRepository userStatusRepository;
    @Autowired
    private UserStatusNameService userStatusNameService;

    public List<UserStatusModel> getStatistics(Integer userId, String onlineStatus, Long updateTime) {
        Integer statusId = this.makeStatusIdValue(onlineStatus);
        updateTime = this.makeUpdateTimeValue(updateTime);

        List<UserStatus> userStatusList = this.getStatisticsQueryImpl(userId, statusId, updateTime);
        return this.getStatisticsMakingAnswerImpl(userStatusList);
    }

    private Integer makeStatusIdValue(String onlineStatus) {
        if (onlineStatus == null) {
            return null;
        } else {
            UserStatusNameModel userStatusNameModel = this.userStatusNameService
                    .getStatusByStatusName(onlineStatus.toLowerCase());
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
        List<UserStatusNameModel> allStatusNames = this.userStatusNameService.getAllStatuses();
        for (UserStatusNameModel userStatusNameModel : allStatusNames) {
            UserStatusEnum userStatusEnum = UserStatusEnum.findEnum(userStatusNameModel.getStatusName());
            if (userStatusEnum == null) {
                throw new ResourceNotFoundException("Enum for " + allStatusNames + " doesn't exist");
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