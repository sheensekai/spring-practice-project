package com.example.tests.services;

import com.example.entities.Gender;
import com.example.exception.ResourceNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GenderServiceImplTests {
    private GenderServiceImpl genderService;
    private static GenderRepository genderRepository;
    private static List<String> rightNames;
    private static List<String> wrongNames;
    private static List<GenderModel> genderModelList;
    private static List<Gender> genderList;

    @BeforeAll
    static void init() {
        rightNames = new ArrayList<>();
        rightNames.addAll(Arrays.asList("male", "MALE", "MalE", "female",
                "FEMALE", "FEmalE", "unknown", "UNKNOWN", "UnknoWN"));

        wrongNames = new ArrayList<>();
        wrongNames.addAll(Arrays.asList("notmale", "malemale", "fefefefemale", "notfemale", "asdfasdgasd",
                "femal", "mal", "email", "femalemale"));

        genderList = new ArrayList<>();
        for (int i = 0; i < rightNames.size(); ++i) {
            genderList.add(new Gender(i, rightNames.get(i)));
        }

        genderModelList = new ArrayList<>();
        for (int i = 0; i < rightNames.size(); ++i) {
            genderModelList.add(new GenderModel(genderList.get(i)));
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
            assertTrue(
                    this.genderService.existsGender(genderModel),
                    "Returns false while gender is contained in the repotisotry");
        }
    }

    @Test
    public void whenGenderDoesntExistReturnsFalse() {
        Mockito.when(genderRepository.existsByGender(Mockito.anyString())).thenReturn(false);
        genderService = new GenderServiceImpl(genderRepository);

        for (GenderModel genderModel : genderModelList) {
            assertFalse(
                    this.genderService.existsGender(genderModel),
                    "Returns true while gender is not contained in the repository");
        }
    }

    @Test
    public void whenGenderIsContainedByGenderIdReturnsGenderModel() {
        for (int i = 0; i < genderList.size(); ++i) {
            Optional<Gender> optionalGender = Optional.of(genderList.get(i));
            Mockito.when(genderRepository.findById(Mockito.anyInt())).thenReturn(optionalGender);
            genderService = new GenderServiceImpl(genderRepository);

            assertEquals(
                    this.genderService.findGenderByGenderId(i),
                    genderModelList.get(i),
                    "Returned genderModel must be equal because they are made of the same Gender");
        }
    }

    @Test
    public void whenGenderIsNotContainedByGenderNameThrowsException() {
            Mockito.when(genderRepository.findByGender(Mockito.anyString())).thenThrow(new ResourceNotFoundException(""));
            genderService = new GenderServiceImpl(genderRepository);
            assertThrows(ResourceNotFoundException.class, () -> genderService.findGenderByGender(rightNames.get(0)));
    }

    @Test
    public void whenGenderIsContainedByGenderNameReturnsGenderModel() {
        for (int i = 0; i < genderList.size(); ++i) {
            Optional<Gender> optionalGender = Optional.of(genderList.get(i));
            Mockito.when(genderRepository.findByGender(Mockito.anyString())).thenReturn(optionalGender);
            genderService = new GenderServiceImpl(genderRepository);

            assertEquals(
                    this.genderService.findGenderByGender(genderList.get(i).getGenderName()),
                    genderModelList.get(i),
                    "Returned genderModel must be equal because they are made of the same Gender");
        }
    }

    @Test
    public void whenAddsNotContainedGenderReturnsNewMadeGender() {
        for (int i = 0; i < genderList.size(); ++i) {
            Mockito.when(genderRepository.save(Mockito.any())).thenReturn(genderList.get(i));
            genderService = new GenderServiceImpl(genderRepository);

            assertEquals(
                    this.genderService.addGender(genderModelList.get(i)),
                    new GenderModel(genderList.get(i)),
                    "Returned genderModel must be equals because they are made of the same Gender");
        }
    }

}
