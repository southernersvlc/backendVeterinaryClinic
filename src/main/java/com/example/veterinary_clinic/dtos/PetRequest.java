package com.example.veterinary_clinic.dtos;

import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PetRequest(

        @NotNull(message = "The name cannot be null")
        @NotEmpty(message = "The name cannot be empty")
        String name,

        @NotNull(message = "The breed cannot be null")
        @NotEmpty(message = "The breed cannot be empty")
        String breed,

        @NotNull(message = "The specie cannot be null")
        @NotEmpty(message = "The specie cannot be empty")
        String species,

        @NotNull(message = "The age cannot be null")
        @NotEmpty(message = "The age cannot be empty")
        String age,

        @NotNull(message = "The guardian ID cannot be null")
        Long guardianId
) {
}
