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
    private static List<String> wrongNames;

    @BeforeAll
    static void init() {
        rightNames = new ArrayList<>();
        rightNames.addAll(Arrays.asList("online", "ONLINE", "onLInE", "offline",
                "OFFLINE", "OFflINE", "unknown", "UNKNOWN", "unKNown"));

        wrongNames = new ArrayList<>();
        wrongNames.addAll(Arrays.asList("onlin", "line", "notonline", "ofline", "known", "notoffline"));
    }

    @Test
    public void findEnumReturnsEnumsWhenRightStatusNamesPassed() {
        for (String statusName : rightNames) {
            UserStatusEnum userStatusEnum = UserStatusEnum.findEnum(statusName);

            assertNotNull(userStatusEnum, "Couldn't findEnum for right status name " + statusName);
        }
    }

    @Test
    public void findEnumThrowsExceptionWhenWrongStatusNamesPassed() {
        for (String statusName : wrongNames) {
            UserStatusEnum userStatusEnum = UserStatusEnum.findEnum(statusName);

            assertNull(userStatusEnum, "Could findEnum for wrong status name " + statusName);
        }
    }
}
