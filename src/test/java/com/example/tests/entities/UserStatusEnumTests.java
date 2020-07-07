package com.example.tests.entities;

import com.example.UserStatusEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserStatusEnumTests {
    private static List<String> rightNames;
    private static List<String> wrongName;

    @BeforeAll
    static void init() {
        rightNames = new ArrayList<>();
        rightNames.addAll(Arrays.asList("online", "ONLINE", "onLInE", "offline",
                "OFFLINE", "OFflINE", "unknown", "UNKNOWN", "unKNown"));

        wrongName = new ArrayList<>();
        wrongName.addAll(Arrays.asList("onlin", "line", "notonline", "ofline", "known", "notoffline"));
    }

    @Test
    public void testFindEnumRightStatusNames() {
        for (String statusName : rightNames) {
            UserStatusEnum genderEnum = UserStatusEnum.findEnum(statusName);
            assertNotNull(genderEnum, "Couldn't findEnum for right status name " + statusName);
        }
    }

    @Test
    public void testFindEnumWrongStatusNames() {
        for (String statusName : wrongName) {
            UserStatusEnum genderEnum = UserStatusEnum.findEnum(statusName);
            assertNull(genderEnum, "Could findEnum for wrong status name " + statusName);
        }
    }
}
