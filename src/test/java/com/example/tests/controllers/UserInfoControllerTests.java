package com.example.tests.controllers;

import com.example.GenderEnum;
import com.example.controller.UserInfoController;
import com.example.dto.UserInfoDTO;
import com.example.exception.exists.UserInfoAlreadyExistsException;
import com.example.exception.notfound.UserNotFoundException;
import com.example.model.UserInfoModel;
import com.example.service.impl.UserInfoServiceImpl;
import com.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserInfoControllerTests {
    private static UserInfoServiceImpl userInfoService;
    private static UserInfoController userInfoController;
    private static UserServiceImpl userService;

    @BeforeEach
    public void initBeforeEeach() {
        userInfoService = Mockito.mock(UserInfoServiceImpl.class);
        userService = Mockito.mock(UserServiceImpl.class);
        userInfoController = new UserInfoController(userInfoService, userService);
    }

    @Test
    public void whenUserIsNotContainedAddUserInfoThrowsException() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);

        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(false);

        assertThrows(UserNotFoundException.class,
                () -> userInfoController.addUserInfo(2, userInfoDTO));
    }

    @Test
    public void whenUserInfoIsContainedAddUserInfoThrowsException() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);

        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(true);
        Mockito.when(userInfoService.addUserInfo(Mockito.any())).thenThrow(UserInfoAlreadyExistsException.class);

        assertThrows(UserInfoAlreadyExistsException.class,
                () -> userInfoController.addUserInfo(2, userInfoDTO));
    }

    @Test
    public void whenUserIsContainedAndUserInfoIsNotContainedAddUserInfoReturnsAddedUserInfo() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);
        UserInfoModel userInfoModel = new UserInfoModel(userInfoDTO);
        userInfoModel.setGenderEnum(GenderEnum.UNKNOWN);
        UserInfoDTO toCompare = new UserInfoDTO(userInfoModel);

        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(true);
        Mockito.when(userInfoService.addUserInfo(Mockito.any())).thenReturn(userInfoModel);

        assertEquals(userInfoController.addUserInfo(2, userInfoDTO),toCompare);
    }

    @Test
    public void whenUserIsNotContainedUpdateUserInfoThrowsException() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);

        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(false);

        assertThrows(UserNotFoundException.class,
                () -> userInfoController.updateUserInfo(2, userInfoDTO));
    }

    @Test
    public void whenUserIsContainedUpdateUserInfoReturnsUpdatedUser() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);
        UserInfoModel userInfoModel = new UserInfoModel(userInfoDTO);
        userInfoModel.setGenderEnum(GenderEnum.FEMALE);
        UserInfoDTO toCompare = new UserInfoDTO(userInfoModel);

        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(true);
        Mockito.when(userInfoService.updateUserInfo(Mockito.any())).thenReturn(userInfoModel);

        assertEquals(userInfoController.updateUserInfo(2, userInfoDTO), toCompare);
    }

    @Test
    public void whenUserIsNotContainedGetUserInfoThrowsException() {
        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userInfoController.getUserInfo(2));
    }

    @Test
    public void whenUserInfoIsContainedGetUserInfoReturns() {
        UserInfoModel userInfoModel = new UserInfoModel("name", "lastname", 10L);
        userInfoModel.setGenderEnum(GenderEnum.FEMALE);
        UserInfoDTO toCompare = new UserInfoDTO(userInfoModel);

        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(true);
        Mockito.when(userInfoService.getUserInfoByUserId(Mockito.anyInt())).thenReturn(userInfoModel);

        assertEquals(userInfoController.getUserInfo(2), toCompare);
    }

}
