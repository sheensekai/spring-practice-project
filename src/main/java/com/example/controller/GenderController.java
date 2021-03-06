package com.example.controller;

import com.example.dto.GenderDTO;
import com.example.exception.exists.GenderAlreadyExistsException;
import com.example.model.GenderModel;
import com.example.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genders")
public class GenderController {
    private final GenderService genderService;

    public GenderController(@Autowired GenderService genderService) {
        this.genderService = genderService;
    }

    @PostMapping("/addGender")
    public GenderDTO addGender(@RequestBody GenderDTO genderDTO)
            throws GenderAlreadyExistsException {
        GenderModel newGenderModel = new GenderModel(genderDTO);
        newGenderModel = this.genderService.addGender(newGenderModel);
        return new GenderDTO(newGenderModel);
    }
}
