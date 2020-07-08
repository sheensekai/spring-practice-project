package com.example.tests.services;

import com.example.entities.UserStatusName;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusNameRepository;
import com.example.service.impl.UserStatusNameServiceImpl;
import com.example.tests.BaseTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserStatusNameServiceImplTests extends BaseTestClass {
    @BeforeEach
    public void initBeforeEach() {
        userStatusNameRepository = Mockito.mock(UserStatusNameRepository.class);
        userStatusNameService = new UserStatusNameServiceImpl(userStatusNameRepository);
    }

    @Test
    public void whenUserStatusNameIsContainedExistsByStatusIdReturnsTrue() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            Mockito.when(userStatusNameRepository.existsById(i)).thenReturn(true);
            userStatusNameService = new UserStatusNameServiceImpl(userStatusNameRepository);

            assertTrue(userStatusNameService.existsByStatusId(i));
        }
    }

    @Test
    public void whenUserStatusNameIsNotContainedExistsByStatusIdReturnsFalse() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            Mockito.when(userStatusNameRepository.existsById(i)).thenReturn(false);
            userStatusNameService = new UserStatusNameServiceImpl(userStatusNameRepository);

            assertFalse(userStatusNameService.existsByStatusId(i));
        }
    }

    @Test
    public void whenStatusNameIsContainedGetStatusByStatusNameReturnsFoundStatusName() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            UserStatusName userStatusName = userStatusNameList.get(i);
            UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(i);
            Optional<UserStatusName> optionalUserStatusName = Optional.of(userStatusName);

            Mockito.when(userStatusNameRepository.findByStatusName(userStatusName
                    .getStatusName())).thenReturn(optionalUserStatusName);
            userStatusNameService = new UserStatusNameServiceImpl(userStatusNameRepository);

            assertEquals(
                    userStatusNameService.getStatusByStatusName(userStatusName.getStatusName()),
                    userStatusNameModel);
        }
    }

    @Test
    public void whenStatusNameIsNotContainedGetStatusByStatusNameThrowsException() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            UserStatusName userStatusName = userStatusNameList.get(i);
            Mockito.when(userStatusNameRepository.findByStatusName(userStatusName
                    .getStatusName())).thenThrow(ResourceNotFoundException.class);
            userStatusNameService = new UserStatusNameServiceImpl(userStatusNameRepository);

            assertThrows(
                    ResourceNotFoundException.class,
                    () -> userStatusNameService.getStatusByStatusName(userStatusName.getStatusName()));
        }
    }

    @Test
    public void whenStatusNameIsContainedGetStatusByStatusIdReturnsFoundStatusName() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            UserStatusName userStatusName = userStatusNameList.get(i);
            UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(i);
            Optional<UserStatusName> optionalUserStatusName = Optional.of(userStatusName);

            Mockito.when(userStatusNameRepository.findById(userStatusName.getStatusId())).thenReturn(optionalUserStatusName);
            userStatusNameService = new UserStatusNameServiceImpl(userStatusNameRepository);

            assertEquals(
                    userStatusNameService.getStatusByStatusId(userStatusName.getStatusId()),
                    userStatusNameModel);
        }
    }

    @Test
    public void whenStatusNameIsNotContainedGetStatusByStatusIdThrowsException() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            UserStatusName userStatusName = userStatusNameList.get(i);
            Mockito.when(userStatusNameRepository.findById(userStatusName
                    .getStatusId())).thenThrow(ResourceNotFoundException.class);
            userStatusNameService = new UserStatusNameServiceImpl(userStatusNameRepository);

            assertThrows(
                    ResourceNotFoundException.class,
                    () -> userStatusNameService.getStatusByStatusId(userStatusName.getStatusId()));
        }
    }

    @Test
    public void getAllStatusesReturnsAllTheStatuses() {
        Mockito.when(userStatusNameRepository.findAll()).thenReturn(userStatusNameList);
        userStatusNameService = new UserStatusNameServiceImpl(userStatusNameRepository);

        assertEquals(
                userStatusNameModelList,
                userStatusNameService.getAllStatuses());
    }
}
