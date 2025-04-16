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

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3.4.x
- Spring Cloud 2024.0.1
- Spring Cloud Gateway
- Netflix Eureka (Service Discovery)
- Maven

---

### Prerequisites:
- Java 17
- Maven
- (Optional) Postman or any API client


## Postman API 
- https://api.postman.com/collections/32596270-dbddf429-0fd4-448e-b32c-2998f452c320?access_key=PMAT-01JS083HS31BN8CYMF0HM45NGP
