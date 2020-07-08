package com.example.tests.controllers;

import com.example.GenderEnum;
import com.example.controller.GenderController;
import com.example.dto.GenderDTO;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.model.GenderModel;
import com.example.service.impl.GenderServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenderControllerTests {
    private static GenderServiceImpl genderService;
    private static GenderController genderController;
    @BeforeAll
    static void init() {
        genderService = Mockito.mock(GenderServiceImpl.class);
        genderController = new GenderController(genderService);
    }

    @Test
    public void whenGenderIsContainedAddGenderThrowsException() {
        GenderDTO genderDTO = new GenderDTO(GenderEnum.MALE.toString().toLowerCase());
        Mockito.when(genderService.addGender(Mockito.any()))
                .thenThrow(ResourceAlreadyExistsException.class);

        assertThrows(ResourceAlreadyExistsException.class,
                () -> genderController.addGender(genderDTO));
    }

    @Test
    public void whenGenderIsNotContainedAddGenderReturnsAddedGender() {
        GenderDTO genderDTO = new GenderDTO(GenderEnum.MALE.toString().toLowerCase());
        GenderModel genderModel = new GenderModel(genderDTO);
        Mockito.when(genderService.addGender(Mockito.any()))
                .thenReturn(genderModel);

        assertEquals(genderController.addGender(genderDTO), genderDTO);
    }

}
