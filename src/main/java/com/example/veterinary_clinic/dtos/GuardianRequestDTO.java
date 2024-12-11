package com.example.veterinary_clinic.dtos;

import jakarta.validation.constraints.*;


public record GuardianRequestDTO(

        @NotNull(message = "The name cannot be null")
        @NotEmpty(message = "The name cannot be empty")
        String name,

        @NotBlank
        @Size(min = 9, max = 9, message = "The phone must be have 9 digits" )
        @Pattern(regexp = "^\\d{9}$", message = "The Phone must be have 9 digits")
        String phone,

        @NotNull(message = "The address cannot be null")
        @NotEmpty(message = "The address cannot be empty")
        String address,

        @NotNull(message = "The email cannot be null")
        @NotEmpty(message = "The email cannot be empty")
        @Email(message = "The email must be a correctly formatted address")
        String email
) {
}
