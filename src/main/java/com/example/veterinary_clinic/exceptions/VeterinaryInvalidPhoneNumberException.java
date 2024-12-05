package com.example.veterinary_clinic.exceptions;


public class VeterinaryInvalidPhoneNumberException extends RuntimeException {
    public VeterinaryInvalidPhoneNumberException(String message) {
        super(message);
    }
}
