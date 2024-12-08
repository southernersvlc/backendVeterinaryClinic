package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.StatisticsResponseDTO;
import com.example.veterinary_clinic.repositories.AppointmentRepository;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final GuardianRepository guardianRepository;
    private final PetRepository petRepository;
    private final AppointmentRepository appointmentRepository;

    public StatisticsService(GuardianRepository guardianRepository, PetRepository petRepository, AppointmentRepository appointmentRepository) {
        this.guardianRepository = guardianRepository;
        this.petRepository = petRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public StatisticsResponseDTO getStatistics() {
        Long appointments = appointmentRepository.count();
        Long pets = petRepository.count();
        Long guardians = guardianRepository.count();

        return new StatisticsResponseDTO(appointments, pets, guardians);
    }
}
