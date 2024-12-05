package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.AppointmentRequestDTO;
import com.example.veterinary_clinic.dtos.AppointmentResponseDTO;
import com.example.veterinary_clinic.entities.Appointment;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.VeterinaryNotFoundException;
import com.example.veterinary_clinic.mappers.AppointmentMapper;
import com.example.veterinary_clinic.repositories.AppointmentRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, PetRepository petRepository) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
    }

    public AppointmentResponseDTO createAppoiment(AppointmentRequestDTO appointmentRequestDTO) {
        Optional<Pet> pet = petRepository.findById(appointmentRequestDTO.petId());
        if (pet.isPresent()) {
            Appointment appointment = AppointmentMapper.toEntity(appointmentRequestDTO, pet.get());
            Appointment savedAppoiment = appointmentRepository.save(appointment);
            return AppointmentMapper.toResponseDto(savedAppoiment, pet.get());
        }
        throw new VeterinaryNotFoundException("There is no pet with this id.");
    }
}
