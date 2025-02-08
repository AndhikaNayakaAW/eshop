# Module 1 - Coding Standards

**AndhikaNayakaAryaWibowo (2306174135)**

---

## Project Overview

This repository contains the **EShop Spring Boot Application** developed as part of Module 1 – Coding Standards for Advanced Programming. The application demonstrates basic CRUD (Create, Read, Update, Delete) operations for managing products in a simple e-shop system. The project follows the MVC pattern and separates the application into the following layers:
- **Controller**: Manages HTTP requests and responses.
- **Service**: Contains business logic.
- **Repository**: Handles data management (in this case, an in-memory list).

---

## Coding Standards & Clean Code Principles

In developing this application, several clean code principles have been applied:

- **Meaningful Naming**:
    - Classes, methods, and variables use descriptive names (e.g., `ProductController`, `ProductService`, `createProduct()`) to clearly indicate their roles and responsibilities.

- **Separation of Concerns**:
    - The application is divided into distinct layers (controller, service, repository) that isolate business logic from presentation logic, making the code easier to maintain and extend.

- **Small, Focused Functions**:
    - Functions are kept short and perform only one task. For instance, each controller method handles a specific HTTP endpoint.

- **Clean Formatting & Layout**:
    - The code uses consistent indentation and spacing, which helps improve readability.

- **Minimal & Meaningful Comments**:
    - The code is self-explanatory with descriptive names. Comments are used sparingly for legal purposes and to explain non-obvious decisions rather than redundant explanations.

- **Error Handling**:
    - Basic error handling is implemented (e.g., null-safe comparisons in the controller). In a production system, this could be enhanced with more robust exception management.

---

## Secure Coding Practices

The application also follows several secure coding guidelines:

- **Input Data Handling**:
    - Although the current implementation assumes correct input, the code structure encourages the addition of further input validation (e.g., ensuring that product quantities are valid numbers).

- **Safe Data Management**:
    - Product IDs are generated using `UUID`, ensuring that each product has a unique identifier.

- **Encapsulation & Data Hiding**:
    - The repository layer manages data internally without exposing its underlying structure directly to the client.

- **Future Enhancements**:
    - While the basics are in place, additional measures such as more detailed input sanitization, secure session management, and comprehensive error handling could be implemented in subsequent iterations.

---

## Reflection 1

Having implemented the EShop application using Spring Boot, I have had the opportunity to apply both clean code principles and secure coding practices learned in Module 1. Here are some key reflections:

- **Clean Code**:  
  The codebase is organized into clearly defined layers (Controller, Service, Repository), with meaningful names that describe each component's purpose. This improves readability and maintainability. The methods are small and focused, adhering to the single responsibility principle.

- **Secure Coding**:  
  The use of UUID for product identification and cautious handling of potential null values are examples of secure coding practices in this project. However, I recognize that input validation (e.g., ensuring that the product quantity is a positive integer) can be further improved to prevent potential issues.

- **Opportunities for Improvement**:
    - **Input Validation**: Enhance validation on form inputs to ensure data integrity and prevent malicious entries.
    - **Error Handling**: Implement more robust error handling, such as using exception handlers to provide clearer feedback when operations fail.
    - **Security Enhancements**: As the application evolves, integrating secure session management and further sanitization of user inputs will be important steps toward a more secure application.

Overall, this project serves as a practical exercise in maintaining clean and secure code. Continuous evaluation and refactoring will help ensure that the code remains robust, readable, and secure as new features are added.

---

## How to Run the Application

1. **Prerequisites**:
    - Java 21
    - Git
    - IntelliJ Ultimate (or another preferred IDE)

2. **Setup**:
    - Clone this repository.
    - Open the project in your IDE.
    - Ensure that Gradle downloads all required dependencies automatically.

3. **Running the Application**:
    - Use the **Run** button (triangle icon) in IntelliJ or execute `./gradlew bootRun` from the terminal.
    - Open your web browser and navigate to `http://localhost:8080/product/list` to view the product list.

4. **Testing the Features**:
    - Use the **Create Product** button to add new products.
    - Edit or delete products using the corresponding options in the product list.
    - Changes are reflected immediately on the web interface.

---

## Project Structure

- **`/src/main/java/id/ac/ui/cs/advprog/eshop/controller`**: Contains `ProductController.java`, which handles HTTP requests.
- **`/src/main/java/id/ac/ui/cs/advprog/eshop/model`**: Contains `Product.java`, the model for a product.
- **`/src/main/java/id/ac/ui/cs/advprog/eshop/repository`**: Contains `ProductRepository.java`, which manages product data.
- **`/src/main/java/id/ac/ui/cs/advprog/eshop/service`**: Contains the service interface (`ProductService.java`) and its implementation (`ProductServiceImpl.java`).
- **`/src/main/resources/templates`**: Contains Thymeleaf HTML templates for creating, listing, and editing products.

---

## Conclusion

This module has provided a strong foundation in applying clean code and secure coding principles. The EShop application reflects an ongoing commitment to writing maintainable, readable, and secure code. Continuous improvement—especially in areas such as input validation and error handling—remains a priority as the project evolves.

