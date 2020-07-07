package com.example.dto;

import com.example.model.GenderModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenderDTO {
    private int genderId;
    private String gender;

    public GenderDTO(GenderModel genderModel) {
        this.genderId = genderModel.getGenderId();
        this.gender = genderModel.getGender().toString().toLowerCase();
    }
}
