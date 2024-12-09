# Veterinary Clinic Application 🐾

This is a Spring Boot backend application designed to manage Margarita's veterinary clinic. The system supports the management of **Guardians** and their **Pets** (pets) through CRUD operations.

---
## What we have learned 

In this project we have worked with several new valuable insights into building a structured and maintainable backend application using Spring Boot.
- **Data Transfer.  Objects (DTOs)**: DTOs help decouple the data layer from the presentation layer. Instead of exposing entire entities, we use DTOs to transfer only necessary data between the client and server.


- **Service Layer**: The service layer handles business logic, keeping controllers clean and focused solely on handling HTTP requests.


- **Mappers**: Mappers convert between DTOs and entities, ensuring data transformation.
---
## Features ✨

- **Guardian Management** 🧑‍⚕️
    - Add, update, delete, and retrieve owners.
    - Validation for unique phone numbers and proper formatting.

- **Pet Management** 🐶
    - Add, update, delete, and retrieve patients.
    - Associate patients with owners.

- **Appointment Management** 🐱
  - Add, update, delete, and retrieve appointments.
  - Associate appointments with pets.

- **Statistics** 📊
  - Counts and show the total amount saved of pets, owners and dates within the system.
  
- **Validation and Error Handling** 
    - Proper checks to ensure required fields are populated.
    - Error messages for invalid data or non-existent IDs.

---
## Diagrams
![erDiagramMargarita.png](Utils%2FerDiagramMargarita.png)
![umlClinicVet.png](Utils%2FumlClinicVet.png)
---
## EndPoints
![guardians endpoints.png](Utils%2Fguardians%20endpoints.png)
![pets endpoints.png](Utils%2Fpets%20endpoints.png)
---

## Technologies Used ⚙️

- **Backend Framework**: Spring Boot
- **Database**: MySQL
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **REST API**: CRUD operations using Spring Controllers

---

## Getting Started 🚀

### Prerequisites

1. Java 17+
2. Maven
3. MySQL Database
4. IntelliJ IDEA (Optional but recommended)

---

## 📦 **Files Structure**
    |--- utils
    |--- src
        |--- main
            |--- java
                |---com.example.veterinary_clinic
                        |--- controllers
                            |--- GuardianController
                            |--- PetController
                            |--- AppointmentController
                            |--- StatisticsController
                        |--- dtos
                            |--- AppointmentRequestDTO
                            |--- AppointmentResponseDTO
                            |--- GuardianRequestDTO
                            |--- GuardianResponseDTO
                            |--- PetRequestDTO
                            |--- PetResponseDTO
                            |--- StatisticsResponseDTO
                        |--- entities
                            |--- Appointment
                            |--- Guardian
                            |--- Patient
                        |--- exceptions
                        |--- mappers
                            |--- AppointmentMapper
                            |--- GuardianMapper
                            |--- PetMapper
                        |--- repositories
                            |--- AppointmentRepository
                            |--- GuardianRepository
                            |--- PetRepository
                        |--- services
                            |--- AppointmentService
                            |--- GuardianService
                            |--- PetServices
                            |--- StatisticsService
                        |--- VeterinaryClinicApplication
        |--- test
           |--- java
               |---com.example.veterinary_clinic
                        |--- controller
                            |--- OwnerControllerTest

                        |--- services
                            |--- GuardianServiceTest
                        |--- VeterinaryClinicApplicationTests

---
## 💻 Technology Stack:

<img src= "https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white"/>
<img src= "https://img.shields.io/badge/-Postman-FF6C37?style=flat&logo=postman&logoColor=white"/>
<img src= "https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Intellij%20Idea-000?logo=intellij-idea&amp;style=for-the-badge"/>
<img src= "https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white"/>
<img src= "https://shields.io/badge/simple__diarizer-Trello-blue?logo=Trello&style=flat"/>
<img src= "https://img.shields.io/badge/Lucid-282C33?logo=lucid&logoColor=fff&style=for-the-badge"/>

---

## 🌐 Authors

- **Mònica Simó**                      
  [<img src="https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />](https://github.com/monicasimoF5)
  [<img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn" />](https://www.linkedin.com/in/mónica-simó/)
- **Sergi Asins**                      
  [<img src="https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />](https://github.com/SergiAsins)
  [<img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn" />](https://www.linkedin.com/in/sergiasins)
- **Paola Perdomo**                      
  [<img src="https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />](https://github.com/Paola077)
  [<img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn" />](https://www.linkedin.com/in/paolaperdomo07/)
- **Miguel Reyes**                              
  [<img src="https://img.shields.io/badge/github-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />](https://github.com/MIANREVA2024)
  [<img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn" />](https://www.linkedin.com/in/miguelreyesvasquez/)