package com.example.dto;

import com.example.model.GenderModel;
import lombok.*;

@Data
@AllArgsConstructor
public class GenderDTO {
    private int genderId;

    private String genderName;

    public GenderDTO(String genderName) {
        this.genderName = genderName;
    }

    public GenderDTO(GenderModel genderModel) {
        this(genderModel.getGenderId(), genderModel.getGenderEnum().toString().toLowerCase());
    }
}
