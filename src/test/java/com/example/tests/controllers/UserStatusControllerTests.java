package com.example.tests.controllers;

import com.example.UserStatusEnum;
import com.example.controller.UserStatusController;
import com.example.dto.UserStatusDTO;
import com.example.model.UserStatusModel;
import com.example.service.impl.UserStatusServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStatusControllerTests {
    private static UserStatusServiceImpl userStatusService;
    private static UserStatusController userStatusController;

    @BeforeEach
    public void initBeforeEach() {
        userStatusService = Mockito.mock(UserStatusServiceImpl.class);
        userStatusController = new UserStatusController(userStatusService);
    }

    @Test
    public void getStatisticsReturnsStatistics() {
        UserStatusModel userStatusModel = new UserStatusModel(1L, 2, UserStatusEnum.ONLINE, 50L);
        List<UserStatusDTO> toCompare = new ArrayList<>();
        toCompare.add(new UserStatusDTO(userStatusModel));

        Mockito.when(userStatusService.getStatistics(null, null, null))
                .thenReturn(Arrays.asList(userStatusModel));

        assertEquals(userStatusController.getStatistics("null", "null", "null"), toCompare);
    }
}
