package com.example.service.impl;

import com.example.entities.Gender;
import com.example.exception.exists.GenderAlreadyExistsException;
import com.example.exception.notfound.GenderNotFoundException;
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

    public GenderModel addGender(GenderModel genderModel)
        throws GenderAlreadyExistsException {
        if (this.existsGender(genderModel)) {
            throw new GenderAlreadyExistsException("Gender " + genderModel.getGenderEnum().toString() + " already exists");
        }

        Gender newGender = new Gender(genderModel);
        newGender = this.genderRepository.save(newGender);

        return new GenderModel(newGender);
    }

    public GenderModel findGenderByGender(String genderString)
        throws GenderNotFoundException {
        Gender gender = this.genderRepository.findByGenderName(genderString)
                .orElseThrow(() -> new GenderNotFoundException("Gender " + genderString + " doesn't exist"));

        return new GenderModel(gender);
    }

    public GenderModel findGenderByGenderId(int genderId)
        throws  GenderNotFoundException {
        Gender gender = this.genderRepository.findById(genderId)
                .orElseThrow(() -> new GenderNotFoundException("Gender with id " + genderId + " doesn't exist"));

        return new GenderModel(gender);
    }

    public boolean existsGender(GenderModel genderModel) {
        return this.genderRepository.existsByGenderName(genderModel.getGenderEnum().toString().toLowerCase());
    }
}
