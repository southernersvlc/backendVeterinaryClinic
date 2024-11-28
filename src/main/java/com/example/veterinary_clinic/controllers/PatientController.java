package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.entities.Owner;
import com.example.veterinary_clinic.entities.Patient;
import com.example.veterinary_clinic.repositories.PatientRepository;
import com.example.veterinary_clinic.repositories.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patients")

public class PatientController {

        private final PatientRepository patientRepository;
        private final OwnerRepository ownerRepository;

        public PatientController(PatientRepository patientRepository, OwnerRepository ownerRepository) {
            this.patientRepository = patientRepository;
            this.ownerRepository = ownerRepository;
        }

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient){
        Optional<Owner> ownerOptional = ownerRepository.findById(1L);

        if (ownerOptional.isPresent()){
            Owner owner = ownerOptional.get();
            patient.setOwner(owner);
            patientRepository.save(patient);
            return new ResponseEntity<>("The patient is created correctly.",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("The owner does not exist.", HttpStatus.BAD_REQUEST);
        }

}
