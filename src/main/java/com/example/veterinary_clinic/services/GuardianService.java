package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.GuardianRequestDTO;
import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.exceptions.GuardianFieldsCannotByEmptyException;
import com.example.veterinary_clinic.exceptions.GuardianInvalidPhoneNumberException;
import com.example.veterinary_clinic.exceptions.GuardianNotFoundException;
import com.example.veterinary_clinic.exceptions.GuardianPhoneNumberAlreadyExistsException;
import com.example.veterinary_clinic.mapper.GuardianMapper;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    public GuardianResponseDTO createGuardian(GuardianRequestDTO guardianRequestDTO) {

        if(guardianRequestDTO.name().isEmpty() || guardianRequestDTO.phoneNumber().isEmpty() || guardianRequestDTO.email().isEmpty() || guardianRequestDTO.address().isEmpty()) {
            throw new GuardianFieldsCannotByEmptyException("Fields cannot by empty");
        }

        String phoneNumberPattern = "^\\d{9}$";
        if (!Pattern.matches(phoneNumberPattern, guardianRequestDTO.phoneNumber())) {
            throw new GuardianInvalidPhoneNumberException("Phone number must have exactly 9 digits."); // IlegalArgument
        }

        Optional<Guardian> existGuardian = guardianRepository.findByPhoneNumber(guardianRequestDTO.phoneNumber());
        if (existGuardian.isPresent())
            throw new GuardianPhoneNumberAlreadyExistsException("Guardian already exist with this phone number");

        Guardian guardian = GuardianMapper.toEntity(guardianRequestDTO);
        Guardian savedGuardian = guardianRepository.save(guardian);
        return GuardianMapper.toResponseDto(savedGuardian);
    }

    public List<GuardianResponseDTO> findByNameIgnoreCaseContaining(String name) {
        List<GuardianResponseDTO> guardians = guardianRepository.findByNameIgnoreCaseContaining(name);

        if (guardians.isEmpty()) {
            throw new GuardianNotFoundException("The guardian with name " + name + " does not exist.");
        }
        return guardians;
    }

}
