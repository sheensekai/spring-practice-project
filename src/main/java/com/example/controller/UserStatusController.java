package com.example.controller;

import com.example.UserStatusEnum;
import com.example.dto.UserStatusDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserStatusModel;
import com.example.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/userStatus")
public class UserStatusController {
    private final UserStatusService userStatusService;

    public UserStatusController(@Autowired UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @RequestMapping("/statistics")
    public List<UserStatusDTO> getStatistics
            (@RequestParam(name = "userId", defaultValue = "null") Integer userId,
             @RequestParam(name = "onlineStatus", defaultValue = "null") String onlineStatus,
             @RequestParam(name = "updateTime", defaultValue = "0") Long updateTime)
        throws ResourceNotFoundException {

        UserStatusEnum onlineStatusEnum = UserStatusEnum.findEnum(onlineStatus);
        List<UserStatusModel> userStatusModelList =
                this.userStatusService.getStatistics(userId, onlineStatusEnum, updateTime);
        List<UserStatusDTO> answer = new ArrayList<>();
        for (UserStatusModel userStatusModel : userStatusModelList) {
            answer.add(new UserStatusDTO(userStatusModel));
        }

        return answer;
    }

}