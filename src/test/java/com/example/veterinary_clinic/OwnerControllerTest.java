package com.example.veterinary_clinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

//import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class OwnerControllerTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private OwnerController ownerController;

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


    @Test
    void givenOwnerWithAnEmptyField_whenAddOwner_thenReturnBadRequest() throws Exception {
        String ownerWithEmptyPhone = """
        {
            "name": "Jose",
            "surname": "Vicent",
            "phoneNumber": ""
        }
    """;

        mockMvc.perform(post("/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ownerWithEmptyPhone))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Fields cannot be empty."));

        String ownerWithEmptySurname = """
        {
            "name": "Jose",
            "surname": "",
            "phoneNumber": "111111111"
        }
    """;

        mockMvc.perform(post("/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ownerWithEmptySurname))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Fields cannot be empty."));

        String ownerWithEmptyName = """
        {
            "name": "",
            "surname": "Vicent",
            "phoneNumber": "111111111"
        }
    """;

        mockMvc.perform(post("/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ownerWithEmptyName))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Fields cannot be empty."));
    }

    @Test
    void givenOwnerWithAWrongPhoneNumber_whenAddOwner_thenReturnBadRequest() throws Exception {
        String ownerWithAWrongPhoneNumber = """
        {
            "name": "Jose",
            "surname": "Vicent",
            "phoneNumber": "123as"
        }
    """;

        mockMvc.perform(post("/owners")
        .contentType(MediaType.APPLICATION_JSON)
                .content(ownerWithAWrongPhoneNumber))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This is not a phone number, please try again."));

    }


}