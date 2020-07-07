package com.example;

public enum GenderEnum {
    MALE,
    FEMALE;

    public static GenderEnum findEnum(String name) {
        String toCheck = name.toLowerCase();
        for (GenderEnum gender : GenderEnum.values())  {
            if (toCheck.equals(gender.toString().toLowerCase())) {
                return gender;
            }
        }

        return null;
    }
}
