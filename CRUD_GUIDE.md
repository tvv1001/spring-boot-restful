# Spring Boot CRUD Application with Database

## Overview
This project implements a complete CRUD (Create, Read, Update, Delete) application using Spring Boot with JPA and H2 in-memory database.

## Project Structure

### New Files Created:

1. **Model Layer** - `src/main/java/com/example/demo/model/Product.java`
   - JPA Entity representing a Product
   - Fields: id, name, price, description, quantity

2. **Repository Layer** - `src/main/java/com/example/demo/repository/ProductRepository.java`
   - Extends JpaRepository for database operations
   - Custom query methods for searching

3. **Service Layer** - `src/main/java/com/example/demo/service/ProductService.java`
   - Business logic for all CRUD operations
   - Methods for creating, reading, updating, and deleting products

4. **Controller Layer** - `src/main/java/com/example/demo/controller/ProductController.java`
   - REST endpoints for all CRUD operations
   - Proper HTTP status codes and error handling

### Modified Files:

1. **pom.xml** - Added dependencies:
   - `spring-boot-starter-data-jpa` for database operations
   - `h2` database driver

2. **application.properties** - Added database configuration:
   - H2 in-memory database setup
   - JPA/Hibernate configuration

## API Endpoints

### CREATE (POST)
```
POST /api/products
Content-Type: application/json

{
  "name": "Laptop",
  "price": 999.99,
  "description": "High-performance laptop",
  "quantity": 5
}
```

### READ (GET)
```
GET /api/products                    # Get all products
GET /api/products/{id}               # Get product by ID
GET /api/products/search/name?name=Laptop  # Search by name
GET /api/products/search/price?maxPrice=500  # Get products under price
GET /api/products/search/quantity?minQuantity=10  # Get products with quantity
```

### UPDATE (PUT/PATCH)
```
PUT /api/products/{id}
PATCH /api/products/{id}
Content-Type: application/json

{
  "name": "Updated Laptop",
  "price": 1099.99,
  "quantity": 3
}
```

### DELETE
```
DELETE /api/products/{id}            # Delete specific product
DELETE /api/products                 # Delete all products
```

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build
```bash
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Database Access

### H2 Console (Optional)
Access the H2 database console at: `http://localhost:8080/h2-console`

Connection Details:
- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave blank)

## Testing with cURL

### Create a Product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Mouse",
    "price": 29.99,
    "description": "Wireless mouse",
    "quantity": 50
  }'
```

### Get All Products
```bash
curl http://localhost:8080/api/products
```

### Get Product by ID
```bash
curl http://localhost:8080/api/products/1
```

### Search by Name
```bash
curl "http://localhost:8080/api/products/search/name?name=Mouse"
```

### Update Product
```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Mouse",
    "price": 39.99,
    "quantity": 45
  }'
```

### Delete Product
```bash
curl -X DELETE http://localhost:8080/api/products/1
```

## Features

✅ Full CRUD operations
✅ JPA/Hibernate ORM
✅ H2 in-memory database
✅ Search functionality (by name, price, quantity)
✅ Proper HTTP status codes
✅ RESTful API design
✅ Error handling

## Application Flow

1. **Request** → Controller Layer
2. **Controller** → Service Layer (business logic)
3. **Service** → Repository Layer (database operations)
4. **Repository** → H2 Database
5. **Response** → JSON

## Layers Explanation

- **Controller**: Handles HTTP requests/responses
- **Service**: Contains business logic and validation
- **Repository**: Handles database operations via JPA
- **Model/Entity**: Represents database table structure
