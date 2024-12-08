package com.example.veterinary_clinic.controllers;


import com.example.veterinary_clinic.dtos.StatisticsResponseDTO;
import com.example.veterinary_clinic.services.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics/global")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public StatisticsResponseDTO getGlobalStatistics() {
        StatisticsResponseDTO statisticsResponseDTO = statisticsService.getStatistics();
        return new ResponseEntity<>(statisticsResponseDTO, HttpStatus.OK).getBody();
    }
}
