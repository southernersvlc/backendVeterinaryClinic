package com.example.veterinary_clinic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;

    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
    private List<Pet> petsList = new ArrayList<>();


    public Guardian(String name, String phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Guardian(Long id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardian guardian = (Guardian) o;
        return Objects.equals(id, guardian.id) && Objects.equals(name, guardian.name) && Objects.equals(phone, guardian.phone) && Objects.equals(email, guardian.email) && Objects.equals(address, guardian.address) && Objects.equals(petsList, guardian.petsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, address, petsList);
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", petsList=" + petsList +
                '}';
    }
}
