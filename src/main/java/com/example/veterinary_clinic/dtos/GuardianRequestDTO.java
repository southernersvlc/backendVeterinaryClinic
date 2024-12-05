package com.example.veterinary_clinic.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record GuardianRequestDTO(

        @NotNull(message = "The name cannot be null")
        @NotEmpty(message = "The name cannot be empty")
        String name,

        @NotNull(message = "The phone cannot be null")
        @NotEmpty(message = "The phone cannot be empty")
        String phoneNumber,

        @NotNull(message = "The email cannot be null")
        @NotEmpty(message = "The email cannot be empty")
        String address,

        @NotNull(message = "The address cannot be null")
        @NotEmpty(message = "The address cannot be empty")
        @Email
        String email
) {
}
