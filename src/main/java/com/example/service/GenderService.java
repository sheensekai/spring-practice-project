package com.example.service;

import com.example.entities.Gender;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.GenderModel;
import com.example.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderService {
    @Autowired
    private GenderRepository genderRepository;

    public GenderModel addGender(GenderModel gender) {
        if (this.existsGender(gender)) {
            throw new ResourceAlreadyExistsException("Gender " + gender.getGender().toString() + " already exists");
        }

        Gender newGender = new Gender(gender);
        newGender = this.genderRepository.save(newGender);
        return new GenderModel(newGender);
    }

    public GenderModel findGenderByGender(String genderString) {
        Gender gender = this.genderRepository.findByGender(genderString)
                .orElseThrow(() -> new ResourceNotFoundException("Gender " + genderString + " doesn't exist"));

        return new GenderModel(gender);
    }

    public GenderModel findGenderById(int genderId) {
        Gender gender = this.genderRepository.findById(genderId)
                .orElseThrow(() -> new ResourceNotFoundException("Gender with id " + genderId + " doesn't exist"));

        return new GenderModel(gender);
    }

    public boolean existsGender(GenderModel gender) {
        return this.genderRepository.existsByGender(gender.getGender().toString().toLowerCase());
    }
}
