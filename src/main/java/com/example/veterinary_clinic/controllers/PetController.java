package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.dtos.PetRequest;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.PetNotFoundException;
import com.example.veterinary_clinic.repositories.PetRepository;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.services.PetServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetRepository petRepository;
    private final GuardianRepository guardianRepository;
    private final PetServices petServices;

    public PetController(PetRepository petRepository, GuardianRepository guardianRepository, PetServices petServices) {
        this.petRepository = petRepository;
        this.guardianRepository = guardianRepository;
        this.petServices = petServices;
    }


    @GetMapping()
    public List<Pet> getAllPets() {
        List<Pet> allPets = petServices.listAllPets();
        return allPets;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Long id) {
        try {
            Pet pet = petServices.showPetById(id);
            return new ResponseEntity<>(pet, HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody PetRequest petRequest) {//@RequestParam Long guardianId
        Pet pet = petServices.createPet(petRequest);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable Long id, @RequestBody PetRequest petRequest) {
        try {
            Pet modifiedPet = petServices.modifyPet(id, petRequest);
            return new ResponseEntity<>(modifiedPet, HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {
        try {
            petServices.killPet(id);
            return new ResponseEntity<>("The pet has been deleted successfully.", HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}



