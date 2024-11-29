package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.entities.Owner;
import com.example.veterinary_clinic.entities.Patient;
import com.example.veterinary_clinic.repositories.PatientRepository;
import com.example.veterinary_clinic.repositories.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @GetMapping()
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            return new ResponseEntity<>(optionalPatient.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("This Id does not belong to any patient.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient, @RequestParam Long ownerId) {

        Optional<Owner> ownerOptional = ownerRepository.findById(ownerId);

        if (!ownerOptional.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The tutor does not exist with this id" + ownerId);
        }

        Owner owner = ownerOptional.get();
        patient.setOwner(owner);
        Patient newPatient = patientRepository.save(patient);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {

        Optional<Patient> optionalPatientToUpdate = patientRepository.findById(id);

        if (optionalPatientToUpdate.isPresent()) {
            Patient patientToUpdate = optionalPatientToUpdate.get();
            patientToUpdate.setName(patient.getName());
            patientToUpdate.setAge(patient.getAge());
            patientToUpdate.setBreed(patient.getBreed());

            patientRepository.save(patientToUpdate);

            return new ResponseEntity<>(patientToUpdate, HttpStatus.OK);
        }

        return new ResponseEntity<>("This Id doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {

        Optional<Patient> optionalPatientToDelete = patientRepository.findById(id);

        if (optionalPatientToDelete.isPresent()) {
            patientRepository.deleteById(id);

            return new ResponseEntity<>("The patient has been deleted correctly", HttpStatus.OK);
        }

        return new ResponseEntity<>("Ei buddy! this Id does not exist, chill out bro!", HttpStatus.BAD_REQUEST);
    }
}



