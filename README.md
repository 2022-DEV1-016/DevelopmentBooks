# Software Development Books Kata API
This is a solution of the Books Kata using Test Driven Development (TDD) with Spring Boot and JUnit.

Complete Description of this kata can be found here: https://github.com/stephane-genicot/katas/blob/master/DevelopmentBooks.md

# Stack

- Java 11
- Spring Boot 2.6.5
- Lombok
- Mapstruct
- JUnit 5
- Mockito
- Spring Data JPA
- H2 in-memory database
- Maven
- Swagger/OpenApi 3.0

# How to Run It
This application will be packaged as a jar which have Tomcat embedded, you can follow these steps to run it:
1. Clone this repository:

        git clone https://github.com/2022-DEV1-016/DevelopmentBooks.git
2. Make sure you have JDK 11 and maven
3. Build and package the application using the following maven command:

        mvn package spring-boot:repackage
4. Move to /target and run the following command:

        java -jar devbookskata-0.0.1-SNAPSHOT.jar

Alternatively, you can run the app without packaging it using:

    mvn spring-boot:run

Or you can run it with your preferred IDE executing the main method of DevbookskataApplication.class.

The app will start running at http://localhost:8081

# Explore Books APIs

This application defines the following endpoints:

- Read all available Development Books:

        GET /api/books/getAll
- Calculate price of a list of books:

        POST /api/books/purchase

All APIs are "self-documented" by OpenApi 3.0 using annotations. To view the API docs, run the application and browse to: http://localhost:8081/swagger-ui.html

You can test them directly from this Swagger UI or via Postman or any other rest client.

# Run Tests

You can run tests using one of the following options:

- Using the maven command:

        mvn test
- Using your preferred IDE: running all tests or one by one.