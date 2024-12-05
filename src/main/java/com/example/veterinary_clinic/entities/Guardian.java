package com.example.veterinary_clinic.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
    private List<Pet> petsList = new ArrayList<>();

    public Guardian() {
    }

    public Guardian(String name, String phoneNumber, String address, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Guardian(Long id, String name, String phoneNumber, String address, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardian guardian = (Guardian) o;
        return Objects.equals(id, guardian.id) && Objects.equals(name, guardian.name) && Objects.equals(phoneNumber, guardian.phoneNumber) && Objects.equals(email, guardian.email) && Objects.equals(address, guardian.address) && Objects.equals(petsList, guardian.petsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, address, petsList);
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", petsList=" + petsList +
                '}';
    }
}
