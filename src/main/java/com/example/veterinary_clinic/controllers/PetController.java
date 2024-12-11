package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.dtos.PetRequestDTO;
import com.example.veterinary_clinic.dtos.PetResponseDTO;
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
    public ResponseEntity<List<PetResponseDTO>> getAllPets() {
        List<PetResponseDTO> allPets = petServices.listAllPets();
        return new ResponseEntity<>(allPets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> getPetById(@PathVariable Long id) {
            PetResponseDTO petResponseDTO = petServices.showPetById(id);
        return new ResponseEntity<>(petResponseDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<PetResponseDTO> addPet(@RequestBody @Valid PetRequestDTO petRequestDTO) {
        PetResponseDTO pet = petServices.createPet(petRequestDTO);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDTO> updatePet(@PathVariable Long id, @RequestBody @Valid PetRequestDTO petRequestDTO) {
        PetResponseDTO modifiedPet = petServices.modifyPet(id, petRequestDTO);
        return new ResponseEntity<>(modifiedPet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id) {
            petServices.deletePetById(id);
            return new ResponseEntity<>("The pet has been deleted successfully.", HttpStatus.OK);
    }
}



