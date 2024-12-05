package com.example.veterinary_clinic.entities;

import jakarta.persistence.*;

import java.util.Optional;

@Entity

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
        this.setBreed(breed); //set unknown for an empty field
        this.species = species;
        this.age = age;
        this.guardian = guardian;
    }

    public Pet() {
    }

    public Pet(Optional<Pet> byId) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = (breed == null || breed.isEmpty()) ? "unknown" : breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }
}
