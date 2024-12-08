package com.example.veterinary_clinic.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AppointmentRequestDTO(

        @NotNull(message = "The date cannot be null")
        @NotEmpty(message = "The date cannot be empty")
        String date,

        @NotNull(message = "The time cannot be null")
        @NotEmpty(message = "The time cannot be empty")
        String time,

        @NotNull(message = "The reason cannot be null")
        @NotEmpty(message = "The reason cannot be empty")
        String reason,

        @NotNull(message = "The pet ID cannot be null")
        Long petId
) {
}
