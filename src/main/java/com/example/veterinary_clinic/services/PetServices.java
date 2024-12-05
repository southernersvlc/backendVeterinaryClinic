package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.PetRequest;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.PetNotFoundException;
import com.example.veterinary_clinic.mappers.PetMapper;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.plugins.jpeg.JPEGQTable;
import java.util.List;
import java.util.Optional;

@Service
public class PetServices {
    private final PetRepository petRepository;
    private final GuardianRepository guardianRepository;

    public PetServices(PetRepository petRepository, GuardianRepository guardianRepository){
        this.petRepository = petRepository;
        this.guardianRepository = guardianRepository;
    }

    public Pet createPet(PetRequest petRequest) {
        validatePetRequest(petRequest);
        Guardian guardian = guardianRepository.findById(petRequest.guardianId())
                .orElseThrow(() -> new PetNotFoundException("Guardian not found."));

        System.out.println("Hi" + petRequest);
        Pet pet = new Pet(petRequest.name(), petRequest.breed(), petRequest.species(), petRequest.age(), guardian);  // could be changed into "toEntity"
        System.out.println(pet);
        return petRepository.save(pet);
    }

    public List<Pet> listAllPets (){
        return petRepository.findAll();
    }

    public Pet showPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet with ID " + id + " not found."));
    }

    public void killPet (Long id){
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            petRepository.deleteById(id);
        } else {
            throw new PetNotFoundException("The pet with id " + id + " does not exist.");
        }
    }

    private void validatePetRequest(PetRequest petRequest){
        if (petRequest.name() == null || petRequest.name().isEmpty()){
            throw new IllegalArgumentException("Pet name can not be empty.");
        }

        if (petRequest.age() == null || petRequest.age().isEmpty()){
            throw new IllegalArgumentException("Pet age can not be empty.");
        }
    }


}
