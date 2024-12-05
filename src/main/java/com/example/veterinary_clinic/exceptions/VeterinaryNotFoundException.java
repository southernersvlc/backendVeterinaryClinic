package com.example.veterinary_clinic.exceptions;


public class VeterinaryNotFoundException extends RuntimeException {
    public VeterinaryNotFoundException(String message) {
        super(message);
    }
}
