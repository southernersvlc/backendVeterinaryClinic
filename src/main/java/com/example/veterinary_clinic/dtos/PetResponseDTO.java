package com.example.veterinary_clinic.dtos;

public record PetResponseDTO(
        Long id,
        String name,
        String breed,
        String species,
        String age,
        Long guardianId
) {
}
