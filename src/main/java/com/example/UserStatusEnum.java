package com.example;

public enum UserStatusEnum {
    ONLINE,
    OFFLINE,
    UNKNOWN;

    public static UserStatusEnum findEnum(String name) {
        if (name == null) {
            return null;
        }
        String toCheck = name.toLowerCase();
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values())  {
            if (toCheck.equals(userStatusEnum.toString().toLowerCase())) {
                return userStatusEnum;
            }
        }

        return null;
    }
}
