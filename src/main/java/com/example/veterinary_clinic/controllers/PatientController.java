package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.entities.Patient;
import com.example.veterinary_clinic.repositories.PatientRepository;
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

        public PatientController(PatientRepository patientRepository) {
            this.patientRepository = patientRepository;
        }
    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient){
        if (patient.getName().isEmpty() || patient.getBreed().isEmpty() || patient.getAge().isEmpty() ){
            return new ResponseEntity<>("Fields cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        if (patientRepository.existsById(patient.getId())){
            return new ResponseEntity<>("This id already exists.", HttpStatus.BAD_REQUEST);
        }

        patientRepository.save(patient);
        return new ResponseEntity<>("Patient created correctly.", HttpStatus.CREATED);

        }


}
