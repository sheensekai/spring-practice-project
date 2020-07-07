package com.example.model;

import com.example.GenderEnum;
import com.example.dto.GenderDTO;
import com.example.entities.Gender;
import com.example.exception.GenderEnumDoesntExistException;
import lombok.Data;

@Data
public class GenderModel {
    private int genderId;
    private GenderEnum gender;

    public GenderModel(String gender, int genderId) {
        this.gender = GenderEnum.findEnum(gender);
        if (this.gender == null) {
            throw new GenderEnumDoesntExistException("Enum for " + gender + " doesn't exist");
        }

        this.genderId = genderId;
    }

    public GenderModel(GenderDTO genderDTO) {
        this(genderDTO.getGender(), genderDTO.getGenderId());
    }

    public GenderModel(Gender gender) {
        this(gender.getGender(), gender.getGenderId());
    }
}
