package com.example.model;

import com.example.GenderEnum;
import com.example.dto.GenderDTO;
import com.example.entities.Gender;
import com.example.exception.GenderEnumDoesntExistException;
import lombok.Data;

@Data
public class GenderModel {
    private int genderId;
    private GenderEnum genderEnum;

    public GenderModel(String gender, int genderId) {
        this.genderEnum = GenderEnum.findEnum(gender);
        if (this.genderEnum == null) {
            throw new GenderEnumDoesntExistException("Enum for " + gender + " doesn't exist");
        }

        this.genderId = genderId;
    }

    public GenderModel(GenderDTO genderDTO) {
        this(genderDTO.getGenderName(), genderDTO.getGenderId());
    }

    public GenderModel(Gender gender) {
        this(gender.getGenderName(), gender.getGenderId());
    }
}
