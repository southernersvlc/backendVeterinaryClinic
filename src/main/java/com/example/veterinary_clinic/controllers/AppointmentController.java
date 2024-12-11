package com.example.veterinary_clinic.controllers;

import com.example.veterinary_clinic.dtos.AppointmentRequestDTO;
import com.example.veterinary_clinic.dtos.AppointmentResponseDTO;
import com.example.veterinary_clinic.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> addAppointment(@RequestBody @Valid AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.createAppointment(appointmentRequestDTO);
        return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        List<AppointmentResponseDTO> appointmentList = appointmentService.findAll();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.findById(id);
        return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/next")
    public ResponseEntity<List<AppointmentResponseDTO>> getNextAppointmentsByPetId(@RequestParam Long petId) {
        List<AppointmentResponseDTO> appointmentList = appointmentService.findFutureAppointments(petId);
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @GetMapping("/past")
    public ResponseEntity<List<AppointmentResponseDTO>> getPastAppointmentsByPetId(@RequestParam Long petId) {
        List<AppointmentResponseDTO> appointmentList = appointmentService.findPastAppointments(petId);
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @RequestBody @Valid AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.updateAppointmentById(id, appointmentRequestDTO);
        return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointmentById(id);
        return new ResponseEntity<>("The appointment has been eliminated", HttpStatus.OK);
    }

}
