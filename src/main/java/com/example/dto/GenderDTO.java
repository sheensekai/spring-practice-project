package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenderDTO {
    private String gender;

    public GenderDTO(String gender) {
        this.gender = gender;
    }
}
