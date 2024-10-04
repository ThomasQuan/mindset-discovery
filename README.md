# Spring Boot Backend Discovery Project 

## Overview

This repository contains a Spring Boot application that serves as the first attempt at building a backend for my personal website. It's built using Domain-Driven Design (DDD) principles and incorporates various Spring ecosystem technologies.

## Technologies Used

- Spring Boot
- Spring Security
- Lombok
- MapStruct
- Domain-Driven Design (DDD) principles
- PostgreSQL

## Purpose

This project represents my first iteration in building a backend for my personal website. It served as a learning experience for understanding the intricacies of Spring Boot and its associated design patterns and principles.

## Learning Outcomes

Through this project, I gained valuable insights into:

- Java Spring Boot and its opinionated design patterns
- Domain-Driven Design (DDD)
- Architecture Decision Records (ADR)
- Inversion of Control (IoC)
- Aspect-Oriented Programming (AOP)
- AOP Proxy setups in Spring
- Dependency Injection

## Project Status

While this project provided an excellent learning opportunity, I've decided to transition to Nest.js for my backend needs. The primary reason for this shift is the complexity and numerous steps required in Spring Boot to expose endpoints for consumption.

You can find the new Nest.js backend project here: [Nest.js Backend Repository](https://github.com/yourusername/nestjs-backend-project)

## Future Use

Although I've moved away from using Spring Boot for my personal projects, I believe it remains a suitable candidate for building large and scalable applications. This repository serves as a valuable reference for future Java Spring projects, showcasing best practices and implementations of various Spring ecosystem technologies.

## Getting Started

To set up and run this project locally, follow these steps:

1. **Prerequisites**
   - Ensure you have Java JDK 11 or later installed
   - Install Maven 3.6 or later
   - Install PostgreSQL

2. **Clone the Repository**
   ```
   git clone https://github.com/yourusername/spring-boot-backend-project.git
   cd spring-boot-backend-project
   ```

3. **Configure the Environment Variables**
   Create a `.env` file in the root directory of the project with the following variables:
   ```
    POSTGRES_DB=mindset
    POSTGRES_USER=tquan
    POSTGRES_PASSWORD=CrappyPatty7943!
    PRIVATE_KEY=
    PUBLIC_KEY=
   ```
   Replace the values with your actual database credentials and JWT keys.

4. **Verify Application Properties**
   The `application.properties` file should look like this:
   ```
   spring.config.import=optional:file:.env[.properties]
   spring.application.name=mindset
   spring.datasource.url=jdbc:postgresql://localhost:5432/${POSTGRES_DB}
   spring.datasource.username=${POSTGRES_USER}
   spring.datasource.password=${POSTGRES_PASSWORD}
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   jwt.private-key=${PRIVATE_KEY}
   jwt.public-key=${PUBLIC_KEY}
   ```
   Ensure this file is present in `src/main/resources/`.

5. **Build the Project**
   ```
   mvn clean install
   ```

6. **Run the Application**
   ```
   mvn spring-boot:run
   ```

7. **Access the Application**
   - The application should now be running on `http://localhost:8080`
   - You can test the endpoints using tools like Postman or cURL

8. **Run Tests**
9. Currently there are no test implemented using H2 datbasae
   ```
   mvn test
   ```

Note: Ensure your PostgreSQL server is running before starting the application.

## Contributing

None

## License

None
