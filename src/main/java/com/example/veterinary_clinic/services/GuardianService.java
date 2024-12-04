package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.GuardianRequestDTO;
import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.exceptions.GuardianNotFoundExcepcion;
import com.example.veterinary_clinic.exceptions.GuardianPhoneNumberAlreadyExistsException;
import com.example.veterinary_clinic.mapper.GuardianMapper;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    public GuardianResponseDTO createGuardian(GuardianRequestDTO guardianRequestDTO) {
        Guardian guardian = GuardianMapper.toEntity(guardianRequestDTO);

        Optional<Guardian> existGuardian = guardianRepository.findByPhoneNumber(guardian.getPhoneNumber());
        if (existGuardian.isPresent())
            throw new GuardianPhoneNumberAlreadyExistsException("Guardian already exist with this phone number");

        Guardian savedGuardian = guardianRepository.save(guardian);
        return GuardianMapper.toResponseDto(savedGuardian);
    }

    public List<Guardian> findByNameIgnoreCaseContaining(String name) {
        List<Guardian> guardians = guardianRepository.findByNameIgnoreCaseContaining(name);

        if (guardians.isEmpty()) {
            throw new GuardianNotFoundExcepcion("The guardian with name " + name + " does not exist.");
        }
        return guardians;
    }


}
