package com.example.veterinary_clinic;

import com.example.veterinary_clinic.controllers.GuardianController;
import com.example.veterinary_clinic.controllers.PetController;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.entities.Pet;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import com.example.veterinary_clinic.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

//import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

class PetControllerTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PetController petController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void given2Pets_whenCallGetAllPets_thenReturnAListWithThesePets() throws Exception {
        Guardian guardian1 = new Guardian("name1", "surname1", "phone1");
        guardianRepository.save(guardian1);
        Pet pet1 = new Pet("Sauron", "Labrador", "Dog", "10", guardian1);
        Pet pet2 = new Pet("Pepa", "", "Cat", "7",guardian1);
        petRepository.save(pet1);
        petRepository.save(pet2);

        mockMvc.perform(get("/pets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));

    }

    @Test
    void getPetById() {
    }

    @Test
    void addPet() {
    }

    @Test
    void updatePet() {
    }

    @Test
    void deletePet() {
    }
}