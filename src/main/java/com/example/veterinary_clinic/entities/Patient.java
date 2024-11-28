package com.example.veterinary_clinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String breed;
    //private enum Type{dog,cat};
    private String age;

    @ManyToOne
    @JoinColumn (name = "owner_id")
    @JsonIgnoreProperties(value = "patients")
    private Owner owner;

    public Patient(Long id, String name, String breed, String age, Owner owner) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.owner = owner;
    }

    public Patient() {
    }

    public Patient(String name, String breed, String age, Long owner_id) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.owner = new Owner();
        this.owner.setId(owner_id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getAge() {
        return age;
    }

    public Owner getOwner() {
        return owner;
    }

    public long getOwnerId() { return owner.getId(); }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
