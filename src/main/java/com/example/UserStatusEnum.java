package com.example;

public enum UserStatusEnum {
    ONLINE,
    OFFLINE;

    public static UserStatusEnum findEnum(String name) {
        String toCheck = name.toLowerCase();
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values())  {
            if (toCheck.equals(userStatusEnum.toString().toLowerCase())) {
                return userStatusEnum;
            }
        }

        return null;
    }
}
