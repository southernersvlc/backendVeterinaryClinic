package com.example.veterinary_clinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class OwnerControllerTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void given2Owners_whenCallGetAllOwners_thenReturnAListTheseOwners() throws Exception {
        //Given
        Owner owner1 = new Owner("name1", "surname1", "phone1");
        Owner owner2 = new Owner("name2", "surname2", "phone2");
        ownerRepository.save(owner1);
        ownerRepository.save(owner2);

        String jsonResponse =
                """
                    [
                        {
                            "id": 1,
                            "name": "name1",
                            "surname": "surname1",
                            "phoneNumber": "phone1"
                        },
                        {
                            "id": 2,
                            "name": "name2",
                            "surname": "surname2",
                            "phoneNumber": "phone2"
                        }
                    ]
                """;

        //When
        mockMvc.perform(get("/owners").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse))
                .andExpect(jsonPath("$[0].id", is(1)));
    }
}