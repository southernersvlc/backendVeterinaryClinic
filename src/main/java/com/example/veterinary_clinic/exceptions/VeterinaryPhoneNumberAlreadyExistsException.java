package com.example.veterinary_clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VeterinaryPhoneNumberAlreadyExistsException extends RuntimeException {
    public VeterinaryPhoneNumberAlreadyExistsException(String message) {
        super(message);
    }
}
