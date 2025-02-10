# Module 1 - Coding Standards

**AndhikaNayakaAryaWibowo (2306174135)**

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

