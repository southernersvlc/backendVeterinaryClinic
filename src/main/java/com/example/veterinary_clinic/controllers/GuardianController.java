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
    private final GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @PostMapping
    public ResponseEntity<GuardianResponseDTO> addGuardian(@RequestBody @Valid GuardianRequestDTO guardianRequestDTO) {
        GuardianResponseDTO guardianResponseDTO = guardianService.createGuardian(guardianRequestDTO);
        return new ResponseEntity<>(guardianResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public List<GuardianResponseDTO> getAllGuardians() {
        List<GuardianResponseDTO> guardianList = guardianService.findAll();
        return new ResponseEntity<>(guardianList, HttpStatus.OK).getBody();
    }

    @GetMapping("/id/{id}")
    public GuardianResponseDTO getGuardianById(@PathVariable Long id) {
        GuardianResponseDTO guardianResponseDTO = guardianService.findById(id);
        return new ResponseEntity<>(guardianResponseDTO, HttpStatus.OK).getBody();
    }

    @GetMapping("/name")
    public List<GuardianResponseDTO> getGuardianByName(@RequestParam String name) {
        List<GuardianResponseDTO> guardians = guardianService.findByNameIgnoreCaseContaining(name);
        return new ResponseEntity<>(guardians, HttpStatus.OK).getBody();
    }

   @PutMapping("/{id}")
    public GuardianResponseDTO updateGuardian(@PathVariable Long id, @RequestBody @Valid GuardianRequestDTO guardianRequestDTO) {
       GuardianResponseDTO guardianResponseDTO = guardianService.updateGuardianById(id, guardianRequestDTO);
       return new ResponseEntity<>(guardianResponseDTO, HttpStatus.OK).getBody();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuardian(@PathVariable Long id) {
        guardianService.deleteGuardianById(id);
        return new ResponseEntity<>("The guardian has been eliminated", HttpStatus.OK);
    }
}



