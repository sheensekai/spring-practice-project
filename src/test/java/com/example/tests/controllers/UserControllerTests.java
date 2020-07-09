package com.example.tests.controllers;

import com.example.UserStatusEnum;
import com.example.controller.UserController;
import com.example.dto.UserDTO;
import com.example.dto.UserStatusDTO;
import com.example.exception.exists.ResourceAlreadyExistsException;
import com.example.exception.notfound.ResourceNotFoundException;
import com.example.exception.notfound.UserNotFoundException;
import com.example.model.UserModel;
import com.example.model.UserStatusModel;
import com.example.service.UserStatusService;
import com.example.service.impl.UserServiceImpl;
import com.example.service.impl.UserStatusServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserControllerTests {
    private static UserServiceImpl userService;
    private static UserStatusService userStatusService;
    private static UserController userController;

    @BeforeEach
    public void init() {
        userService = Mockito.mock(UserServiceImpl.class);
        userStatusService = Mockito.mock(UserStatusServiceImpl.class);
        userController = new UserController(userService, userStatusService);
    }

    @Test
    public void whenUserIsContainedAddUserThrowsException() {
        UserDTO userDTO = new UserDTO("name", "email", 1L);

        Mockito.when(userService.addUser(Mockito.any())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userController.addUser(userDTO));
    }

    @Test
    public void whenUserIsNotContainedAddUserReturnsAddeduser() {
        UserDTO userDTO = new UserDTO("name", "email", 1L);
        UserModel userModel = new UserModel(userDTO);

        userModel.setUserId(7);
        userModel.setStatusId(7);
        userModel.setUpdatetime(7L);
        UserDTO toCompare = new UserDTO(userModel);

        Mockito.when(userService.addUser(Mockito.any())).thenReturn(userModel);

        assertEquals(userController.addUser(userDTO), toCompare);
    }

    @Test
    public void whenUserIsNotContainedUpdateUserOnlineStatusThrowsException() {
        Mockito.when(userStatusService.updateUserStatus(Mockito.anyInt(), Mockito.any()))
                .thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userController.updateUserOnlineStatus(
                        0, UserStatusEnum.ONLINE.toString().toLowerCase()));
    }

    @Test
    public void whenUserIsContainedUpdateUserOnlineStatusReturnsUpdatedStatus() {
        UserStatusModel userStatusModel = new UserStatusModel(
                1L, 2, UserStatusEnum.ONLINE, 10L );
        UserStatusDTO toCompare = new UserStatusDTO(userStatusModel);

        Mockito.when(userStatusService.updateUserStatus(Mockito.anyInt(), Mockito.any()))
                .thenReturn(userStatusModel);

        assertEquals(userController.updateUserOnlineStatus(
                2, "online"), toCompare);
    }

    @Test
    public void whenUserIsNotContainedGetUserByIdThrowsException() {
        Mockito.when(userService.getUserByUserId(Mockito.anyInt()))
                .thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                () -> userController.getUserById(1));
    }

    @Test
    public void whenUserIsContainedGetUserByIdReturnsFoundUser() {
        UserModel userModel = new UserModel("name", "email", 20L);
        UserDTO toCompare = new UserDTO(userModel);

        Mockito.when(userService.getUserByUserId(Mockito.anyInt()))
                .thenReturn(userModel);

        assertEquals(userController.getUserById(0), toCompare);
    }
}
