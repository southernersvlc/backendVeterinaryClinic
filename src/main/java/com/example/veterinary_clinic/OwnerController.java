package com.example.veterinary_clinic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @PostMapping
    public ResponseEntity<?>  addOwner(@RequestBody Owner owner){
        if (owner.getName().isEmpty() || owner.getSurname().isEmpty() || owner.getPhoneNumber().isEmpty()){
            return new ResponseEntity<>("Fields cannot be empty",HttpStatus.BAD_REQUEST);
        }

        /*if (ownerRepository.existsBySurname(owner.getSurname())){
            return new ResponseEntity<>("The owner really exists", HttpStatus.BAD_REQUEST);
        }*/

        ownerRepository.save(owner);
        return new ResponseEntity<>("Owner created correctly", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }


}
