package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.PetRequest;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.PetNotFoundException;
import com.example.veterinary_clinic.mappers.PetMapper;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.stereotype.Service;

import javax.imageio.plugins.jpeg.JPEGQTable;

@Service
public class PetServices {
    private final PetRepository petRepository;
    private final GuardianRepository guardianRepository;

    public PetServices(PetRepository petRepository, GuardianRepository guardianRepository){
        this.petRepository = petRepository;
        this.guardianRepository = guardianRepository;
    }

    public Pet createPet(PetRequest petRequest){
        Guardian guardian = guardianRepository.findById(petRequest.guardianId())
                .orElseThrow(() -> new PetNotFoundException("Guardian not found."));

        System.out.println("Hi" + petRequest);
        Pet pet = new Pet(petRequest.name(), petRequest.breed(), petRequest.age(), guardian);  // could be changed into "toEntity"
        System.out.println(pet);
        return petRepository.save(pet);
    }

    private void validatePetRequest(PetRequest petRequest) {

        if (petRequest.name() == null || petRequest.name().isEmpty()){
            throw new IllegalArgumentException("Pet name can not be empty.");
        }

        if (petRequest.breed() == null || petRequest.breed().isEmpty()){
            String breed = (petRequest.breed() == null || petRequest.breed().isEmpty()) ? "unknown" : petRequest.breed();
            //PetRequest updatedPetRequest = new PetRequest(petRequest.id(), petRequest.name(), breed, petRequest.age(), petRequest.guardianId());
        }

        /*if (petRequest.species() != Pet.Species.CAT || petRequest.species() != Pet.Species.DOG){
            throw new IllegalArgumentException("Pet species must be CAT or DOG.");
        }*/

        /*if (petRequest.age() == null || petRequest.age().isEmpty()){
            throw new IllegalArgumentException("Pet age can not be empty.");
        }*/

    }
}
