package com.example.veterinary_clinic.mappers;

import com.example.veterinary_clinic.dtos.GuardianRequestDTO;
import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.entities.Guardian;

public class GuardianMapper {
    public static Guardian toEntity(GuardianRequestDTO guardianRequestDTO) {
        return new Guardian(
                guardianRequestDTO.name(),
                guardianRequestDTO.phone(),
                guardianRequestDTO.address(),
                guardianRequestDTO.email());
    }

    public static GuardianResponseDTO toResponseDto(Guardian guardian) {
        return new GuardianResponseDTO(
                guardian.getId(),
                guardian.getName(),
                guardian.getPhone(),
                guardian.getAddress(),
                guardian.getEmail());
    }
}
