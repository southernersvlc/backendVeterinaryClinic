package com.example.veterinary_clinic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @PostMapping
    public ResponseEntity<?>  addOwner(@RequestBody Owner owner){
        String newPhoneNumber = owner.getPhoneNumber();

        if (owner.getName().isEmpty() || owner.getSurname().isEmpty() || owner.getPhoneNumber().isEmpty()){
            return new ResponseEntity<>("Fields cannot be empty.",HttpStatus.BAD_REQUEST);
        }

        if (ownerRepository.existsByPhoneNumber(owner.getPhoneNumber())){
            return new ResponseEntity<>("This phone number already exists.", HttpStatus.BAD_REQUEST);
        }

        if (!owner.isValidPhoneNumber(newPhoneNumber)){
            return new ResponseEntity<>("This is not a phone number, please try again.", HttpStatus.BAD_REQUEST);
        }

        ownerRepository.save(owner);
        return new ResponseEntity<>("Owner created correctly.", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        if (optionalOwner.isPresent()) {
            return new ResponseEntity<>(optionalOwner.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("This Id does not belong to any owner.", HttpStatus.NOT_FOUND);
    }
}
