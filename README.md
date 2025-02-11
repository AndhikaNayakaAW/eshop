# Module 1 - Coding Standards

**Andhika Nayaka Arya Wibowo (2306174135)**

---

## Reflection 1

Having implemented the EShop application using Spring Boot, I have had the opportunity to apply both clean code principles and secure coding practices learned in Module 1. Here are some key reflections:

- **Clean Code**:  
  The codebase is organized into clearly defined layers (**Controller, Service, Repository**), with meaningful names that describe each component's purpose. This improves **readability and maintainability**. I ensured that methods remain **small and focused**, adhering to the **Single Responsibility Principle (SRP)** to separate concerns effectively.

- **Code Optimization**:  
  I reduced **code duplication** by reusing logic in **service and repository** methods. This helped maintain a cleaner and more efficient codebase. Additionally, I applied proper **naming conventions** to make the code self-explanatory, ensuring variables like `productID` clearly indicate their purpose.

- **Secure Coding**:  
  The use of **UUID for product identification** and cautious handling of **potential null values** are examples of secure coding practices in this project. Additionally, I implemented **confirmation prompts** for delete actions to prevent accidental deletions. However, I recognize that **input validation** (e.g., ensuring that the product quantity is a positive integer) can be further improved to prevent potential issues.

- **Opportunities for Improvement**:
  - **Input Validation**: Enhance validation on form inputs by using annotations like `@NotNull` and `@Size` to ensure data integrity and prevent malicious entries.
  - **Error Handling**: Implement more robust error handling, such as using **exception handlers** to provide clearer feedback when operations fail.
  - **Security Enhancements**: As the application evolves, integrating **authorization checks** to restrict access to edit and delete actions, as well as improving session security, will be important for protecting sensitive data.

---

## Reflection 2

After implementing unit tests in this project, I have gained a deeper understanding of their importance in software development. Below are my key takeaways:

- **Importance of Unit Testing**:  
  Writing unit tests is essential for validating the correctness of individual components. I realized that the number of unit tests should depend on the **complexity of the function being tested**. Achieving **100% code coverage does not necessarily mean the code is free of bugs**, as different edge cases and possible input scenarios still need to be manually reviewed.

- **Code Redundancy in Functional Tests**:  
  While writing functional tests, I observed potential **code redundancy** in test setup. For example, if I were to create another functional test suite to verify the number of items in the product list, I might end up **duplicating setup code** from `CreateProductFunctionalTest.java`. While this approach might work, it would reduce code maintainability and cleanliness.

- **Refactoring Test Setup**:  
  A common issue I identified is the **repetition of instance variables** like `serverPort`, `testBaseUrl`, and `baseUrl`, which exist in multiple test files. Any modifications to these values would require **updating multiple files**, increasing maintenance effort. To address this, I plan to introduce a **new utility class** that centralizes common test setup logic. This class would handle:
  - **Browser setup**
  - **Server port initialization**
  - **URL management**

  By implementing **inheritance in functional test classes**, I can significantly **reduce redundancy and improve maintainability**.
