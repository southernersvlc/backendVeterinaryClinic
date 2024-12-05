package com.example.veterinary_clinic.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VeterinaryNotFoundException extends RuntimeException {
    public VeterinaryNotFoundException(String message) {
        super(message);
    }
}
