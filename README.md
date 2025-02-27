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

# Module 2 - CI/CD & DevOps

**Andhika Nayaka Arya Wibowo (2306174135)**

---
## Reflection 1

During the exercise, I resolved several code quality issues to improve the overall CI/CD workflow. One of the key issues was a duplicate code segment, which I fixed by refactoring the repeated logic into a reusable function to enhance maintainability and reduce redundancy. Additionally, I encountered a permission error in the Dockerfile and ci.yml, which I resolved by adding chmod +x gradlew before executing the build command. This ensured that the Gradle wrapper had the necessary execution permissions. To further improve code quality, I integrated SonarCloud into the project, allowing early detection of potential issues and maintaining a high-quality codebase.

---

## Reflection 2

My current CI/CD implementation aligns with Continuous Integration (CI) principles by automatically executing test suites and analyzing code quality on every push or pull request. It also incorporates Continuous Deployment (CD) by automatically deploying the latest changes to Koyeb whenever updates are made to the main branch. This automation minimizes manual intervention, reduces errors, and ensures a fast, reliable, and scalable deployment process. By implementing both CI and CD, the workflow maintains code consistency, enhances software reliability, and accelerates delivery cycles.

# Module 3 - OO Principles & Software Maintainability

**Andhika Nayaka Arya Wibowo (2306174135)**

---

## Reflection

### 1) Explain what principles you apply to your project!

In this project, I applied several SOLID principles:
- **Single Responsibility Principle (SRP):**  
  Each class is responsible for a single aspect of functionality. For example, `ProductController` handles product-related operations while `CarController` manages car-related features.

- **Open-Closed Principle (OCP):**  
  The design is open for extension but closed for modification. By relying on interfaces (e.g., for service and repository layers), new behaviors can be added (such as a new repository implementation) without changing the existing code.

- **Liskov Substitution Principle (LSP):**  
  Subclasses or alternative implementations (like a logging repository extending an in-memory repository) can replace their base class without breaking the system’s behavior.

- **Interface Segregation Principle (ISP):**  
  Large interfaces are split into smaller ones (for example, using nested interfaces in `CarService` for query and command operations) so that clients only depend on the methods they need.

- **Dependency Inversion Principle (DIP):**  
  High-level modules (like controllers) depend on abstractions (interfaces) rather than concrete classes. Constructor injection is used to ensure that controllers and services are loosely coupled to their implementations.

### 2) Explain the advantages of applying SOLID principles to your project with examples.

- **Maintainability:**  
  By adhering to SRP, each class remains focused and easier to understand. For instance, modifications in product-related logic do not affect car-related functionality.

- **Extensibility:**  
  With OCP, new features (such as additional repository implementations) can be added without modifying existing code. This minimizes the risk of introducing bugs during enhancements.

- **Robustness:**  
  LSP ensures that any new subclass (like a logging-enhanced repository) can replace its parent without altering the program’s correctness, simplifying testing and maintenance.

- **Clarity:**  
  Using ISP, interfaces remain clean and focused, so clients are not burdened with unnecessary methods. This prevents unintended side effects and makes the codebase easier to navigate.

- **Flexibility:**  
  DIP reduces tight coupling between components. For example, the controllers depend on the abstract `ProductService` instead of a concrete implementation, making it simple to swap out components for alternative implementations without affecting the overall system.

### 3) Explain the disadvantages of not applying SOLID principles to your project with examples.

- **Increased Complexity:**  
  Without SRP, a single class might handle multiple responsibilities, leading to large, hard-to-maintain classes where changes in one area inadvertently affect others.

- **Reduced Extensibility:**  
  Not following OCP means that adding new features might require modifying existing, stable code—this increases the risk of regressions.

- **Fragility:**  
  A violation of LSP can result in subclasses that behave unexpectedly when used in place of their parent, potentially breaking the application’s overall behavior.

- **Bloated Interfaces:**  
  Without ISP, clients might be forced to depend on methods they do not need, which can lead to confusion and unnecessary coupling.

- **Tight Coupling:**  
  Failing to follow DIP makes high-level modules depend directly on low-level modules. This tight coupling makes it difficult to change or replace lower-level components without affecting the entire system.
