package com.example.veterinary_clinic.dtos;

public record AppointmentRequestDTO(
        String date,
        String time,
        String reason,
        Long petId
) {
}
