package com.example.controller;

import com.example.dto.GenderDTO;
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
    public int addGender(@RequestBody GenderDTO gender) {
        GenderModel newGender = new GenderModel(gender);
        return this.genderService.addGender(newGender);
    }
}