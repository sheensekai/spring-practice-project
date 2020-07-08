package com.example.service;

import com.example.model.GenderModel;

public interface GenderService {
    GenderModel addGender(GenderModel genderModel);
    GenderModel findGenderByGender(String genderString);
    GenderModel findGenderByGenderId(int genderId);
    boolean existsGender(GenderModel genderModel);
}
