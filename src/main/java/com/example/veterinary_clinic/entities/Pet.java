package com.example.veterinary_clinic.entities;

import jakarta.persistence.*;

@Entity

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String breed = "unknown";
    private String age;
    /*@Enumerated(EnumType.STRING)
    private Species species;*/

    @ManyToOne
    @JoinColumn(name = "guardian_id", nullable = false)
    private Guardian guardian;

    /*public enum Species{
        DOG,
        CAT
    }*/

    public Pet(String name, String breed, String age, Guardian guardian) {
        this.name = name;
        if (breed == null || breed.isEmpty()) this.breed = "unknown"; //should be in the setter
        //this.species = species;
        this.age = age;
        this.guardian = guardian;
    }

    public Pet() {
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
        this.breed = breed;
    }

    /*public Species getSpecies() {
        return species;
    }*/

    /*public void setSpecies(Species species) {
        this.species = species;
    }*/

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
