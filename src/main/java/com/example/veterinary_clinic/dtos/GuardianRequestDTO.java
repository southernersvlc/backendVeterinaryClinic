package com.example.veterinary_clinic.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record GuardianRequestDTO(
        String name,
        String phoneNumber,
        String address,
        String email
) {
}
