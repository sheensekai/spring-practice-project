package com.example.service;

import com.example.model.GenderModel;
import org.springframework.stereotype.Service;

@Service
public interface GenderService {
    GenderModel addGender(GenderModel genderModel);
    GenderModel findGenderByGender(String genderString);
    GenderModel findGenderByGenderId(int genderId);
    boolean existsGender(GenderModel genderModel);
}
