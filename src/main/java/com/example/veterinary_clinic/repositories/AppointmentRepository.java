package com.example.veterinary_clinic.repositories;

import com.example.veterinary_clinic.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
