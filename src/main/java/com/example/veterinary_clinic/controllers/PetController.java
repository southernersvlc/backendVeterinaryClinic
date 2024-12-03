package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.repositories.PetRepository;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.services.PetServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetRepository petRepository;
    private final GuardianRepository guardianRepository;
    private final PetServices petServices;

    public PetController(PetRepository petRepository, GuardianRepository guardianRepository) {
        this.petRepository = petRepository;
        this.guardianRepository = guardianRepository;
        this.petServices = petServices;
    }


    @GetMapping()
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            return new ResponseEntity<>(optionalPet.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("This Id does not belong to any pet.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addPet(@RequestBody Pet pet, @RequestParam Long guardianId) {

        Optional<Guardian> optionalGuardian = guardianRepository.findById(guardianId);

        if (!optionalGuardian.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The guardian does not exist with this id" + guardianId);
        }

        Guardian guardian = optionalGuardian.get();
        pet.setGuardian(guardian);
        Pet newPet = petRepository.save(pet);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPet);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable Long id, @RequestBody Pet pet) {

        Optional<Pet> optionalPeToUpdate = petRepository.findById(id);

        if (optionalPeToUpdate.isPresent()) {
            Pet petToUpdate = optionalPeToUpdate.get();
            petToUpdate.setName(pet.getName());
            petToUpdate.setAge(pet.getAge());
            petToUpdate.setBreed(pet.getBreed());
            petToUpdate.setSpecies(pet.getSpecies());

            petRepository.save(petToUpdate);

            return new ResponseEntity<>(petToUpdate, HttpStatus.OK);
        }

        return new ResponseEntity<>("This Id doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {

        Optional<Pet> optionalPetToDelete = petRepository.findById(id);

        if (optionalPetToDelete.isPresent()) {
            petRepository.deleteById(id);

            return new ResponseEntity<>("The pet has been deleted correctly", HttpStatus.OK);
        }

        return new ResponseEntity<>("Ei buddy! this Id does not exist, chill out bro!", HttpStatus.BAD_REQUEST);
    }
}



