package com.example.veterinary_clinic.controller;

import com.example.veterinary_clinic.controllers.GuardianController;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.repositories.GuardianRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class GuardianControllerTest {

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private GuardianController guardianController;

    @Autowired
    MockMvc mockMvc;


    @Test
    void given2Guardians_whenCallGetAllGuardians_thenReturnAListTheseGuardians() throws Exception {
        //Given
        Guardian guardian1 = new Guardian("name1", "phone1", "email1", "address1");
        Guardian guardian2 = new Guardian("name2", "phone2", "email2", "address2");
        guardianRepository.save(guardian1);
        guardianRepository.save(guardian2);


        //When
        mockMvc.perform(get("/guardians").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    void givenGuardianWithAnEmptyField_whenAddGuardian_thenReturnBadRequest() throws Exception {
        String guardianWithEmptyPhone = """
                    {
                        "name": "Jose",
                        "phone": "",
                        "email":"guardian1@gmail.com",
                        "address": "amatista 1"
                    }
                """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guardianWithEmptyPhone))
                .andExpect(status().isBadRequest());

        String guardianWithEmptyName = """
                    {
                        "name": "",
                        "phone": "123456789",
                        "email":"guardian1@gmail.com",
                        "address": "amatista 1"
                    }
                """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guardianWithEmptyName))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenGuardianWithAWrongPhoneNumber_whenAddGuardian_thenReturnBadRequest() throws Exception {
        String guardianWithAWrongPhoneNumber = """
                    {
                        "name": "Jose",
                        "phone": "123as",
                        "email":"guardian1@gmail.com",
                        "address": "amatista 1"
                    }
                """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guardianWithAWrongPhoneNumber))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenDuplicatePhoneNumber_whenAddGuardian_thenReturnBadRequest() throws Exception {
        String firstGuardian = """
                    {
                        "name": "Jose",
                        "phone": "123456789",
                        "email":"guardian1@gmail.com",
                        "address": "amatista 1"
                    }
                """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(firstGuardian))
                .andExpect(status().isCreated());

        String duplicatePhoneGuardian = """
                    {
                        "name": "John",
                        "phone": "123456789",
                        "email":"guardian1@gmail.com",
                        "address": "amatista 1"
                    }
                """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(duplicatePhoneGuardian))
                .andExpect(status().isConflict());
    }


    @Test
    void givenGuardianWithId_whenCallGetGuardianById_thenReturnThisGuardian() throws Exception {
        Guardian guardian = new Guardian("jose", "123456789", "guardian1@gmail.com", "amatista 1");

        guardianRepository.save(guardian);

        mockMvc.perform(get("/guardians/" + guardian.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(guardian.getName())))
                .andExpect(jsonPath("$.phone", is(guardian.getPhone())))
                .andExpect(jsonPath("$.email", is(guardian.getEmail())))
                .andExpect(jsonPath("$.address", is(guardian.getAddress())));
    }

    @Test
    void givenGuardianWithName_whenCallGetGuardianByName_thenReturnThisGuardian() throws Exception {
        Guardian guardian = new Guardian("jose", "123456789", "guardian1@gmail.com", "amatista 1");

        guardianRepository.save(guardian);

        mockMvc.perform(get("/guardians")
                        .param("name", "jose")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(guardian.getName())))
                .andExpect(jsonPath("$[0].phone", is(guardian.getPhone())))
                .andExpect(jsonPath("$[0].email", is(guardian.getEmail())))
                .andExpect(jsonPath("$[0].address", is(guardian.getAddress())));
    }

    @Test
    void givenGuardianWithId_whenCallUpdateGuardian_thenReturnUpdatedGuardian() throws Exception {
        Guardian guardian = new Guardian("Lil", "666333111", "amatista 1", "guardian1@gmail.com");
        guardianRepository.save(guardian);

        String updatedGuardian = """
                    {
                        "id": 1,
                        "name": "Arthur",
                        "phone": "111333666",
                        "email": "guardian1@gmail.com",
                        "address": "amatista 1"
                    }
                """;

        mockMvc.perform(put("/guardians/" + guardian.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedGuardian))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Arthur")))
                .andExpect(jsonPath("$.phone", is("111333666")))
                .andExpect(jsonPath("$.email", is("guardian1@gmail.com")))
                .andExpect(jsonPath("$.address", is("amatista 1")));
    }


    @Test
    void givenGuardianWithId_whenCallDeleteGuardian_thenReturnIsOk() throws Exception {
        Guardian guardian = new Guardian("Vicent", "666333111", "guardian1@gmail.com", "amatista 1");
        guardianRepository.save(guardian);

        mockMvc.perform(delete("/guardians/" + guardian.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("The guardian has been eliminated"));
    }
}