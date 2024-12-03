package com.example.veterinary_clinic;

import com.example.veterinary_clinic.controllers.GuardianController;
import com.example.veterinary_clinic.entities.Guardian;
import com.example.veterinary_clinic.repositories.GuardianRepository;
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
            "phoneNumber": "",
            "email":"guardian1@gmail.com",
            "address": "amatista 1"
        }
    """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guardianWithEmptyPhone))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Fields cannot be empty."));

        String guardianWithEmptyName = """
        {
            "name": "",
            "phoneNumber": "",
            "email":"guardian1@gmail.com",
            "address": "amatista 1"
        }
    """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guardianWithEmptyName))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Fields cannot be empty."));
    }

    @Test
    void givenGuardianWithAWrongPhoneNumber_whenAddGuardian_thenReturnBadRequest() throws Exception {
        String guardianWithAWrongPhoneNumber = """
        {
            "name": "Jose",
            "phoneNumber": "123as",
            "email":"guardian1@gmail.com",
            "address": "amatista 1"
        }
    """;

        mockMvc.perform(post("/guardians")
        .contentType(MediaType.APPLICATION_JSON)
                .content(guardianWithAWrongPhoneNumber))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This is not a phone number, please try again."));
    }

    @Test
    void givenDuplicatePhoneNumber_whenAddGuardian_thenReturnBadRequest() throws Exception {
        String firstGuardian = """
        {
            "name": "Jose",
            "phoneNumber": "123456789",
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
            "phoneNumber": "123456789",
            "email":"guardian1@gmail.com",
            "address": "amatista 1"
        }
    """;

        mockMvc.perform(post("/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(duplicatePhoneGuardian))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This phone number already exists."));
    }
    
  /*
  @Test
    void givenGuardianWithId_whenCallGetGuardianById_thenReturnThisGuardian() throws Exception {
        Guardian guardian = new Guardian("jose", "reyes", "123456789");

        guardianRepository.save(guardian);

        mockMvc.perform(get("/guardians/" + guardian.getId() )
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(guardian.getName())))
                .andExpect(jsonPath("$.surname", is(guardian.getSurname())))
                .andExpect(jsonPath("$.phoneNumber", is(guardian.getPhoneNumber())));
    }
   */
    /*
     @Test
    void givenGuardianWithId_whenCallUpdateGuardian_thenReturnUpdatedGuardian() throws Exception {
        Guardian guardian = new Guardian("Lil", "Wayne", "666333111");
        guardianRepository.save(guardian);

        String updatedGuardian = """
                    {
                        "name": "Arthur",
                        "surname": "Schumacher",
                        "phoneNumber": "111333666"
                    }
                """;

        mockMvc.perform(put("/guardians/" + guardian.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedGuardian))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Arthur")))
                .andExpect(jsonPath("$.surname", is("Schumacher")))
                .andExpect(jsonPath("$.phoneNumber", is("111333666")));
    }
     */

   /*
   @Test
    void givenGuardianWithId_whenCallDeleteGuardian_thenReturnIsOk() throws Exception {
        Guardian guardian = new Guardian("Vicent", "Roig", "666333111");
        guardianRepository.save(guardian);

        mockMvc.perform(delete("/guardians/"+ guardian.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("The Guardian has been deleted correctly."));
    }
    */

}