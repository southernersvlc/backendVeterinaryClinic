package com.example.veterinary_clinic.repositories;

import com.example.veterinary_clinic.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.pet.id = :petId AND (a.date > :date OR (a.date = :date AND a.time > :time))")
    List<Appointment> findFutureAppointments(@Param("petId") Long petId, @Param("date") LocalDate date, @Param("time") LocalTime time);


    @Query("SELECT a FROM Appointment a WHERE a.pet.id = :petId AND (a.date < :date OR (a.date = :date AND a.time < :time))")
    List<Appointment> findPastAppointments(@Param("petId") Long petId, @Param("date") LocalDate date, @Param("time") LocalTime time);
}
