package com.example.veterinary_clinic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class VeterinaryClinicApplicationTests {

	@Test
	void contextLoads() {
	}

}
