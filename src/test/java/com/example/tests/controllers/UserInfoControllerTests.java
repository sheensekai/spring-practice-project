package com.example.tests.controllers;

import com.example.GenderEnum;
import com.example.controller.UserInfoController;
import com.example.dto.UserInfoDTO;
import com.example.exception.exists.ResourceAlreadyExistsException;
import com.example.exception.notfound.ResourceNotFoundException;
import com.example.model.UserInfoModel;
import com.example.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserInfoControllerTests {
    private static UserInfoServiceImpl userInfoService;
    private static UserInfoController userInfoController;

    @BeforeEach
    public void initBeforeEeach() {
        userInfoService = Mockito.mock(UserInfoServiceImpl.class);
        userInfoController = new UserInfoController(userInfoService);
    }

    @Test
    public void whenUserIsNotContainedAddUserInfoThrowsException() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);
        Mockito.when(userInfoService.addUserInfo(Mockito.any()))
                .thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class,
                () -> userInfoController.addUserInfo(2, userInfoDTO));
    }

    @Test
    public void whenUserInfoIsContainedAddUserInfoThrowsException() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);
        Mockito.when(userInfoService.addUserInfo(Mockito.any()))
                .thenThrow(ResourceAlreadyExistsException.class);
        assertThrows(ResourceAlreadyExistsException.class,
                () -> userInfoController.addUserInfo(2, userInfoDTO));
    }

    @Test
    public void whenUserIsContainedAndUserInfoIsNotContainedAddUserInfoReturnsAddedUserInfo() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);
        UserInfoModel userInfoModel = new UserInfoModel(userInfoDTO);
        userInfoModel.setGenderEnum(GenderEnum.UNKNOWN);
        UserInfoDTO toCompare = new UserInfoDTO(userInfoModel);
        Mockito.when(userInfoService.addUserInfo(Mockito.any()))
                .thenReturn(userInfoModel);

        assertEquals(userInfoController.addUserInfo(2, userInfoDTO),toCompare);
    }

    @Test
    public void whenUserIsNotContainedUpdateUserInfoThrowsException() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);
        Mockito.when(userInfoService.updateUserInfo(Mockito.any()))
                .thenThrow(ResourceNotFoundException.class);
        assertThrows(
                ResourceNotFoundException.class,
                () -> userInfoController.updateUserInfo(2, userInfoDTO));
    }

    @Test
    public void whenUserIsContainedUpdateUserInfoReturnsUpdatedUser() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("name", "lastName", "male", 10L);
        UserInfoModel userInfoModel = new UserInfoModel(userInfoDTO);
        userInfoModel.setGenderEnum(GenderEnum.FEMALE);
        UserInfoDTO toCompare = new UserInfoDTO(userInfoModel);
        Mockito.when(userInfoService.updateUserInfo(Mockito.any()))
                .thenReturn(userInfoModel);

        assertEquals(userInfoController.updateUserInfo(2, userInfoDTO), toCompare);
    }

    @Test
    public void whenUserIsNotContainedGetUserInfoThrowsException() {
        Mockito.when(userInfoService.getUserInfoByUserId(Mockito.anyInt()))
                .thenThrow(ResourceNotFoundException.class);
        assertThrows(
                ResourceNotFoundException.class,
                () -> userInfoController.getUserInfo(2));
    }

    @Test
    public void whenUserInfoIsContainedGetUserInfoReturns() {
        UserInfoModel userInfoModel = new UserInfoModel("name", "lastname", 10L);
        userInfoModel.setGenderEnum(GenderEnum.FEMALE);
        UserInfoDTO toCompare = new UserInfoDTO(userInfoModel);

        Mockito.when(userInfoService.getUserInfoByUserId(Mockito.anyInt()))
                .thenReturn(userInfoModel);

        assertEquals(userInfoController.getUserInfo(2), toCompare);
    }

}
