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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
@Profile("!test")
public class InitFakeData {

    @Bean
    public CommandLineRunner initGuardianData(GuardianRepository guardianRepository) {
        return args -> {
            List<Guardian> guardianList = List.of
                    (new Guardian("Name1", "123456454", "test addres", "hola@email.com"),
                            new Guardian("Name2", "123451454", "test addres 2", "hola@email.com"));

            guardianRepository.saveAll(guardianList);
        };
    }

    @Bean
    public CommandLineRunner initPetData(PetRepository petRepository, GuardianRepository guardianRepository) {
        return args -> {
            List<Pet> petList = List.of
                    (new Pet("Pet1", "breed1", "DOG", "3", guardianRepository.getReferenceById(1L)),
                            new Pet("Pet2", "breed2", "CAT", "5", guardianRepository.getReferenceById(2L)));

            petRepository.saveAll(petList);
        };
    }

    @Bean
    public CommandLineRunner initAppointmentData(PetRepository petRepository, GuardianRepository guardianRepository, AppointmentRepository appointmentRepository) {
        return args -> {
            List<Appointment> appointmentList = List.of
                    (new Appointment(LocalDate.of(2024, 12, 17), LocalTime.of(15, 30), "reason1", petRepository.getReferenceById(1L)),
                            new Appointment(LocalDate.of(2024, 12, 20), LocalTime.of(15, 30), "reason1", petRepository.getReferenceById(2L)));

            appointmentRepository.saveAll(appointmentList);
        };
    }
}