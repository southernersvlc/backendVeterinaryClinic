package com.example.veterinary_clinic.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "petId", nullable = false)
    private Pet pet;

    public Appointment(LocalDate date, LocalTime time, String reason, Pet pet) {
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.pet = pet;
    }
}
