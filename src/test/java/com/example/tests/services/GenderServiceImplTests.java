package com.example.tests.services;

import com.example.model.GenderModel;
import com.example.repository.GenderRepository;
import com.example.service.impl.GenderServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenderServiceImplTests {
    private GenderServiceImpl genderService;
    private static GenderRepository genderRepository;
    private static List<String> rightNames;
    private static List<String> wrongNames;
    private static List<GenderModel> genderModelList;

    @BeforeAll
    static void init() {
        rightNames = new ArrayList<>();
        rightNames.addAll(Arrays.asList("male", "MALE", "MalE", "female",
                "FEMALE", "FEmalE", "unknown", "UNKNOWN", "UnknoWN"));

        wrongNames = new ArrayList<>();
        wrongNames.addAll(Arrays.asList("notmale", "malemale", "fefefefemale", "notfemale", "asdfasdgasd",
                "femal", "mal", "email", "femalemale"));

        genderModelList = new ArrayList<>();
        for (int i = 0; i < rightNames.size(); ++i) {
            genderModelList.add(new GenderModel(rightNames.get(i), i + 1));
        }
    }

    @BeforeEach
    public void initBeforeEach() {
        genderRepository = Mockito.mock(GenderRepository.class);
    }

    @Test
    public void whenGenderAlreadyExistsReturnsTrue() {
        Mockito.when(genderRepository.existsByGender(Mockito.anyString())).thenReturn(true);
        genderService = new GenderServiceImpl(genderRepository);
        for (GenderModel genderModel : genderModelList) {
            assertTrue(this.genderService.existsGender(genderModel));
        }
    }

    @Test
    public void whenGenderDoesntExistReturnsFalse() {
        Mockito.when(genderRepository.existsByGender(Mockito.anyString())).thenReturn(false);
        genderService = new GenderServiceImpl(genderRepository);
        for (GenderModel genderModel : genderModelList) {
            assertFalse(this.genderService.existsGender(genderModel));
        }
    }

}
