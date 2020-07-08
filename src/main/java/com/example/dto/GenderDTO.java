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
    private String genderName;

    public GenderDTO(GenderModel genderModel) {
        this(genderModel.getGenderEnum().toString().toLowerCase());
        this.genderId = genderModel.getGenderId();
    }
}
