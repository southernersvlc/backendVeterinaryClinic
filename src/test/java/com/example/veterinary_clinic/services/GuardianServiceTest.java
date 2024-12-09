package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.GuardianRequestDTO;
import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.mappers.GuardianMapper;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GuardianServiceTest {

    @Mock
    GuardianRepository guardianRepository;

    @Test
    void should_createAValidGuardian() {
        //GIVEN
        GuardianRequestDTO guardianRequestDTO = new GuardianRequestDTO("some-name", "123456789", "some-address", "some-email@email.com");
        GuardianService guardianService = new GuardianService(guardianRepository);
        Guardian savedGuardian = new Guardian("some-name", "123456789", "some-address", "some-email@email.com");

        //MOCK
        Guardian expectedGuardian = new Guardian(1L,"some-name", "123456789", "some-address", "some-email@email.com");
        GuardianResponseDTO expectedGuardianResponse = GuardianMapper.toResponseDto(expectedGuardian);
        Mockito.when(guardianRepository.save(savedGuardian)).thenReturn(expectedGuardian);

        //WHEN
        GuardianResponseDTO guardianResponseDTO = guardianService.createGuardian(guardianRequestDTO);

        //THEN
        verify(guardianRepository).save(savedGuardian);
        assertEquals(expectedGuardianResponse, guardianResponseDTO);
    }


    @Test
    void findById() {
        //GIVEN
        Long expectedGuardianId = 1L;
        GuardianService guardianService = new GuardianService(guardianRepository);

        //MOCK
        Guardian expectedGuardian = new Guardian(expectedGuardianId,"some-name", "123456789", "some-address", "some-email@email.com");
        GuardianResponseDTO expectedGuardianResponse = GuardianMapper.toResponseDto(expectedGuardian);
        Mockito.when(guardianRepository.findById(expectedGuardianId)).thenReturn(Optional.of(expectedGuardian));

        //WHEN
        GuardianResponseDTO guardianResponseDTO = guardianService.findById(expectedGuardianId);

        //THEN
        verify(guardianRepository).findById(expectedGuardianId);
        assertEquals(expectedGuardianResponse, guardianResponseDTO);
    }


    @Test
    void should_findAGuardianByAValidName() {
        //GIVEN
        String expectedGuardianName = "some-name";
        GuardianService guardianService = new GuardianService(guardianRepository);

        //MOCK
        Guardian expectedGuardian = new Guardian(1L,expectedGuardianName, "123456789", "some-email@email.com", "some-address");
        GuardianResponseDTO expectedGuardianResponse = GuardianMapper.toResponseDto(expectedGuardian);
        List<GuardianResponseDTO> expectedResultList = Collections.singletonList(expectedGuardianResponse);
        Mockito.when(guardianRepository.findByNameIgnoreCaseContaining(expectedGuardianName)).thenReturn(expectedResultList);

        //WHEN
        List<GuardianResponseDTO> responseList = guardianService.findByNameIgnoreCaseContaining(expectedGuardianName);

        //THEN
        verify(guardianRepository).findByNameIgnoreCaseContaining(expectedGuardianName);
        assertEquals(expectedResultList, responseList);
    }
}