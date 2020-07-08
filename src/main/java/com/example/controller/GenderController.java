package com.example.controller;

import com.example.dto.GenderDTO;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.model.GenderModel;
import com.example.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genders")
public class GenderController {
    @Autowired
    private GenderService genderService;

    @PostMapping("/")
    public GenderDTO addGender(@RequestBody GenderDTO genderDTO)
            throws ResourceAlreadyExistsException {
        GenderModel newGenderModel = new GenderModel(genderDTO);
        newGenderModel = this.genderService.addGender(newGenderModel);
        return new GenderDTO(newGenderModel);
    }
}
