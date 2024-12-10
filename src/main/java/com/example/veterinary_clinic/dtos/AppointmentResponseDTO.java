package com.example.veterinary_clinic.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponseDTO(
        Long id,
        LocalDate date,
        LocalTime time,
        String reason,
        PetResponseDTO pet
) {
}
