# E-Commerce Backend

A RESTful E-Commerce backend built with **Java, Spring Boot, Spring Data JPA, MySQL, Jakarta Bean Validation, and Maven**.

The application implements the core backend operations of an e-commerce system, covering **user management, category and product management, cart operations, and order processing**.

## Tech Stack

* **Java**
* **Spring Boot**
* **Spring Data JPA / Hibernate**
* **MySQL**
* **Jakarta Bean Validation**
* **Maven**
* **Lombok**
* **REST APIs**

## Architecture & Project Structure

The application follows a **layered architecture** with clear separation between API handling, business logic, data access, and persistence.

```text
src/main/java/com/ecommerce
│
├── controller      # Exposes REST API endpoints
├── service         # Defines application operations
├── service/impl    # Implements business logic
├── repository      # Handles data access with Spring Data JPA
├── entity          # JPA persistence models
├── dto             # API request and response models
├── mapper          # Converts between entities and DTOs
├── exception       # Custom exceptions and global error handling
└── enums           # Application-specific enumerations
```

DTOs are used at the API boundary to keep request/response models separate from JPA entities.

## Core Features

### User Management

* Create, retrieve, update, and delete users
* Email uniqueness validation
* Request validation
* User role support

### Category Management

* Category CRUD operations
* Unique category names
* Category-product relationship

### Product Management

* Product CRUD operations
* Category association and updates
* Search products by name
* Retrieve products by category
* Price and stock validation

### Cart Management

* Create and retrieve user carts
* Add products to cart
* Update cart item quantities
* Remove cart items
* Clear cart
* Calculate cart total
* Validate product stock when modifying cart quantities

### Order Processing

* Create orders from the user's cart
* Validate cart contents and product stock before placing an order
* Create order items containing the purchased product, quantity, and price
* Calculate the order total
* Update product stock as part of order placement
* Clear the cart after a successful order
* Process order creation using Spring's `@Transactional`

## Validation & Exception Handling

The application uses **Jakarta Bean Validation** for request validation, including:

* Required fields
* Email format
* Password length
* Positive prices and quantities
* Non-negative product stock

Business-specific errors are handled through **custom exceptions** and a centralized `@RestControllerAdvice`.

Handled cases include missing resources, duplicate email or category names, invalid cart operations, empty carts, and insufficient stock.

## API Overview

### Users

```text
POST    /api/users
GET     /api/users
GET     /api/users/{id}
PUT     /api/users/{id}
DELETE  /api/users/{id}
```

### Categories

```text
POST    /api/categories
GET     /api/categories
GET     /api/categories/{id}
PUT     /api/categories/{id}
DELETE  /api/categories/{id}
```

### Products

```text
POST    /api/products
GET     /api/products
GET     /api/products/{id}
GET     /api/products/search?name={name}
GET     /api/products/category/{categoryId}
PUT     /api/products/{id}
DELETE  /api/products/{id}
```

### Cart

```text
POST    /api/carts/{userId}
GET     /api/carts/{userId}
POST    /api/cartitems/{userId}
PUT     /api/users/{userId}/cartitems/{cartItemId}
DELETE  /api/users/{userId}/cartitems/{cartItemId}
DELETE  /api/users/{userId}/cart
GET     /api/carts/{userId}/total
```

### Orders

```text
POST    /api/orders/{userId}
GET     /api/orders
GET     /api/orders/{id}
GET     /api/orders/user/{userId}
```

## How to Run

### Prerequisites

* **Java 17+**
* **Maven**
* **MySQL**

### Setup

1. Clone the repository.

2. Create a MySQL database for the application.

3. Configure the database connection in:

```text
src/main/resources/application.properties
```

4. Set the following properties using your local MySQL configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

5. Start the application:

```bash
mvn spring-boot:run
```

The application will be available at:

```text
http://localhost:8080
```

The REST APIs can be accessed using an API client such as **Postman**.

## Project Scope

The current implementation focuses on the core backend operations of an e-commerce application while maintaining a structured and extensible architecture.

Future development can extend the system with **authentication and authorization, JWT-based security, pagination and sorting, file uploads, email notifications, payment integration, API documentation, automated testing, and Docker support**.
