package com.example.veterinary_clinic.repositories;

import com.example.veterinary_clinic.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByNameAndGuardianId(String name, Long guardianId);
}
