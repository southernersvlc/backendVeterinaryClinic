package com.example.veterinary_clinic.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppoimentRequestDTO(
        @NotNull(message = "The date cannot be null")
        @NotEmpty(message = "The date cannot be empty")
        LocalDate date,

        @NotNull(message = "The time cannot be null")
        @NotEmpty(message = "The time cannot be empty")
        @Future(message = "The date must not be earlier than the current date.")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalTime time,

        @NotNull(message = "The reason cannot be null")
        @NotEmpty(message = "The reason cannot be empty")
        String reason,

        @NotNull(message = "The pet id cannot be null")
        @NotEmpty(message = "The pet id cannot be empty")
        Long petId
) {


}
