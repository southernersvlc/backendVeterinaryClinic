package com.example.veterinary_clinic.repositories;

import com.example.veterinary_clinic.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findById(Long id);

    boolean existsByPhoneNumber(String phoneNumber);
}
