package com.example.veterinary_clinic.dtos;

public record StatisticsResponseDTO(
        Long appointments,
        Long pets,
        Long guardians
) {
}
