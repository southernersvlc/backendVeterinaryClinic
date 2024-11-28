package com.example.veterinary_clinic;

import jakarta.persistence.*;

@Entity

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String Name;
    private String breed;
    private enum Type{dog,cat};
    private String age;

    @ManyToOne
    @JoinColumn (name = "owner_id")
    private Owner owner;

    public Patient(Long id, String name, String breed, String age, Owner owner) {
        this.id = id;
        Name = name;
        this.breed = breed;
        this.age = age;
        this.owner = owner;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
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
