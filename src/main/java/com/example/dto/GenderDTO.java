package com.example.dto;

import com.example.model.GenderModel;
import lombok.Data;

@Data
public class GenderDTO {
    private String gender;

    public GenderDTO(String gender) {
        this.gender = gender;
    }

    public GenderDTO(GenderModel genderModel) {
        this.gender = genderModel.getGender().toString().toLowerCase();
    }
}
