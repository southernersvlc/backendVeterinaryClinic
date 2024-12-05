package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.PetRequestDTO;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.GuardianFieldsCannotByEmptyException;
import com.example.veterinary_clinic.exceptions.PetNotFoundException;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.stereotype.Service;

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

    public Pet createPet(PetRequestDTO petRequestDTO){

        if (petRequestDTO.name() == null || petRequestDTO.name().isEmpty()){
            throw new GuardianFieldsCannotByEmptyException("Pet name can not be empty.");
        }

        if (petRequestDTO.age() == null || petRequestDTO.age().isEmpty()){
            throw new GuardianFieldsCannotByEmptyException("Pet age can not be empty.");
        }

        Guardian guardian = guardianRepository.findById(petRequestDTO.guardianId())
                .orElseThrow(() -> new PetNotFoundException("Guardian not found."));

        Pet pet = new Pet(petRequestDTO.name(), petRequestDTO.breed(), petRequestDTO.species(), petRequestDTO.age(), guardian);  // could be changed into "toEntity"

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

    public Pet modifyPet(Long id, PetRequestDTO petRequestDTO) {
        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("The pet with id " + id + " does not exist."));

        if (petRequestDTO.getGuardianId() != null) {
            Guardian guardian = guardianRepository.findById(petRequestDTO.getGuardianId())
                    .orElseThrow(() -> new PetNotFoundException("Owner with id " + petRequestDTO.getGuardianId() + " not found."));
            existingPet.setGuardian(guardian);
        }

        existingPet.setName(petRequestDTO.getName());
        existingPet.setAge(petRequestDTO.getAge());
        existingPet.setBreed(petRequestDTO.getBreed());

        return petRepository.save(existingPet);
    }

}
