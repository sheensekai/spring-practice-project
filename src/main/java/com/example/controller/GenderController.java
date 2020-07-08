package com.example.controller;

import com.example.dto.GenderDTO;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.model.GenderModel;
import com.example.service.impl.GenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genders")
public class GenderController {
    @Autowired
    private GenderServiceImpl genderService;

    @PostMapping("/")
    public GenderDTO addGender(@RequestBody GenderDTO gender)
            throws ResourceAlreadyExistsException {
        GenderModel newGendreModel = new GenderModel(gender);
        newGendreModel = this.genderService.addGender(newGendreModel);
        return new GenderDTO(newGendreModel);
    }
}
