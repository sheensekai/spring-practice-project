package com.example.service;

import com.example.entities.Gender;
import com.example.model.GenderModel;
import com.example.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderService {
    @Autowired
    private GenderRepository genderRepository;

    public int addGender(GenderModel gender) {
        Gender newGender = new Gender(gender);
        return this.genderRepository.save(newGender).getId();
    }

    public boolean exists(GenderModel gender) {
        return this.genderRepository.existsByGender(gender.getGender().toString().toLowerCase());
    }
}
