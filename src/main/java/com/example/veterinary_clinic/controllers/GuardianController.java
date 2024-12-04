package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.dtos.GuardianRequestDTO;
import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.services.GuardianService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guardians")
public class GuardianController {
    private final GuardianRepository guardianRepository;
    private final GuardianService guardianService;

    public GuardianController(GuardianRepository guardianRepository, GuardianService guardianService) {
        this.guardianRepository = guardianRepository;
        this.guardianService = guardianService;
    }

    @PostMapping
    public ResponseEntity<GuardianResponseDTO> addGuardian(@Valid @RequestBody GuardianRequestDTO guardianRequestDTO) {
        GuardianResponseDTO guardianResponseDTO = guardianService.createGuardian(guardianRequestDTO);
        return new ResponseEntity<>(guardianResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Guardian> getAllGuardians() {
        return guardianRepository.findAll();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getGuardianById(@PathVariable Long id) {
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);

        if (optionalGuardian.isPresent()) {
            return new ResponseEntity<>(optionalGuardian.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("This Id does not belong to any guardian.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getGuardianByName(@RequestParam String name) {
        List<Guardian> guardians = guardianService.findByNameIgnoreCaseContaining(name);
        return new ResponseEntity<>(guardians, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGuardian(@PathVariable Long id, @RequestBody Guardian guardian) {
        Optional<Guardian> optionalGuardianToUpdate = guardianRepository.findById(id);

        if (optionalGuardianToUpdate.isPresent()) {
            Guardian guardianToUpdate = optionalGuardianToUpdate.get();
            guardianToUpdate.setName(guardian.getName());
            guardianToUpdate.setPhoneNumber(guardian.getPhoneNumber());

            guardianRepository.save(guardianToUpdate);

            return new ResponseEntity<>(guardianToUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("This Id doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGuardian(@PathVariable Long id) {
        Optional<Guardian> optionalGuardianToDelete = guardianRepository.findById(id);

        if (optionalGuardianToDelete.isPresent()) {
            guardianRepository.deleteById(id);
            return new ResponseEntity<>("The Guardian has been deleted correctly.", HttpStatus.OK);
        }
        return new ResponseEntity<>("This Id doesn't exist.", HttpStatus.BAD_REQUEST);
    }

}



