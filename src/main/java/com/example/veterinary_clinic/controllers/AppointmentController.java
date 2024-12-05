package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.dtos.AppointmentRequestDTO;
import com.example.veterinary_clinic.dtos.AppointmentResponseDTO;
import com.example.veterinary_clinic.entities.Appointment;
import com.example.veterinary_clinic.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appoiments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> addAppoiment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.createAppoiment(appointmentRequestDTO);
        return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointmentList = appointmentService.findAll();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK).getBody();
    }
}
