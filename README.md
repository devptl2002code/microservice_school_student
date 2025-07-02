# Spring Boot Microservices Architecture

This project is a basic microservice architecture built using **Spring Boot**, **Spring Cloud Gateway**, and **Netflix Eureka**. It demonstrates how to register services, route requests through an API Gateway, and structure a scalable backend.

---

## 🧱 Project Modules

### 1. `eureka-server`
- Acts as the **service registry** using **Netflix Eureka**.
- All other services register themselves here.

### 2. `cloud-gateway`
- Acts as the **API Gateway**.
- Uses **Spring Cloud Gateway** to route external traffic to appropriate services.
- Applies filters and forwards requests to microservices.

### 3. `student-service`
- Microservice that handles student-related operations.
- Registered with Eureka and accessible via API Gateway (`/student` route).

### 4. `school-service`
- Microservice that handles school-related operations.
- Registered with Eureka and accessible via API Gateway (`/school` route).
- Uses **MySQL** as database.
- Data Access handled via **Spring Data JPA**.
- Includes:
  - DTO-based controller-service mapping
  - Centralized error handling
  - Custom structured API responses
  - Logging via SLF4J + Logback (file + console)
  - Swagger/OpenAPI documentation
  - API versioning (`/api/v1/school`)
  - Full CRUD APIs

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3.4.x
- Spring Cloud 2024.0.1
- Spring Cloud Gateway
- Netflix Eureka (Service Discovery)
- Spring Data JPA
- MySQL
- Lombok
- SLF4J & Logback (logging)
- Swagger / OpenAPI (API documentation)
- Maven

---

### ✅ Features Implemented Today:
- ✅ Refactored `SchoolService` to use `DTO` classes (`SchoolRequest`, `SchoolResponse`)
- ✅ Added validation and duplicate check before saving
- ✅ Introduced structured logging with custom pattern in `application.properties`
- ✅ Used `@Slf4j` and meaningful logs in service and controller
- ✅ Proper error handling with `ErrorResponse` DTO
- ✅ Swagger documentation using `@Operation`, `@ApiResponse`
- ✅ Fully versioned API path (`/api/v1/school`)
- ✅ Built POST, GET, DELETE APIs with proper response structure

---

### Prerequisites:
- Java 17
- Maven
- (Optional) Postman or Swagger UI for API testing

---

## 🔗 Reference
[Let's Build a Microservice with Spring Boot (Medium)](https://medium.com/ms-club-of-sliit/lets-build-a-microservice-with-spring-boot-faf39b968857)
