package com.example.veterinary_clinic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findById(Long id);

    boolean existsByPhoneNumber(String phoneNumber);
}
