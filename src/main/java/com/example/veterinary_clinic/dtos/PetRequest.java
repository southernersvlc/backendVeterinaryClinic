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
}
