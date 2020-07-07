package com.example.tests.entities;

import com.example.entities.User;
import com.example.entities.UserStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {

    @Test
    public void testUpdateState() {
        User user = new User();
        for (int statusId = 1; statusId <= 10; ++statusId) {
            UserStatus userStatus = new UserStatus(System.currentTimeMillis(), user.getUserId(), statusId);
            user.updateStatus(userStatus);
            assertEquals(user.getStatusId(), userStatus.getStatusId(),
                    "After updating status of user, user.getStatus() has incorrect value");
            assertEquals(user.getUpdatetime(), userStatus.getUpdateTime(),
                    "After updating status of user, user.getUpdatetime() has incorrect value");
        }
    }
}
