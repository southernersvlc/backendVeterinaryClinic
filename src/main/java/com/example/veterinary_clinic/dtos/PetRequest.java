package com.example.veterinary_clinic.dtos;

import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;

public record PetRequest(
        String name,
        String breed,
        String species,
        String age,
        Long guardianId
) {
    //Validations should be here with a constructor method
    //could be manual validation methods or @Validator
}
