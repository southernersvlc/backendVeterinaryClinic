package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.GuardianRequestDTO;
import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.exceptions.*;
import com.example.veterinary_clinic.mappers.GuardianMapper;
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
        String phoneNumberPattern = "^\\d{9}$";
        if (!Pattern.matches(phoneNumberPattern, guardianRequestDTO.phoneNumber())) {
            throw new VeterinaryInvalidPhoneNumberException("Phone number must have exactly 9 digits.");
        }

        Optional<Guardian> existGuardian = guardianRepository.findByPhoneNumber(guardianRequestDTO.phoneNumber());
        if (existGuardian.isPresent())
            throw new VeterinaryExistingPhoneNumberException("Guardian already exist with this phone number");

        Guardian guardian = GuardianMapper.toEntity(guardianRequestDTO);
        Guardian savedGuardian = guardianRepository.save(guardian);
        return GuardianMapper.toResponseDto(savedGuardian);
    }

    public List<Guardian> findAll() {
        List<Guardian> guardianList = guardianRepository.findAll();

        if(guardianList.isEmpty()) {
           throw new VeterinaryNotFoundException("There is no guardian to show");
        }
        return guardianList;
    }

    public GuardianResponseDTO findById(Long id) {
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);

        if(optionalGuardian.isEmpty()) {
            throw new VeterinaryNotFoundException("The guardian with id " + id + " does not exist.");
        }
        Guardian guardian = optionalGuardian.get();
        return GuardianMapper.toResponseDto(guardian);
    }


    public List<GuardianResponseDTO> findByNameIgnoreCaseContaining(String name) {
        List<GuardianResponseDTO> guardians = guardianRepository.findByNameIgnoreCaseContaining(name);

        if (guardians.isEmpty()) {
            throw new VeterinaryNotFoundException("The guardian with name " + name + " does not exist.");
        }
        return guardians;
    }

    public GuardianResponseDTO updateGuardianById(Long id, GuardianRequestDTO guardianRequestDTO) {
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);

        if(optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();

            guardian.setName(guardianRequestDTO.name());
            guardian.setPhoneNumber(guardianRequestDTO.phoneNumber());
            guardian.setEmail(guardianRequestDTO.email());
            guardian.setAddress(guardianRequestDTO.address());

            Guardian updatedGuardian = guardianRepository.save(guardian);
            return GuardianMapper.toResponseDto(updatedGuardian);
        }
        throw new VeterinaryNotFoundException("The guardian with id " + id + " does not exist.");
    }

    public void deleteGuardianById(Long id) {
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);

        if (optionalGuardian.isEmpty()) {
            throw new VeterinaryNotFoundException("The guardian with id " + id + " does not exist.");
        }
        guardianRepository.deleteById(id);
    }
 }
