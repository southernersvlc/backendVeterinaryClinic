package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.entities.Owner;
import com.example.veterinary_clinic.entities.Patient;
import com.example.veterinary_clinic.repositories.PatientRepository;
import com.example.veterinary_clinic.repositories.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        /*if (patient.getName().isEmpty() || patient.getBreed().isEmpty() || patient.getAge().isEmpty() ){
            return new ResponseEntity<>("Fields cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        if (patientRepository.existsById(patient.getId())){
            return new ResponseEntity<>("This id already exists.", HttpStatus.BAD_REQUEST);
        }*/
        //Owner owner = new Owner("Marc", "Marquez", "123456789");
        //patient.setOwner(owner);

        Owner owner = ownerRepository.findById(patient.getOwner().getId()).get();
        patient.setOwner(owner);
        patientRepository.save(patient);
        return new ResponseEntity<>("Patient created correctly.", HttpStatus.CREATED);
        }
}
