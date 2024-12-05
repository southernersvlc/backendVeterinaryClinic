package com.example.veterinary_clinic.exceptions;

public class VeterinaryExistingPhoneNumberException extends RuntimeException {
    public VeterinaryExistingPhoneNumberException(String message) {
        super(message);
    }
}
