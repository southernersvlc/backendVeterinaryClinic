package com.example.veterinary_clinic.dtos;

import com.example.veterinary_clinic.entities.Guardian;

public record PetRequest(
        Long id, //need to check
        String name,
        String breed,
        String species,
        String age,
        Guardian guardian //need to check guardian_id
) {

}
