package com.example.veterinary_clinic.repositories;

import com.example.veterinary_clinic.entities.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {

    Optional<Guardian> findById(Long id);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Guardian> findByNameIgnoreCaseContaining(String name);
}
