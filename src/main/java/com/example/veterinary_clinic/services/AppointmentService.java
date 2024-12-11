package com.example.veterinary_clinic.services;

import com.example.veterinary_clinic.dtos.AppointmentRequestDTO;
import com.example.veterinary_clinic.dtos.AppointmentResponseDTO;
import com.example.veterinary_clinic.entities.Appointment;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.exceptions.VeterinaryNotFoundException;
import com.example.veterinary_clinic.mappers.AppointmentMapper;
import com.example.veterinary_clinic.repositories.AppointmentRepository;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final GuardianRepository guardianRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, PetRepository petRepository,
                              GuardianRepository guardianRepository) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.guardianRepository = guardianRepository;
    }

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Optional<Pet> pet = petRepository.findById(appointmentRequestDTO.petId());
        if (pet.isPresent()) {
            Appointment appointment = AppointmentMapper.toEntity(appointmentRequestDTO, pet.get());
            Appointment savedAppointment = appointmentRepository.save(appointment);
            return AppointmentMapper.toResponseDto(savedAppointment);
        }
        throw new VeterinaryNotFoundException("There is no pet with this id.");
    }

    public List<AppointmentResponseDTO> findAll() {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentResponseDTO> responseList = new java.util.ArrayList<>(Collections.emptyList());
        appointmentList.forEach(appointment -> {
            AppointmentResponseDTO appointmentResponseDTO = AppointmentMapper.toResponseDto(appointment);
            responseList.add(appointmentResponseDTO);
                });

        if(appointmentList.isEmpty()) {
            throw new VeterinaryNotFoundException("There is not appointment to show");
        }
        return responseList;
    }

    public AppointmentResponseDTO findById(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if(optionalAppointment.isEmpty()) {
            throw new VeterinaryNotFoundException("The appointment with id " + id + " does not exist.");
        }
        Appointment appointment = optionalAppointment.get();
        return AppointmentMapper.toResponseDto(appointment);
    }

    public AppointmentResponseDTO updateAppointmentById(Long id, AppointmentRequestDTO appointmentRequestDTO) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            throw new VeterinaryNotFoundException("The appointment with id " + id + " does not exist.");
        }

        Appointment appointment = optionalAppointment.get();

        Optional<Pet> optionalPet = petRepository.findById(appointmentRequestDTO.petId());
        if (optionalPet.isEmpty()) {
            throw new VeterinaryNotFoundException("The pet with id " + appointmentRequestDTO.petId() + " does not exist.");
        }

        appointment.setDate(appointmentRequestDTO.date());
        appointment.setTime(appointmentRequestDTO.time());
        appointment.setReason(appointmentRequestDTO.reason());

        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return AppointmentMapper.toResponseDto(updatedAppointment);
    }

    public void deleteAppointmentById(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isEmpty()) {
            throw new VeterinaryNotFoundException("The appointment with id " + id + " does not exist.");
        }
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentResponseDTO> findFutureAppointments(Long petId) {
        LocalDate actualDate = LocalDate.now();
        LocalTime actualTime = LocalTime.now();
        List<Appointment> appointments = appointmentRepository.findFutureAppointments(petId, actualDate, actualTime);
        return appointments.stream() .map(AppointmentMapper::toResponseDto) .collect(Collectors.toList()); }
}
