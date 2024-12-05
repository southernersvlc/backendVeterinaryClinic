package com.example.veterinary_clinic.dtos;

public record GuardianResponseDTO(
        Long id,
        String name,
        String phoneNumber,
        String address,
        String email
) {
}
