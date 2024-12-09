package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.dtos.PetRequestDTO;
import com.example.veterinary_clinic.dtos.PetResponseDTO;
import com.example.veterinary_clinic.exceptions.PetNotFoundException;
import com.example.veterinary_clinic.services.PetServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetServices petServices;

    public PetController(PetServices petServices) {
        this.petServices = petServices;
    }

    @GetMapping()
    public List<PetResponseDTO> getAllPets() {
        List<PetResponseDTO> allPets = petServices.listAllPets();
        return new ResponseEntity<>(allPets, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public PetResponseDTO getPetById(@PathVariable Long id) {
            PetResponseDTO petResponseDTO = petServices.showPetById(id);
        return new ResponseEntity<>(petResponseDTO, HttpStatus.OK).getBody();

    }

    @PostMapping
    public ResponseEntity<PetResponseDTO> addPet(@RequestBody @Valid PetRequestDTO petRequestDTO) {
        PetResponseDTO pet = petServices.createPet(petRequestDTO);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PetResponseDTO updatePet(@PathVariable Long id, @RequestBody @Valid PetRequestDTO petRequestDTO) {
        PetResponseDTO modifiedPet = petServices.modifyPet(id, petRequestDTO);
        return new ResponseEntity<>(modifiedPet, HttpStatus.OK).getBody();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {
        try {
            petServices.deletePetById(id);
            return new ResponseEntity<>("The pet has been deleted successfully.", HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}



