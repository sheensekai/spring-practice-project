package com.example.entities;

import com.example.model.GenderModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "genders")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int genderId;

    @NonNull
    @Column(name = "gender", unique = true)
    private String gender;

    public Gender(GenderModel genderModel) {
        this(genderModel.getGender().toString().toLowerCase());
    }
}
