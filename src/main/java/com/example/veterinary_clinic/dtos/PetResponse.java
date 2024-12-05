package com.example.veterinary_clinic.dtos;

import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;

public record PetResponse(
        Long id,
        String name,
        String breed,
        String species,
        String age,
        GuardianResponseDTO guardian
) {
}
