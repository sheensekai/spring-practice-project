package com.example.tests.entities;

import com.example.GenderEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GenderEnumTests {
    private static List<String> rightNames;
    private static List<String> wrongNames;

    @BeforeAll
    static void init() {
        rightNames = new ArrayList<>();
        rightNames.addAll(Arrays.asList("male", "MALE", "MalE", "female",
                "FEMALE", "FEmalE", "unknown", "UNKNOWN", "UnknoWN"));

        wrongNames = new ArrayList<>();
        wrongNames.addAll(Arrays.asList("notmale", "malemale", "fefefefemale", "notfemale", "asdfasdgasd",
                "femal", "mal", "email", "femalemale"));
    }

    @Test
    public void testFindEnumRightStatusNames() {
        for (String statusName : rightNames) {
            GenderEnum genderEnum = GenderEnum.findEnum(statusName);
            assertNotNull(genderEnum, "Couldn't findEnum for right status name " + statusName);
        }
    }

    @Test
    public void testFindEnumWrongStatusNames() {
        for (String statusName : wrongNames) {
            GenderEnum genderEnum = GenderEnum.findEnum(statusName);
            assertNull(genderEnum, "Could findEnum for wrong status name " + statusName);
        }
    }
}
