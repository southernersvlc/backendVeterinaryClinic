package com.example.veterinary_clinic.repositories;

import com.example.veterinary_clinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Long> {

}
