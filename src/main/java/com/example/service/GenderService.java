package com.example.service;

import com.example.entities.Gender;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.model.GenderModel;
import com.example.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderService {
    @Autowired
    private GenderRepository genderRepository;

    public int addGender(GenderModel gender) {
        if (this.existsGender(gender)) {
            throw new ResourceAlreadyExistsException("Gender " + gender.getGender().toString() + " already exists");
        }

        Gender newGender = new Gender(gender);
        return this.genderRepository.save(newGender).getId();
    }

    public boolean existsGender(GenderModel gender) {
        return this.genderRepository.existsByGender(gender.getGender().toString().toLowerCase());
    }
}
