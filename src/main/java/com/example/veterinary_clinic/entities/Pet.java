package com.example.veterinary_clinic.entities;

import jakarta.persistence.*;

@Entity

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String breed;
    private String age;

    @ManyToOne
    @JoinColumn(name = "guardian_id", nullable = false)
    private Guardian guardian;

    public Pet(Long id, String name, String breed, String age, Guardian guardian) {
        this.id = id;
        this.name = name;
        this.breed = breed;
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
