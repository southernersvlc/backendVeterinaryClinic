package com.example.veterinary_clinic.config;

import com.example.veterinary_clinic.entities.Appointment;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.repositories.AppointmentRepository;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
@Profile("!test")
public class InitFakeData {

    @Bean
    @Order(1)
    public CommandLineRunner initGuardianData(GuardianRepository guardianRepository) {
        return args -> {
            if (guardianRepository.count() == 0) {
                List<Guardian> guardianList = List.of(
                        new Guardian("Alice Johnson", "123456454", "123 Meadow Lane", "alice.johnson@email.com"),
                        new Guardian("Emma Smith", "123451454", "124 Meadow Lane", "emma.smith@email.com")
                );
                guardianRepository.saveAll(guardianList);
            }
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner initPetData(PetRepository petRepository, GuardianRepository guardianRepository) {
        return args -> {
            if (petRepository.count() == 0) {
                List<Pet> petList = List.of(
                        new Pet("Coco", "Labrador", "DOG", "3", guardianRepository.findById(1L).orElseThrow()),
                        new Pet("Pelusa", "siames", "CAT", "5", guardianRepository.findById(2L).orElseThrow())
                );
                petRepository.saveAll(petList);
            }
        };
    }

    @Bean
    @Order(3)
    public CommandLineRunner initAppointmentData(AppointmentRepository appointmentRepository, PetRepository petRepository) {
        return args -> {
            if (appointmentRepository.count() == 0) {
                List<Appointment> appointmentList = List.of(
                        new Appointment(LocalDate.of(2024, 12, 17), LocalTime.of(15, 30), "Vaccination", petRepository.findById(1L).orElseThrow()),
                        new Appointment(LocalDate.of(2024, 12, 20), LocalTime.of(15, 30), "Follow up", petRepository.findById(2L).orElseThrow())
                );
                appointmentRepository.saveAll(appointmentList);
            }
        };
    }
}