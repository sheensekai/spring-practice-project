package com.example.entities;

import com.example.model.GenderModel;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genderId;

    @Column(unique = true)
    private String genderName;

    public Gender(GenderModel genderModel) {
        this(genderModel.getGenderId(), genderModel.getGenderEnum().toString().toLowerCase());
    }
}
