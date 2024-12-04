package com.example.veterinary_clinic.dtos;

import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;

public record PetRequest(
        Long id, //need to check
        String name,
        String breed,
        Pet.Species species,
        String age,
        Guardian guardian //need to check guardian_id
) {

}
