package com.example.veterinary_clinic.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;

    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
    private List<Pet> petsList = new ArrayList<>();

    public Guardian() {
    }

    public Guardian(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static boolean isValidPhoneNumber(String phoneNumber){
        String pattern = "^\\d{9}$";
        return  phoneNumber.matches(pattern);
    }

    public void setId(Long guardianId) {
        this.id = id;
    }
}
