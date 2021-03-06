package com.example.tests.services;

import com.example.entities.User;
import com.example.exception.exists.UserAlreadyExistsException;
import com.example.exception.notfound.UserNotFoundException;
import com.example.model.UserModel;
import com.example.repository.UserRepository;
import com.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTests extends BaseTestClass {

    @BeforeEach
    public void initBeforeEach() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void whenUserIsContainedExistsByUserIdReturnsTrue() {
        for (int i = 0; i < userList.size(); ++i) {
            Mockito.when(userRepository.existsById(i)).thenReturn(true);

            assertTrue(userService.existsByUserId(i));
        }
    }

    @Test
    public void whenUserIsNotContainedExistsByUserIdReturnsFalse() {
        for (int i = 0; i < userList.size(); ++i) {
            Mockito.when(userRepository.existsById(i)).thenReturn(false);

            assertFalse(userService.existsByUserId(i));
        }
    }

    @Test
    public void whenUserIsContainedContainsByUserNameReturnsTrue() {
        for (String userName : rightNameList) {
            Mockito.when(userRepository.existsByUserName(userName)).thenReturn(true);

            assertTrue(userService.containsByUserName(userName));
        }
    }

    @Test
    public void whenUserIsNotContainedContainsByUserNameReturnsFalse() {
        for (String userName : rightNameList) {
            Mockito.when(userRepository.existsByUserName(userName)).thenReturn(false);

            assertFalse(userService.containsByUserName(userName));
        }
    }

    @Test
    public void whenUserIsContainedContainsByEmailReturnsTrue() {
        for (String email : rightNameList) {
            Mockito.when(userRepository.existsByEmail(email)).thenReturn(true);

            assertTrue(userService.containsByEmail(email));
        }
    }

    @Test
    public void whenUserIsNotContainedContainsByEmailReturnsFalse() {
        for (String email : rightNameList) {
            Mockito.when(userRepository.existsByEmail(email)).thenReturn(false);

            assertFalse(userService.containsByEmail(email));
        }
    }

    @Test
    public void whenUserIsNotContainedUpdateUserThrowsException() {
        Mockito.when(userRepository.existsById(Mockito.anyInt())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userModelList.get(0)));
    }

    @Test
    public void whenUserIsContainedUpdateUserReturnsUpdatedUser() {
        for (int i = 0; i < userList.size(); ++i) {
            User user = userList.get(i);
            UserModel userModel = userModelList.get(i);

            Mockito.when(userRepository.existsById(Mockito.anyInt())).thenReturn(true);
            Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

            assertEquals(userService.updateUser(userModel), userModel);
        }
    }

    @Test
    public void whenUserIsContainedByEmailAddUserThrowsException() {
        Mockito.when(userRepository.existsByEmail(userList.get(0).getEmail())).thenReturn(true);
        Mockito.when(userRepository.existsByUserName(userList.get(0).getUserName())).thenReturn(false);

        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(userModelList.get(0)));
    }

    @Test
    public void whenUserIsContainedByUserNameAddUserThrowsException() {
        Mockito.when(userRepository.existsByUserName(userList.get(0).getUserName())).thenReturn(true);
        Mockito.when(userRepository.existsByEmail(userList.get(0).getEmail())).thenReturn(false);

        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(userModelList.get(0)));
    }

    @Test
    public void whenUserIsContainedByUserNameAndByEmailAddUserThrowsException() {
        Mockito.when(userRepository.existsByEmail(userList.get(0).getEmail())).thenReturn(true);
        Mockito.when(userRepository.existsByUserName(userList.get(0).getUserName())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(userModelList.get(0)));
    }

    @Test
    public void whenUserIsNotContainedAddUserReturnAddedUser() {
        for (int i = 0; i < userList.size(); ++i) {
            User user = userList.get(i);
            UserModel userModel = userModelList.get(i);

            Mockito.when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
            Mockito.when(userRepository.existsByUserName(user.getUserName())).thenReturn(false);
            Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

            assertEquals(userService.addUser(userModel), userModel);
        }
    }
}
