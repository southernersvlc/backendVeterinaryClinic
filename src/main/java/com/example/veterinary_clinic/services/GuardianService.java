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
        Guardian guardian = GuardianMapper.toEntity(guardianRequestDTO);
        if(guardian.getName().isEmpty() || guardian.getPhoneNumber().isEmpty() || guardian.getEmail().isEmpty() || guardian.getAddress().isEmpty()) {
            throw new GuardianFieldsCannotByEmptyException("Fields cannot by empty");
        }

        String phoneNumberPattern = "^\\d{9}$";
        if (!Pattern.matches(phoneNumberPattern, guardian.getPhoneNumber())) {
            throw new GuardianInvalidPhoneNumberException("Phone number must have exactly 9 digits.");
        }

        Optional<Guardian> existGuardian = guardianRepository.findByPhoneNumber(guardian.getPhoneNumber());
        if (existGuardian.isPresent())
            throw new GuardianPhoneNumberAlreadyExistsException("Guardian already exist with this phone number");

        Guardian savedGuardian = guardianRepository.save(guardian);
        return GuardianMapper.toResponseDto(savedGuardian);
    }

    public List<Guardian> findByNameIgnoreCaseContaining(String name) {
        List<Guardian> guardians = guardianRepository.findByNameIgnoreCaseContaining(name);

        if (guardians.isEmpty()) {
            throw new GuardianNotFoundException("The guardian with name " + name + " does not exist.");
        }
        return guardians;
    }

}
