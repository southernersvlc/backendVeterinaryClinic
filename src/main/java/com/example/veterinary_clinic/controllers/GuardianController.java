package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.entities.Guardian;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guardians")
public class GuardianController {
    private final GuardianRepository guardianRepository;

    public GuardianController(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    @PostMapping
    public ResponseEntity<?> addGuardian(@RequestBody Guardian guardian) {
        String newPhoneNumber = guardian.getPhoneNumber();

        if (guardian.getName().isEmpty() || guardian.getPhoneNumber().isEmpty()) {
            return new ResponseEntity<>("Fields cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        if (guardianRepository.existsByPhoneNumber(guardian.getPhoneNumber())) {
            return new ResponseEntity<>("This phone number already exists.", HttpStatus.BAD_REQUEST);
        }

        if (!guardian.isValidPhoneNumber(newPhoneNumber)) {
            return new ResponseEntity<>("This is not a phone number, please try again.", HttpStatus.BAD_REQUEST);
        }

        guardianRepository.save(guardian);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardian);
    }

    @GetMapping
    public List<Guardian> getAllGuardians() {
        return guardianRepository.findAll();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getGuardianById(@PathVariable Long id) {
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);

        if (optionalGuardian.isPresent()) {
            return new ResponseEntity<>(optionalGuardian.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("This Id does not belong to any guardian.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getGuardianByName(@RequestParam String name) {
        List<Guardian> guardians = guardianRepository.findByNameIgnoreCaseContaining(name);

        if (!guardians.isEmpty()) {
            return new ResponseEntity<>(guardians, HttpStatus.OK);
        }
        return new ResponseEntity<>("The guardian with this name does not exist.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGuardian(@PathVariable Long id, @RequestBody Guardian guardian) {
        Optional<Guardian> optionalGuardianToUpdate = guardianRepository.findById(id);

        if (optionalGuardianToUpdate.isPresent()) {
            Guardian guardianToUpdate = optionalGuardianToUpdate.get();
            guardianToUpdate.setName(guardian.getName());
            guardianToUpdate.setPhoneNumber(guardian.getPhoneNumber());

            guardianRepository.save(guardianToUpdate);

            return new ResponseEntity<>(guardianToUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("This Id doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGuardian(@PathVariable Long id) {
        Optional<Guardian> optionalGuardianToDelete = guardianRepository.findById(id);

        if (optionalGuardianToDelete.isPresent()) {
            guardianRepository.deleteById(id);
            return new ResponseEntity<>("The Guardian has been deleted correctly.", HttpStatus.OK);
        }
        return new ResponseEntity<>("This Id doesn't exist.", HttpStatus.BAD_REQUEST);
    }

}



