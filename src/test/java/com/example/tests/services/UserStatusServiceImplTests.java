package com.example.tests.services;

import com.example.UserStatusEnum;
import com.example.entities.UserStatus;
import com.example.entities.UserStatusName;
import com.example.model.UserStatusModel;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusRepository;
import com.example.service.impl.UserServiceImpl;
import com.example.service.impl.UserStatusNameServiceImpl;
import com.example.service.impl.UserStatusServiceImpl;
import com.example.tests.BaseTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStatusServiceImplTests extends BaseTestClass {
    @BeforeEach
    public void initBeforeEach() {
        userStatusRepository = Mockito.mock(UserStatusRepository.class);
        userStatusNameService = Mockito.mock(UserStatusNameServiceImpl.class);
        userService = Mockito.mock(UserServiceImpl.class);
        userStatusService = new UserStatusServiceImpl
                (userStatusRepository, userStatusNameService, userService);
    }

    @Test
    public void whenAllArgsPassedGetStatisticsReturnsAllUserStatuses() {
        Mockito.when(userStatusRepository
                .findByUpdateTimeGreaterThan(Mockito.anyLong()))
                .thenReturn(userStatusList);
        Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
        userStatusService = new UserStatusServiceImpl
                (userStatusRepository, userStatusNameService, userService);

        assertEquals(
                userStatusService.getStatistics(null, null, null),
                userStatusModelList);
    }

    @Test
    public void whenUserIdOnlyPassedReturnsRightUserStatus() {
        for (int i = 0; i < userStatusModelList.size(); ++i) {
            Mockito.when(userStatusRepository
                    .findByUserIdAndUpdateTimeGreaterThan(Mockito.anyInt(), Mockito.anyLong()))
                    .thenReturn(new ArrayList<>(Arrays.asList(userStatusList.get(0))));
            Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
            userStatusService = new UserStatusServiceImpl
                    (userStatusRepository, userStatusNameService, userService);

            assertEquals(
                    userStatusService.getStatistics(i, null, null),
                    new ArrayList<>(Arrays.asList(userStatusModelList.get(0))));
        }
    }

    @Test
    public void whenStatusIdOnlyPassedReturnsRightUserStatus() {
        for (int i = 0; i < 3; ++i) {
            UserStatusName userStatusName = userStatusNameList.get(i);
            List<UserStatus> toCompare = new ArrayList<>();
            for (UserStatus userStatus : userStatusList) {
                if (userStatus.getStatusId() == userStatusName.getStatusId()) {
                    toCompare.add(userStatus);
                }
            }

            List<UserStatusModel> toCompareModel = new ArrayList<>();
            for (UserStatus userStatus : toCompare) {
                toCompareModel.add(new UserStatusModel(userStatus, UserStatusEnum.findEnum(userStatusName.getStatusName())));
            }

            Mockito.when(userStatusRepository
                    .findByStatusIdAndUpdateTimeGreaterThan(userStatusName.getStatusId(), Mockito.anyLong()))
                    .thenReturn(toCompare);
            Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
            userStatusService = new UserStatusServiceImpl
                    (userStatusRepository, userStatusNameService, userService);

            assertEquals(
                    userStatusService.getStatistics(i, null, null),
                    toCompareModel);
        }
    }
}
