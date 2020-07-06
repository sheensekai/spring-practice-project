package com.example.entities;

import com.example.model.GenderModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "genders")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "gender", unique = true)
    private String gender;

    public Gender(String gender) {
        this.gender = gender;
    }

    public Gender(GenderModel genderModel) {
        this.gender = genderModel.getGender();
    }
}
