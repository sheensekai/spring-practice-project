package com.example.model;

import com.example.dto.GenderDTO;
import com.example.entities.Gender;
import com.example.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenderModel {
    private String gender;

    public GenderModel(String gender) {
        this.gender = gender;
    }

    public GenderModel(Gender gender) {
        this.gender = gender.getGender();
    }

    public GenderModel(GenderDTO genderDTO) {
        this.gender = genderDTO.getGender();
    }
}
