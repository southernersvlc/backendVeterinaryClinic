package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.dtos.GuardianRequestDTO;
import com.example.veterinary_clinic.dtos.GuardianResponseDTO;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.services.GuardianService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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


    @GetMapping("/{id}")
    public ResponseEntity<GuardianResponseDTO> getGuardianById(@PathVariable Long id) {
        GuardianResponseDTO guardianResponseDTO = guardianService.findById(id);
        return new ResponseEntity<>(guardianResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public List<GuardianResponseDTO> getGuardianByName(@PathParam("name") String name) {
        if (name == null) {
            return guardianService.findAll();
        }
        return guardianService.findByNameIgnoreCaseContaining(name);
    }

   @PutMapping("/{id}")
    public ResponseEntity<GuardianResponseDTO> updateGuardian(@PathVariable Long id, @RequestBody @Valid GuardianRequestDTO guardianRequestDTO) {
       GuardianResponseDTO guardianResponseDTO = guardianService.updateGuardianById(id, guardianRequestDTO);
       return new ResponseEntity<>(guardianResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuardian(@PathVariable Long id) {
        guardianService.deleteGuardianById(id);
        return new ResponseEntity<>("The guardian has been eliminated", HttpStatus.OK);
    }
}



