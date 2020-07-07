package com.example;

public enum GenderEnum {
    UNKNOWN,
    MALE,
    FEMALE;

    public static GenderEnum findEnum(String name) {
        String toCheck = name.toLowerCase();
        for (GenderEnum genderEnum : GenderEnum.values())  {
            if (toCheck.equals(genderEnum.toString().toLowerCase())) {
                return genderEnum;
            }
        }

        return null;
    }
}
