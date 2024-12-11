package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.PetRequestDTO;
import com.example.veterinary_clinic.dtos.PetResponseDTO;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.VeterinaryAlreadyExistsException;
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

    public PetResponseDTO createPet(PetRequestDTO petRequestDTO) {
        Optional<Pet> existPet = petRepository.findByNameAndGuardianId(petRequestDTO.name(), petRequestDTO.guardianId());
        if (existPet.isPresent()) {
            throw new VeterinaryAlreadyExistsException("There is already a pet with this name linked to this guardian");
        }

        Optional<Guardian> existGuardian = guardianRepository.findById(petRequestDTO.guardianId());
        if(existGuardian.isEmpty()) {
            throw new VeterinaryNotFoundException("There is no guardian with this id.");
        }

        Guardian guardian = existGuardian.get();
        Pet petToSave = PetMapper.toEntity(petRequestDTO, guardian);
        Pet savedPet = petRepository.save(petToSave);
        return PetMapper.toResponse(savedPet);
    }

    public List<PetResponseDTO> listAllPets (){
        List<Pet> petList = petRepository.findAll();
        List<PetResponseDTO> responseList = new java.util.ArrayList<>(Collections.emptyList());
        petList.forEach(pet -> {
            PetResponseDTO petResponseDTO = PetMapper.toResponse(pet);
            responseList.add(petResponseDTO);
        });

        if(petList.isEmpty()) {
            throw new VeterinaryNotFoundException("There are no pets to show");
        }
        return responseList;
    }

    public PetResponseDTO showPetById(Long id) {
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

    public PetResponseDTO modifyPet(Long id, PetRequestDTO petRequestDTO) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if(optionalPet.isPresent()) {
            Pet pet = optionalPet.get();

            pet.setName(petRequestDTO.name());
            pet.setBreed(petRequestDTO.breed());
            pet.setSpecies(petRequestDTO.species());
            pet.setAge(petRequestDTO.age());

            Pet updatePet = petRepository.save(pet);
            return PetMapper.toResponse(updatePet);
        }
        throw new VeterinaryNotFoundException("The guardian with id " + id + " does not exist.");
    }
}
