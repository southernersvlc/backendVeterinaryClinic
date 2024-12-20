package com.example.veterinary_clinic.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PetRequestDTO(

        @NotNull(message = "The name cannot be null")
        @NotEmpty(message = "The name cannot be empty")
        String name,

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
