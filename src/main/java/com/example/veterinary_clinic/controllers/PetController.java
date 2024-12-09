package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.dtos.PetRequest;
import com.example.veterinary_clinic.dtos.PetResponse;
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
    public List<PetResponse> getAllPets() {
        List<PetResponse> allPets = petServices.listAllPets();
        return new ResponseEntity<>(allPets, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public PetResponse getPetById(@PathVariable Long id) {
            PetResponse petResponse = petServices.showPetById(id);
        return new ResponseEntity<>(petResponse, HttpStatus.OK).getBody();

    }

    @PostMapping
    public ResponseEntity<PetResponse> addPet(@RequestBody @Valid PetRequest petRequest) {
        PetResponse pet = petServices.createPet(petRequest);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PetResponse updatePet(@PathVariable Long id, @RequestBody @Valid PetRequest petRequest) {
        PetResponse modifiedPet = petServices.modifyPet(id, petRequest);
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



