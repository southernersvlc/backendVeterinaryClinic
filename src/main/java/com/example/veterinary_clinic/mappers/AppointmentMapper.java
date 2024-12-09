package com.example.veterinary_clinic.mappers;

import com.example.veterinary_clinic.dtos.AppointmentRequestDTO;
import com.example.veterinary_clinic.dtos.AppointmentResponseDTO;
import com.example.veterinary_clinic.dtos.PetResponseDTO;
import com.example.veterinary_clinic.entities.Appointment;
import com.example.veterinary_clinic.entities.Pet;

public class AppointmentMapper {
    public static Appointment toEntity(AppointmentRequestDTO appointmentRequestDTO, Pet pet) {
        return new Appointment(
                appointmentRequestDTO.date(),
                appointmentRequestDTO.time(),
                appointmentRequestDTO.reason(),
                pet);
    }

    public static AppointmentResponseDTO toResponseDto(Appointment appointment) {
        PetResponseDTO petResponseDTO = PetMapper.toResponse(appointment.getPet());
        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getReason(),
                petResponseDTO);
    }

}
