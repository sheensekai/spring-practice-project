package com.example.model;

import com.example.GenderEnum;
import com.example.dto.GenderDTO;
import com.example.exception.GenderEnumDoesntExistException;
import lombok.Data;

@Data
public class GenderModel {
    private GenderEnum gender;

    public GenderModel(String gender) {
        this.gender = GenderEnum.findEnum(gender);
        if (this.gender == null) {
            throw new GenderEnumDoesntExistException("Enum for " + gender + " doesn't exist");
        }
    }

    public GenderModel(GenderDTO genderDTO) {
        this(genderDTO.getGender());
    }
}
