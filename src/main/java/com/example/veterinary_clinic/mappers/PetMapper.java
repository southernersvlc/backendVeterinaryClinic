package com.example.veterinary_clinic.mappers;

import com.example.veterinary_clinic.dtos.PetRequestDTO;
import com.example.veterinary_clinic.dtos.PetResponseDTO;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.entities.Guardian;


public class PetMapper {
    public static Pet toEntity(PetRequestDTO petRequestDTO, Guardian guardian){
        return new Pet(
                petRequestDTO.name(),
                petRequestDTO.breed(),
                petRequestDTO.species(),
                petRequestDTO.age(),
                guardian);
    }

    public static PetResponseDTO toResponseDTO(Pet pet){
    return new PetResponseDTO(
            pet.getId(),
            pet.getName(),
            pet.getBreed(),
            pet.getSpecies(),
            pet.getAge(),
            pet.getGuardianId());
    }
}
