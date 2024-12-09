package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.PetRequest;
import com.example.veterinary_clinic.dtos.PetResponse;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.VeterinaryFieldsCannotBeEmptyException;
import com.example.veterinary_clinic.exceptions.VeterinaryNotFoundException;
import com.example.veterinary_clinic.mappers.PetMapper;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public PetResponse createPet(PetRequest petRequest) {
        validatePetRequest(petRequest);
        Guardian guardian = guardianRepository.findById(petRequest.guardianId())
                .orElseThrow(() -> new VeterinaryNotFoundException("There is no guardian with this id."));

        Pet petToSave = PetMapper.toEntity(petRequest, guardian);
        Pet savedPet = petRepository.save(petToSave);
        return PetMapper.toResponse(savedPet);
    }

    public List<PetResponse> listAllPets (){
        List<Pet> petList = petRepository.findAll();
        List<PetResponse> responseList = new java.util.ArrayList<>(Collections.emptyList());
        petList.forEach(pet -> {
            PetResponse petResponse = PetMapper.toResponse(pet);
            responseList.add(petResponse);
        });

        if(petList.isEmpty()) {
            throw new VeterinaryNotFoundException("There are no pets to show");
        }
        return responseList;
    }

    public PetResponse showPetById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if(optionalPet.isEmpty()) {
            throw new VeterinaryNotFoundException("Pet with id " + id + " does not exist.");
        }
        Pet pet = optionalPet.get();
        return PetMapper.toResponse(pet);
    }

    public void deletePetById(Long id){
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            petRepository.deleteById(id);
        } else {
            throw new VeterinaryNotFoundException("The pet with id " + id + " does not exist.");
        }
    }

    public PetResponse modifyPet(Long id, PetRequest petRequest) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if(optionalPet.isPresent()) {
            Pet pet = optionalPet.get();

            pet.setName(petRequest.name());
            pet.setBreed(petRequest.breed());
            pet.setSpecies(petRequest.species());
            pet.setAge(petRequest.age());

            Pet updatePet = petRepository.save(pet);
            return PetMapper.toResponse(updatePet);
        }
        throw new VeterinaryNotFoundException("The guardian with id " + id + " does not exist.");
    }


    private void validatePetRequest(PetRequest petRequest){
        if (petRequest.name() == null || petRequest.name().isEmpty()){
            throw new VeterinaryFieldsCannotBeEmptyException("Pet name can not be empty.");
        }

        if (petRequest.age() == null || petRequest.age().isEmpty()){
            throw new VeterinaryFieldsCannotBeEmptyException("Pet age can not be empty.");
        }
    }
}
