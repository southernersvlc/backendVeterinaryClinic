package com.example.veterinary_clinic.controller;

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
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

//import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class PetControllerTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PetController petController;

    @Autowired
    private GuardianController guardianController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void given2Pets_whenCallGetAllPets_thenReturnAListWithThesePets() throws Exception {
        Guardian guardian1 = new Guardian("name1", "123456789", "address1", "email1");
        guardianRepository.save(guardian1);
        Pet pet1 = new Pet("Sauron", "Labrador", "DOG", "10", guardian1);
        Pet pet2 = new Pet("Pepa", "", "CAT", "7",guardian1);
        petRepository.save(pet1);
        petRepository.save(pet2);

        mockMvc.perform(get("/pets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));

    }

    @Test
    void givenPetWithId_whenCallGetPetById_thenReturnThisPet() throws Exception {
        Guardian guardian1 = new Guardian("name1", "123456789", "address1", "email1");
        guardianRepository.save(guardian1);
        Pet pet1 = new Pet("Gandalf", "WatterDog", "CAT", "3", guardian1);
        petRepository.save(pet1);

        String createdPet = """
                    {
                        "name": "Gandalf",
                        "breed": "WatterDog",
                        "species": "CAT",
                        "age" : "3",
                        "guardianId" : "1"
                    }
                """;

        mockMvc.perform(put("/pets/" + pet1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createdPet))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Gandalf")))
                .andExpect(jsonPath("$.breed", is("WatterDog")))
                .andExpect(jsonPath("$.age", is("3")));
    }

    @Test
    void givenNewPet_whenCallAddPet_thenPetIsSavedInRepo() throws Exception {
        Guardian guardian1 = new Guardian("name1", "123456789", "address1", "email1");
        guardianRepository.save(guardian1);

        String petToAdd = """
        {
            "name": "Terminator",
            "breed": "Persa",
            "species": "CAT",
            "age": 4,
            "guardianId": 1
        }""";

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petToAdd))
                .andExpect(status().isCreated());
    }

    @Test
    void givenAPet_whenCallUpdatePet_thenThisPetIsUpdated() throws Exception {
        Guardian guardian1 = new Guardian("name1", "123456789", "address1", "email1");
        guardianRepository.save(guardian1);
        Pet pet1 = new Pet("Rambo", "StrikeDog", "CAT", "3", guardian1);
        petRepository.save(pet1);
        String petToUpdate ="""
             {
                "name" : "Terminator",
                "breed" : "Persa",
                "species" : "CAT",
                "age" : "4",
                "guardianId" : 1
                }""";

        mockMvc.perform(put("/pets/1", petToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petToUpdate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Terminator")))
                .andExpect(jsonPath("$.breed", is("Persa")));

    }

    @Test
    void givenAPetWithId_whenCallDeletePet_thenDeleteThisPet() throws Exception {
        Guardian guardian1 = new Guardian("name1", "123456789", "address1", "email1");
        guardianRepository.save(guardian1);
        Pet pet1 = new Pet("Charmander", "PokemonDog", "DOG", "11", guardian1);
        petRepository.save(pet1);

        mockMvc.perform(delete("/pets/" + pet1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("The pet has been deleted successfully."));
    }
}