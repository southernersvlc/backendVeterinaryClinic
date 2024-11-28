package com.example.veterinary_clinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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



        //When
        mockMvc.perform(get("/owners").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
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

    @Test
    void givenDuplicatePhoneNumber_whenAddOwner_thenReturnBadRequest() throws Exception {
        String firstOwner = """
        {
            "name": "Jose",
            "surname": "Vicent",
            "phoneNumber": "123456789"
        }
    """;

        mockMvc.perform(post("/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(firstOwner))
                .andExpect(status().isCreated());

        String duplicatePhoneOwner = """
        {
            "name": "John",
            "surname": "Doe",
            "phoneNumber": "123456789"
        }
    """;

        mockMvc.perform(post("/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(duplicatePhoneOwner))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This phone number already exists."));
    }
    
    @Test
    void givenOwnerWithId_whenCallGetOwnerById_thenReturnThisOwner() throws Exception {
        Owner owner = new Owner("jose", "reyes", "123456789");

        ownerRepository.save(owner);

        mockMvc.perform(get("/owners/" + owner.getId() )
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(owner.getName())))
                .andExpect(jsonPath("$.surname", is(owner.getSurname())))
                .andExpect(jsonPath("$.phoneNumber", is(owner.getPhoneNumber())));
    }

    @Test
    void givenOwnerWithId_whenCallUpdateOwner_thenReturnUpdatedOwner() throws Exception {
        Owner owner = new Owner("Lil", "Wayne", "666333111");
        ownerRepository.save(owner);

        String updatedOwner = """
                    {
                        "name": "Arthur",
                        "surname": "Schumacher",
                        "phoneNumber": "111333666"
                    }
                """;

        mockMvc.perform(put("/owners/" + owner.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedOwner))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Arthur")))
                .andExpect(jsonPath("$.surname", is("Schumacher")))
                .andExpect(jsonPath("$.phoneNumber", is("111333666")));
    }
}