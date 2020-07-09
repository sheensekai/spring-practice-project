package com.example.tests.services;

import com.example.entities.UserStatusName;
import com.example.exception.notfound.UserStatusNameNotFoundException;
import com.example.model.UserStatusNameModel;
import com.example.repository.UserStatusNameRepository;
import com.example.service.impl.UserStatusNameServiceImpl;
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

            assertTrue(userStatusNameService.existsByStatusId(i));
        }
    }

    @Test
    public void whenUserStatusNameIsNotContainedExistsByStatusIdReturnsFalse() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            Mockito.when(userStatusNameRepository.existsById(i)).thenReturn(false);

            assertFalse(userStatusNameService.existsByStatusId(i));
        }
    }

    @Test
    public void whenStatusNameIsContainedGetStatusByStatusNameReturnsFoundStatusName() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            UserStatusName userStatusName = userStatusNameList.get(i);
            UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(i);
            Optional<UserStatusName> optionalUserStatusName = Optional.of(userStatusName);

            Mockito.when(userStatusNameRepository.findByStatusName(userStatusName.getStatusName()))
                    .thenReturn(optionalUserStatusName);

            assertEquals(userStatusNameService
                    .getUserStatusNameByStatusName(userStatusName.getStatusName()), userStatusNameModel);
        }
    }

    @Test
    public void whenStatusNameIsNotContainedGetStatusByStatusNameThrowsException() {
        for (UserStatusName userStatusName : userStatusNameList) {
            Mockito.when(userStatusNameRepository.findByStatusName(userStatusName
                    .getStatusName())).thenThrow(UserStatusNameNotFoundException.class);

            assertThrows(UserStatusNameNotFoundException.class,
                    () -> userStatusNameService.getUserStatusNameByStatusName(userStatusName.getStatusName()));
        }
    }

    @Test
    public void whenStatusNameIsContainedGetStatusByStatusIdReturnsFoundStatusName() {
        for (int i = 0; i < userStatusNameList.size(); ++i) {
            UserStatusName userStatusName = userStatusNameList.get(i);
            UserStatusNameModel userStatusNameModel = userStatusNameModelList.get(i);
            Optional<UserStatusName> optionalUserStatusName = Optional.of(userStatusName);

            Mockito.when(userStatusNameRepository.findById(userStatusName.getStatusId())).thenReturn(optionalUserStatusName);

            assertEquals(userStatusNameService.getUserStatusNameByStatusId(userStatusName.getStatusId()), userStatusNameModel);
        }
    }

    @Test
    public void whenStatusNameIsNotContainedGetStatusByStatusIdThrowsException() {
        for (UserStatusName userStatusName : userStatusNameList) {
            Mockito.when(userStatusNameRepository.findById(userStatusName
                    .getStatusId())).thenThrow(UserStatusNameNotFoundException.class);

            assertThrows(UserStatusNameNotFoundException.class,
                    () -> userStatusNameService.getUserStatusNameByStatusId(userStatusName.getStatusId()));
        }
    }

    @Test
    public void getAllStatusesReturnsAllTheStatuses() {
        Mockito.when(userStatusNameRepository.findAll()).thenReturn(userStatusNameList);

        assertEquals(userStatusNameModelList, userStatusNameService.getAllUserStatusNames());
    }
}
