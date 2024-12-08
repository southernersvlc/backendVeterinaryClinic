package com.example.veterinary_clinic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String breed = "unknown";
    private String species;
    private String age;

    @ManyToOne
    @JoinColumn(name = "guardian_id", nullable = false)
    private Guardian guardian;

    public Pet(String name, String breed, String species,String age, Guardian guardian) {
        this.name = name;
        this.breed = (breed == null || breed.isEmpty()) ? "unknown" : breed;
        this.species = species;
        this.age = age;
        this.guardian = guardian;
    }

    public Pet(Optional<Pet> byId) {
    }
}
