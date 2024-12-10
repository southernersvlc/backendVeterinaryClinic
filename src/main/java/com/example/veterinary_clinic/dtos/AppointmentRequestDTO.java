package com.example.veterinary_clinic.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequestDTO(

        @NotNull(message = "the field date cannot be null")
        @Future(message = "The date cannot be previous to actual date")
        LocalDate date,

        @NotNull(message = "the field time cannot be null")
        LocalTime time,

        @NotNull(message = "the field reason cannot be null")
        @NotEmpty(message = "the field reason cannot be empty")
        String reason,

        @NotNull(message = "the field pet ID cannot be null")
        Long petId
) {
}
