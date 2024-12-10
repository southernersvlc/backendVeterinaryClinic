package com.example.veterinary_clinic.dtos;

public record GuardianResponseDTO(
        Long id,
        String name,
        String phone,
        String address,
        String email
) {
}
