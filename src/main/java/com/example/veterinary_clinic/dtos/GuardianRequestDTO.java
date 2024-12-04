package com.example.veterinary_clinic.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record GuardianRequestDTO(

        @NotBlank(message = "El nombre no puede estar vacío")
        String name,

        @NotBlank(message = "El Número de teléfono no puede estar vacío")
        @Pattern(regexp = "^\\d{9}$", message = "El teléfono debe tener exactamente 9 dígitos")
        String phoneNumber,

        @NotBlank(message = "La dirección no puede estar vacía")
        String address,

        @NotBlank(message = "El email no puede estar vacío")
        String email
) {
}
