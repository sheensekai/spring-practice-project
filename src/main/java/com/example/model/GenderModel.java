package com.example.model;

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
}
