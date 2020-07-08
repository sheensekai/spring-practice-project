package com.example.entities;

import com.example.model.GenderModel;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Genders")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int genderId;

    @NonNull
    @Column(name = "Gender", unique = true)
    private String genderName;

    public Gender(GenderModel genderModel) {
        this(genderModel.getGenderEnum().toString().toLowerCase());
    }
}
