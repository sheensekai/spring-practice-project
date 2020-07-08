package com.example.service.impl;

import com.example.entities.Gender;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.GenderModel;
import com.example.repository.GenderRepository;
import com.example.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;

    public GenderServiceImpl(@Autowired GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public GenderModel addGender(GenderModel genderModel) {
        if (this.existsGender(genderModel)) {
            throw new ResourceAlreadyExistsException("Gender " + genderModel.getGender().toString() + " already exists");
        }

        Gender newGender = new Gender(genderModel);
        newGender = this.genderRepository.save(newGender);
        return new GenderModel(newGender);
    }

    public GenderModel findGenderByGender(String genderString) {
        Gender gender = this.genderRepository.findByGender(genderString)
                .orElseThrow(() -> new ResourceNotFoundException("Gender " + genderString + " doesn't exist"));

        return new GenderModel(gender);
    }

    public GenderModel findGenderByGenderId(int genderId) {
        Gender gender = this.genderRepository.findById(genderId)
                .orElseThrow(() -> new ResourceNotFoundException("Gender with id " + genderId + " doesn't exist"));

        return new GenderModel(gender);
    }

    public boolean existsGender(GenderModel genderModel) {
        return this.genderRepository.existsByGender(genderModel.getGender().toString().toLowerCase());
    }
}
