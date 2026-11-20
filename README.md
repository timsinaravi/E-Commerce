# E-Commerce Backend

A RESTful E-Commerce backend developed using **Java 17 and Spring Boot**, with MySQL for data persistence and Spring Security for authentication and authorization.

The application covers essential e-commerce operations, including user management, product and category management, shopping cart functionality, and order processing.

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 4.1.0
- **Database:** MySQL
- **ORM / Persistence:** Spring Data JPA, Hibernate
- **Security:** Spring Security, JWT (JJWT)
- **Validation:** Jakarta Bean Validation
- **Build Tool:** Maven
- **Other:** Lombok, REST APIs

## Features

- **User Management:** User registration, profile management, email uniqueness validation, and USER/ADMIN role support.
- **Category Management:** Category CRUD operations and product associations.
- **Product Management:** Product CRUD operations, category association, product search, and stock validation.
- **Cart Management:** Add, update, and remove cart items, clear the cart, calculate totals, and validate product stock.
- **Order Processing:** Place orders from the user's cart, calculate order totals, update product stock, and maintain purchase price snapshots.
- **Validation & Exception Handling:** Request validation, custom exceptions, and centralized exception handling.
- **Data Integrity:** Transactional operations, resource ownership checks, and deletion restrictions for associated records.

## Security

- JWT-based authentication using Spring Security.
- BCrypt password hashing.
- Stateless session management.
- USER and ADMIN role-based authorization.
- Public access to registration, login, product retrieval, and category retrieval.
- Protected user, cart, and order operations.
- Users can only access and manage their own cart and personal order history.
- Administrative operations are restricted to the ADMIN role.

Public registration assigns the USER role by default. ADMIN accounts are provisioned separately.

## Project Structure

```text
com.ecommerce
├── controller       # Handles incoming HTTP requests and API responses
├── service          # Defines business operation interfaces
│   └── impl         # Implements business logic
├── repository       # Handles database operations using Spring Data JPA
├── entity           # Contains JPA entity classes
├── dto              # Contains request and response DTOs
├── mapper           # Converts between entities and DTOs
├── exception        # Custom exceptions and global exception handling
├── enums            # Contains application-specific enumerations
└── security         # Contains security configuration and authentication logic
    ├── config       # Spring Security configuration
    ├── jwt          # JWT generation, validation, and filtering
    └── service      # Custom UserDetails implementation
```

The application follows a layered architecture, separating API handling, business logic, data access, and persistence. DTOs keep API request and response models separate from JPA entities.

## API Overview

All endpoints are prefixed with `/api`.

| Module | Main Endpoints |
|---|---|
| Authentication | `/auth/register`, `/auth/login` |
| Users | `/users`, `/users/me`, `/users/{id}` |
| Categories | `/categories`, `/categories/{id}` |
| Products | `/products`, `/products/{id}`, `/products/search`, `/products/category/{categoryId}` |
| Cart | `/carts`, `/carts/items`, `/carts/total` |
| Orders | `/orders`, `/orders/my`, `/orders/{orderId}`, `/orders/user/{userId}` |

**Access Control:** Registration, login, product retrieval, and category retrieval are public. Protected endpoints require authentication, while administrative operations require the ADMIN role.

## Getting Started

### Prerequisites

- Java 17 or later
- MySQL
- Maven

### 1. Clone the repository

```bash
git clone https://github.com/timsinaravi/E-Commerce.git
cd E-Commerce
```

### 2. Create a database

```sql
CREATE DATABASE db_name;
```

### 3. Configure database connection

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.datasource.username=db_username
spring.datasource.password=db_password
```

### 4. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

Or use the Maven Wrapper:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`.

## Authentication

1. Register an account using `POST /api/auth/register`.
2. Log in using `POST /api/auth/login`.
3. Copy the JWT returned in the login response.
4. Include the token in the `Authorization` header when accessing protected endpoints:

```http
Authorization: Bearer YOUR_JWT_TOKEN
```
