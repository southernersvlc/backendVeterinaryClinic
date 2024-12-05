package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.AppointmentRequestDTO;
import com.example.veterinary_clinic.dtos.AppointmentResponseDTO;
import com.example.veterinary_clinic.entities.Appointment;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.VeterinaryNotFoundException;
import com.example.veterinary_clinic.mappers.AppointmentMapper;
import com.example.veterinary_clinic.repositories.AppointmentRepository;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final GuardianRepository guardianRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, PetRepository petRepository,
                              GuardianRepository guardianRepository) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.guardianRepository = guardianRepository;
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

    public List<Appointment> findAll() {
        List<Appointment> appointmentList = appointmentRepository.findAll();

        if(appointmentList.isEmpty()) {
            throw new VeterinaryNotFoundException("There is not appointment to show");
        }
        return appointmentList;
    }
}
