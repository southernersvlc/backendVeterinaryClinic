package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.exceptions.GuardianNotFoundExcepcion;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    public List<Guardian> findByNameIgnoreCaseContaining(String name) {
        List<Guardian> guardians = guardianRepository.findByNameIgnoreCaseContaining(name);

        if (guardians.isEmpty()) {
            throw new GuardianNotFoundExcepcion("The guardian with name " + name + " does not exist.");
        }
        return guardians;
    }

}
