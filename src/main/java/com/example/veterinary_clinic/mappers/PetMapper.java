package com.example.veterinary_clinic.mappers;

import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.dtos.PetRequest;
import com.example.veterinary_clinic.dtos.PetResponse;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;

public class PetMapper {
    public static Pet toEntity(PetRequest petRequest, Guardian guardian) {
        return new Pet(petRequest.name(),
                petRequest.breed(),
                petRequest.species(),
                petRequest.age(),
                guardian);
    }

    public static PetResponse toResponse(Pet pet) {
        GuardianResponseDTO guardianResponseDTO = GuardianMapper.toResponseDto(pet.getGuardian());
        return new PetResponse(pet.getId(),
                pet.getName(),
                pet.getBreed(),
                pet.getSpecies(),
                pet.getAge(),
                guardianResponseDTO);
    }
}
