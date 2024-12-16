/*
package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.GuardianRequestDTO;
import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.dtos.PetRequestDTO;
import com.example.veterinary_clinic.dtos.PetResponseDTO;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.mappers.GuardianMapper;
import com.example.veterinary_clinic.mappers.PetMapper;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PetServicesTest {

    @Mock
    GuardianRepository guardianRepository;

    @Mock
    PetRepository petRepository;

    @Test
    void should_createAValidPet() {
        //Given
        Guardian guardian = new Guardian(1L,"some-name", "123456789", "some-address", "some-email@email.com");
        PetRequestDTO petRequestDTO = new PetRequestDTO("Pepa", "", "DOG", "10", 1L);
        PetServices petServices = new PetServices(petRepository, guardianRepository);
        Pet savedPet = new Pet("Pepa", "", "DOG", "10", guardian);

        //Mock
        Guardian expectedGuardian = new Guardian(1L,"some-name", "123456789", "some-address", "some-email@email.com");
        Pet expectedPet = new Pet("Pepa", "unknown", "DOG", "10", expectedGuardian);
        PetResponseDTO expectedPetResponse = PetMapper.toResponse(expectedPet);
        Mockito.when(guardianRepository.findById(1L)).thenReturn(Optional.of(guardian));
        Mockito.when(petRepository.save(savedPet)).thenReturn(expectedPet);

        //When
        PetResponseDTO petResponseDTO = petServices.createPet(petRequestDTO);

        //Then
        verify(petRepository).save(savedPet);
        assertEquals(expectedPetResponse, petResponseDTO);
    }
}*/
