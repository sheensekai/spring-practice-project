package com.example.controller;

import com.example.dto.UserStatusNameDTO;
import com.example.exception.exists.UserStatusNameAlreadyExistsException;
import com.example.model.UserStatusNameModel;
import com.example.service.UserStatusNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userStatusName")
public class UserStatusNameController {
    private final UserStatusNameService userStatusNameService;

    public UserStatusNameController(@Autowired UserStatusNameService userStatusNameService) {
        this.userStatusNameService = userStatusNameService;
    }

    @PostMapping("/")
    public UserStatusNameDTO addUserStatusName(UserStatusNameDTO userStatusNameDTO)
        throws UserStatusNameAlreadyExistsException {
        UserStatusNameModel userStatusNameModel = new UserStatusNameModel(userStatusNameDTO);

        userStatusNameModel = this.userStatusNameService.addUserStatusName(userStatusNameModel);
        return new UserStatusNameDTO(userStatusNameModel);
    }
}
