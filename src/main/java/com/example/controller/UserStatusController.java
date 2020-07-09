package com.example.controller;

import com.example.UserStatusEnum;
import com.example.dto.UserStatusDTO;
import com.example.exception.notfound.ResourceNotFoundException;
import com.example.model.UserStatusModel;
import com.example.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/statistics")
    public List<UserStatusDTO> getStatistics
            (@RequestParam(value = "userId", defaultValue = "null", required = false) String userIdString,
             @RequestParam(value = "onlineStatus", defaultValue = "null", required = false) String onlineStatus,
             @RequestParam(value = "updateTime", defaultValue = "0", required = false) String updateTimeString)
        throws ResourceNotFoundException {

        Integer userId = null;
        if (!userIdString.equals("null")) {
            userId = Integer.parseInt(userIdString);
        }

        Long updateTime = null;
        if (!updateTimeString.equals("null")) {
            updateTime = Long.parseLong(updateTimeString);
        }

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