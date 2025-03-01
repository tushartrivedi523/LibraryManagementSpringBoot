# Library Management System

## Overview

The **Library Management System** is a Spring Boot application that allows users to manage books and authors efficiently. It supports CRUD operations, custom queries, and relationships between books and authors.

## Features

- 📚 Create, Read, Update, and Delete Books
- 👨‍💼 Create, Read, Update, and Delete Authors
- 🔍 Find books by title
- 📆 Find books published after a certain date
- ✍️ Find authors by name
- 📖 Retrieve all books by a specific author

## Technologies Used

- **Java 17+**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **Hibernate** (JPA for ORM)
- **H2 Database / MySQL**
- **Lombok** (for reducing boilerplate code)
- **JUnit & Mockito** (for testing)

## Project Structure

```
LibraryManagementSystem/
│-- src/main/java/com/example/library/
│   │-- controllers/      # REST API Controllers
│   │-- entities/         # JPA Entities (Book, Author)
│   │-- repositories/     # Spring Data JPA Repositories
│   │-- services/        # Business Logic Layer
│   └-- LibraryManagementSystemApplication.java  # Main entry point
│
│-- src/test/java/com/example/library/   # Unit Tests
│-- pom.xml         # Maven dependencies
│-- application.properties  # Database configuration
```

## API Endpoints

### Book APIs

| Method | Endpoint                   | Description                         |
| ------ | -------------------------- | ----------------------------------- |
| POST   | `/books`                   | Create a new book                   |
| GET    | `/books`                   | Retrieve all books                  |
| GET    | `/books/{id}`              | Retrieve a book by ID               |
| PUT    | `/books/{id}`              | Update book details                 |
| DELETE | `/books/{id}`              | Delete a book                       |
| GET    | `/books/title/{title}`     | Find books by title                 |
| GET    | `/books/after/{date}`      | Find books published after a date   |
| GET    | `/books/author/{authorId}` | Find all books by a specific author |

### Author APIs

| Method | Endpoint        | Description              |
| ------ | --------------- | ------------------------ |
| POST   | `/authors`      | Create a new author      |
| GET    | `/authors`      | Retrieve all authors     |
| GET    | `/authors/{id}` | Retrieve an author by ID |
| PUT    | `/authors/{id}` | Update author details    |
| DELETE | `/authors/{id}` | Delete an author         |

## Database Schema

### Book Entity

```java
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate publishedDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
```

### Author Entity

```java
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
```

## Running the Project

### Prerequisites

- Java 17+
- Maven
- H2/MySQL Database

### Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/LibraryManagementSystem.git
   cd LibraryManagementSystem
   ```
2. Update `application.properties` for database configuration.
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access APIs via **Postman** or **cURL**.

## Running Tests

```bash
mvn test
```

## Contributing

Feel free to submit a pull request if you have improvements or bug fixes.

## License

This project is licensed under the MIT License.

