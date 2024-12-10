package com.example.veterinary_clinic.config;

import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitFakeData {
    @Autowired
    GuardianRepository guardianRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            List<Guardian> guardianList = List.of
                    (new Guardian("Name1", "123456454", "test addres", "hola@email.com"),
                            new Guardian("Name2", "123451454", "test addres 2", "hola@email.com"));

            guardianRepository.saveAll(guardianList);
        };
    }
}