# ShopAxis

ShopAxis (Amazon clone) is a full-stack e-commerce application built with React (frontend) and Spring Boot microservices (backend). It features a responsive UI, RESTful APIs, JWT authentication, and CRUD operations with MongoDB/MySQL.

## Project Overview

This project implements a microservices architecture with the following services:
- **Product Service**: Manages product data and inventory
- **Cart Service**: Handles shopping cart functionality
- **User Service**: Manages user authentication and profiles
- **Navbar Service**: Handles navigation components
- **UI**: React-based frontend components

## Recent Improvements

The codebase has been significantly enhanced with the following improvements:

### 1. Service Configuration Management
- Replaced hardcoded URLs with service discovery using Spring Cloud
- Added Eureka client configuration for microservices communication
- Externalized service URLs in application.properties for fallback configuration
- Created consistent naming conventions across services

```java
// Before
@FeignClient(name="product-service", url="http://localhost:8080/api/products/v1")
public interface ProductServiceFeignClient {
    @GetMapping("/{id}")
    List<Products> getProductById(@PathVariable String id);
}

// After
@FeignClient(name="product-service")
public interface ProductServiceFeignClient {
    @GetMapping("/api/products/v1/{id}")
    Optional<Products> getProductById(@PathVariable Long id);
}
```

### 2. Error Handling
- Implemented a GlobalExceptionHandler to handle exceptions consistently
- Created custom exception classes (ResourceNotFoundException)
- Added proper error responses with informative HTTP status codes and messages
- Integrated logging for better debugging and monitoring

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        // Error handling logic
    }
    // Additional exception handlers
}
```

### 3. Code Organization
- Cleaned up unnecessary commented code
- Added comprehensive Javadoc to all methods and classes
- Restructured services following separation of concerns principles
- Improved naming conventions for better code readability

### 4. API Design
- Standardized API response formats with proper HTTP status codes
- Updated method signatures to be more intuitive (e.g., returning Optional<Products> instead of List)
- Improved parameter handling and validation
- Enhanced error responses with detailed information

### 5. OpenAPI Documentation
- Added OpenAPI/Swagger documentation with a custom configuration
- Documented all API endpoints with descriptions, parameters, and responses
- Made the API easier to understand and test through the Swagger UI
- Added example response schemas

```java
@Operation(summary = "Add a product to the cart", description = "Adds a product to the cart by its ID")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Product added to cart", 
                content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = CartItemDTO.class)) }),
    @ApiResponse(responseCode = "404", description = "Product not found"),
    // Other responses
})
```

### 6. Cross-Origin Configuration
- Implemented a central CORS configuration with externalized properties
- Standardized the approach to CORS across all services
- Enhanced security through proper CORS configuration
- Added flexibility with environment-specific settings

```java
@Configuration
public class CorsConfig {
    @Value("${cors.allowed-origins:http://localhost:3000}")
    private String allowedOrigins;
    // Other properties
    
    @Bean
    public CorsFilter corsFilter() {
        // CORS configuration logic
    }
}
```

### 7. Data Transfer Objects
- Created DTOs for clean separation between API and domain models
- Added mapping layer to convert between entities and DTOs
- Improved data encapsulation and API responses
- Enhanced API flexibility and versioning capability

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {
    private List<CartItemDTO> items;
    private int itemCount;
    private long totalPrice;
    // Pagination fields
}
```

### 8. Pagination Support
- Added pagination for list operations to improve performance
- Implemented sorting and filtering capabilities
- Enhanced response DTOs with pagination metadata
- Improved front-end user experience with paginated data

```java
@GetMapping("/getcart")
public ResponseEntity<CartResponseDTO> getCartItems(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    // Pagination implementation
}
```

### 9. Unit Testing
- Added comprehensive unit tests with JUnit 5 and Mockito
- Implemented test cases for various scenarios including happy path and edge cases
- Improved code reliability with proper test coverage
- Added tests for exception handling

```java
@Test
void addToCart_ProductNotFound_ThrowsResourceNotFoundException() {
    // Arrange
    String productId = "1";
    when(productServiceFeignClient.getProductById(1L)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(ResourceNotFoundException.class, () -> cartService.addToCart(productId));
    verify(productServiceFeignClient, times(1)).getProductById(1L);
    verify(cartProducts, never()).save(any(Products.class));
}
```

## Getting Started

### Prerequisites
- Java 21
- Maven
- MySQL
- Node.js and npm

### Backend Setup
1. Clone the repository
```
git clone https://github.com/yourusername/ShopAxis.com.git
```

2. Set up MySQL database
```
mysql -u root -p
CREATE DATABASE productservice;
CREATE DATABASE cartservice;
CREATE DATABASE userservice;
```

3. Configure application.properties for each service
4. Run each microservice
```
cd productservice/ProductService
mvn spring-boot:run

cd ../cartservice/com.cartservice
mvn spring-boot:run

cd ../userservice/UserService
mvn spring-boot:run
```

### Frontend Setup
1. Navigate to the UI directory
```
cd UI/ecomm
```

2. Install dependencies
```
npm install
```

3. Start the React application
```
npm start
```

## API Documentation
Once the services are running, you can access the Swagger UI at:
- Cart Service: http://localhost:8082/swagger-ui.html
- Product Service: http://localhost:8080/swagger-ui.html
- User Service: http://localhost:8081/swagger-ui.html

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the Apache License 2.0 - see the LICENSE file for details.
