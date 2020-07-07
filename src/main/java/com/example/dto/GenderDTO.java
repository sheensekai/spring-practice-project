package com.example.dto;

import com.example.model.GenderModel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GenderDTO {
    private int genderId;

    @NonNull
    private String gender;

    public GenderDTO(GenderModel genderModel) {
        this(genderModel.getGender().toString().toLowerCase());
        this.genderId = genderModel.getGenderId();
    }
}
