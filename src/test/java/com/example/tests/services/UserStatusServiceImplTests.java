package com.example.tests.services;

import com.example.UserStatusEnum;
import com.example.entities.UserStatus;
import com.example.entities.UserStatusName;
import com.example.exception.notfound.UserNotFoundException;
import com.example.model.UserStatusModel;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusRepository;
import com.example.service.impl.UserServiceImpl;
import com.example.service.impl.UserStatusNameServiceImpl;
import com.example.service.impl.UserStatusServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void whenAllArgsNotPassedGetStatisticsReturnsAllUserStatuses() {
        Mockito.when(userStatusRepository.findByUpdateTimeGreaterThan(Mockito.anyLong())).thenReturn(userStatusList);
        Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);

        assertEquals(userStatusService.getStatistics(null, null, null), userStatusModelList);
    }

    @Test
    public void whenAllArgsPassedGetStatisticsReturnsSuitableStatuses() {
        UserStatus userStatus = userStatusList.get(0);
        UserStatusModel userStatusModel = userStatusModelList.get(0);
        UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(0);

        Mockito.when(userStatusRepository
                .findByUserIdEqualsAndStatusIdEqualsAndUpdateTimeGreaterThan(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(Arrays.asList(userStatus));
        Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
        Mockito.when(userStatusNameService.getStatusByStatusName(Mockito.anyString()))
                .thenReturn(userStatusNameModelList.get(0));

        assertEquals(userStatusService.getStatistics(userStatus.getUserId(),
                        UserStatusEnum.findEnum(userStatusNameModel.getStatusName()), userStatus.getUpdateTime()),
                Arrays.asList(userStatusModel));
    }

    @Test
    public void whenUserIdOnlyPassedReturnsSuitableStatuses() {
        for (int i = 0; i < userStatusModelList.size(); ++i) {
            Mockito.when(userStatusRepository.findByUserIdAndUpdateTimeGreaterThan(Mockito.anyInt(), Mockito.anyLong()))
                    .thenReturn(new ArrayList<>(Arrays.asList(userStatusList.get(0))));
            Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);

            assertEquals(userStatusService.getStatistics(i, null, null),
                    new ArrayList<>(Arrays.asList(userStatusModelList.get(0))));
        }
    }

    @Test
    public void whenStatusIdOnlyPassedReturnsSuitableStatuses() {
        for (int i = 0; i < 3; ++i) {
            UserStatusName userStatusName = userStatusNameList.get(i);
            UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(i);

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

            Mockito.when(userStatusRepository.findByStatusIdAndUpdateTimeGreaterThan(
                            Mockito.anyInt(), Mockito.anyLong()))
                    .thenReturn(toCompare);
            Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
            Mockito.when(userStatusNameService.getStatusByStatusName(Mockito.anyString())).thenReturn(userStatusNameModel);

            assertEquals(userStatusService.getStatistics(null,
                            UserStatusEnum.findEnum(userStatusName.getStatusName()), null),
                    toCompareModel);
        }
    }

    @Test
    public void whenUpdateTimeOnlyPassedReturnsSuitableStatuses() {
        UserStatus userStatus = userStatusList.get(1);
        UserStatusModel userStatusModel = userStatusModelList.get(1);

        Mockito.when(userStatusRepository.findByUpdateTimeGreaterThan(userStatusModel.getUpdateTime()))
                .thenReturn(Arrays.asList(userStatus));
        Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
        Mockito.when(userStatusNameService.getStatusByStatusName(Mockito.anyString())).thenReturn(userStatusNameModelList.get(0));

        assertEquals(userStatusService.getStatistics(null, null, userStatusModel.getUpdateTime()),
                Arrays.asList(userStatusModel));
    }

    @Test
    public void whenUserIdAndStatusIdArePassedReturnsSuitableStatuses() {
        UserStatus userStatus = userStatusList.get(1);
        UserStatusModel userStatusModel = userStatusModelList.get(1);
        UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(0);

        Mockito.when(userStatusRepository.findByUserIdEqualsAndStatusIdEqualsAndUpdateTimeGreaterThan(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(Arrays.asList(userStatus));
        Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
        Mockito.when(userStatusNameService.getStatusByStatusName(Mockito.anyString()))
                .thenReturn(userStatusNameModelList.get(0));

        assertEquals(userStatusService.getStatistics(userStatus.getUserId(),
                        UserStatusEnum.findEnum(userStatusNameModel.getStatusName()), null),
                Arrays.asList(userStatusModel));
    }

    @Test
    public void whenUserIdAndUpdateTimeArePassedReturnsSuitableStatuses() {
        UserStatus userStatus = userStatusList.get(1);
        UserStatusModel userStatusModel = userStatusModelList.get(1);

        Mockito.when(userStatusRepository.findByUserIdAndUpdateTimeGreaterThan(Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(Arrays.asList(userStatus));
        Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
        Mockito.when(userStatusNameService.getStatusByStatusName(Mockito.anyString()))
                .thenReturn(userStatusNameModelList.get(0));

        assertEquals(userStatusService.getStatistics(userStatus.getUserId(), null,
                        userStatus.getUpdateTime()),
                Arrays.asList(userStatusModel));
    }

    @Test
    public void whenStatusIdAndUpdateTimeArePassedReturnsSuitableStatuses() {
        UserStatus userStatus = userStatusList.get(1);
        UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(0);
        UserStatusModel userStatusModel = userStatusModelList.get(1);

        Mockito.when(userStatusRepository.findByStatusIdAndUpdateTimeGreaterThan(Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(Arrays.asList(userStatus));
        Mockito.when(userStatusNameService.getAllStatuses()).thenReturn(userStatusNameModelList);
        Mockito.when(userStatusNameService.getStatusByStatusName(Mockito.anyString()))
                .thenReturn(userStatusNameModelList.get(0));

        assertEquals(userStatusService.getStatistics(null,
                        UserStatusEnum.findEnum(userStatusNameModel.getStatusName()), userStatus.getUpdateTime()),
                Arrays.asList(userStatusModel));
    }

    @Test
    public void whenUserIsNotContainedUpdateUserStatusThrowsException() {
        UserStatusModel userStatusModel = userStatusModelList.get(0);

        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userStatusService.updateUserStatus(
                userStatusModel.getUserId(),
                userStatusModel.getOnlineStatus()));
    }

    @Test
    public void whenUserIsContainedUpdateUserStatusReturnsUpdatedUserStatus() {
        UserStatusModel userStatusModel = userStatusModelList.get(0);
        UserStatus userStatus = userStatusList.get(0);
        UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(0);

        Mockito.when(userService.existsByUserId(Mockito.anyInt())).thenReturn(true);
        Mockito.when(userService.getUserByUserId(Mockito.anyInt())).thenReturn(userModelList.get(0));
        Mockito.when(userStatusRepository.save(Mockito.any())).thenReturn(userStatus);
        Mockito.when(userStatusNameService.getStatusByStatusName(Mockito.anyString())).thenReturn(userStatusNameModel);

        assertEquals(userStatusService.updateUserStatus(userStatusModel.getUserId(), userStatusModel.getOnlineStatus()),
                userStatusModel);
    }

}
