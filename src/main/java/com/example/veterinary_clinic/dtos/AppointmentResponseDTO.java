package com.example.veterinary_clinic.dtos;

public record AppointmentResponseDTO(
        Long id,
        String date,
        String time,
        String reason,
        PetResponseDTO pet
) {
}
