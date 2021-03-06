package com.example.tests.services;

import com.example.entities.UserInfo;
import com.example.exception.exists.UserInfoAlreadyExistsException;
import com.example.exception.notfound.UserInfoNotFoundException;
import com.example.model.GenderModel;
import com.example.model.UserInfoModel;
import com.example.repository.UserInfoRepository;
import com.example.service.impl.GenderServiceImpl;
import com.example.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserInfoServiceImplTests extends BaseTestClass {

    @BeforeEach
    public void initBeforeEach() {
        userInfoRepository = Mockito.mock(UserInfoRepository.class);
        genderService = Mockito.mock(GenderServiceImpl.class);
        userInfoService = new UserInfoServiceImpl(userInfoRepository, genderService);
    }

    @Test
    public void whenUserInfoIsContainedGetUserInfoByIdReturnsTheUser() {
        for (int i = 0; i < userInfoList.size(); ++i) {
            UserInfo userInfo = userInfoList.get(i);
            UserInfoModel userInfoModel = userInfoModelList.get(i);

            Optional<UserInfo> optionalUserInfo = Optional.of(userInfo);
            GenderModel genderModel = new GenderModel(
                    userInfoModel.getGenderEnum().toString().toLowerCase(), userInfo.getGenderId());

            Mockito.when(userInfoRepository.findById(Mockito.anyInt())).thenReturn(optionalUserInfo);
            Mockito.when(genderService.findGenderByGenderId(i)).thenReturn(genderModel);

            assertEquals(userInfoService.getUserInfoByUserId(i), userInfoModel,
                    "Returned userInfoModel must be equal because they are made of the same UserInfo");
        }
    }

    @Test
    public void whenUserInfoIsNotContainedGetUserInfoByIdThrowsException() {
        Mockito.when(userInfoRepository.findById(Mockito.anyInt())).thenThrow(UserInfoNotFoundException.class);

        assertThrows(UserInfoNotFoundException.class,
                () -> userInfoService.getUserInfoByUserId(userInfoList.get(0).getUserId()));
    }

    @Test
    public void whenUserInfoIsNotContainedAddUserInfoReturnsNewUser() {
        for (int i = 0; i < userInfoList.size(); ++i) {
            UserInfo userInfo = userInfoList.get(i);
            UserInfoModel userInfoModel = userInfoModelList.get(i);

            GenderModel genderModel = new GenderModel
                    (userInfoModel.getGenderEnum().toString().toLowerCase(),
                            userInfo.getGenderId());

            Mockito.when(userInfoRepository.existsById(Mockito.anyInt())).thenReturn(false);
            Mockito.when(userInfoRepository.save(Mockito.any())).thenReturn(userInfo);
            Mockito.when(genderService.findGenderByGender(Mockito.anyString())).thenReturn(genderModel);

            assertEquals(userInfoService.addUserInfo(userInfoModel), userInfoModel,
                    "Returned userInfoModel must be equal because they are made of the same UserInfo");
        }
    }

    @Test
    public void whenUserInfoIsContainedAddUserInfoThrowsException() {
        Mockito.when(userInfoRepository.findById(Mockito.anyInt())).thenThrow(UserInfoAlreadyExistsException.class);

        assertThrows(UserInfoAlreadyExistsException.class, () ->
                userInfoService.getUserInfoByUserId(userInfoList.get(0).getUserId()));
    }

    @Test
    public void whenUserIsContainedUpdateUserInfoReturnsUpdatedUser() {
        for (int i = 0; i < userInfoList.size(); ++i) {
            UserInfo userInfo = userInfoList.get(i);
            UserInfoModel userInfoModel = userInfoModelList.get(i);

            GenderModel genderModel = new GenderModel(
                    userInfoModel.getGenderEnum().toString().toLowerCase(), userInfo.getGenderId());

            Mockito.when(userInfoRepository.existsById(Mockito.anyInt())).thenReturn(true);
            Mockito.when(genderService.findGenderByGender(Mockito.anyString())).thenReturn(genderModel);
            Mockito.when(userInfoRepository.save(Mockito.any())).thenReturn(userInfo);

            assertEquals(userInfoService.updateUserInfo(userInfoModel), userInfoModel,
                    "Returned userInfoModel must be equal because they are made of the same UserInfo");
        }
    }

    @Test
    public void whenUserInfoIsNotContainedUpdateUserInfoThrowsException() {
        Mockito.when(userInfoRepository.existsById(Mockito.anyInt())).thenReturn(false);

        assertThrows(UserInfoNotFoundException.class, () -> userInfoService.updateUserInfo(userInfoModelList.get(0)));
    }
}
