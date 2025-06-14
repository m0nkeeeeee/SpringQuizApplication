```markdown
# 🧠 QuizMaster — Spring Boot REST API for Quiz Management

QuizMaster is a Java-based RESTful backend API for managing quizzes and quiz questions. Built with **Spring Boot**, **PostgreSQL**, and **Spring Data JPA**, it supports full CRUD operations, dynamic quiz creation, result calculation, and bulk database setup via an SQL file.

---

## 📚 Table of Contents

- [🚀 Overview](#-overview)
- [🧰 Tech Stack](#-tech-stack)
- [📋 Prerequisites](#-prerequisites)
- [📁 Project Structure](#-project-structure)
- [📦 Getting Started](#-getting-started)
- [🗄️ Importing Sample Data](#️-importing-sample-data)
- [🔗 API Endpoints](#-api-endpoints)
- [🧪 Testing with Postman](#-testing-with-postman)
- [📤 Packaging & Running JAR](#-packaging--running-jar)
- [🤝 Contributing](#-contributing)
- [📄 License](#-license)

---

## 🚀 Overview

QuizMaster allows users to:

- ✏️ Add, update, or delete multiple-choice questions
- 🎯 Filter questions by category or difficulty
- 🧠 Generate quizzes with random questions from a category
- 📩 Submit answers and receive a calculated score
- ⚡ Use a bundled SQL script to populate test questions

---

## 🧰 Tech Stack

| Layer       | Technology            |
|-------------|------------------------|
| ⚙️ Backend   | Spring Boot            |
| 🗃️ Database | PostgreSQL             |
| 🔄 ORM      | Spring Data JPA (Hibernate) |
| 🔧 Build    | Maven                  |
| 📦 Utility  | Lombok                 |
| 🧪 Testing  | Postman                |

---

## 📋 Prerequisites

- ✅ Java 17+
- ✅ Maven
- ✅ PostgreSQL
- ✅ Optional: IntelliJ IDEA / VS Code for development

---

## 📁 Project Structure

```

quizapp/
├── src/
├── questions.sql ✅
├── pom.xml
├── README.md

````

---

## 📦 Getting Started

### 📥 Clone the Repository

```bash
git clone https://github.com/your-username/quizapp.git
cd quizapp
````

---

### 🗄️ Set Up PostgreSQL Database

1. Create a new database:

```sql
CREATE DATABASE quiz;
```

2. Update `application.properties` in `src/main/resources`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/quiz
spring.datasource.username=postgres
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

## 🗄️ Importing Sample Data

To auto-create the `question` table and insert default questions:

1. Ensure `quiz` database exists
2. Import the [`questions.sql`](./questions.sql) file:

```bash
psql -U postgres -d quiz -f questions.sql
```

> 🔁 Replace `postgres` if your DB username is different.

---

## 🔗 API Endpoints

### 📝 Question APIs

| Method | Endpoint                        | Description               |
| ------ | ------------------------------- | ------------------------- |
| GET    | `/Question/allQuestions`        | Get all questions         |
| GET    | `/Question/category/{category}` | Get questions by category |
| POST   | `/Question/add`                 | Add a new question        |
| PUT    | `/Question/update/{id}`         | Update a question by ID   |
| DELETE | `/Question/delete/{id}`         | Delete a question by ID   |

---

### 🧠 Quiz APIs

| Method | Endpoint                                                    | Description                                 |
| ------ | ----------------------------------------------------------- | ------------------------------------------- |
| POST   | `/quiz/create?category={category}&numQ={num}&title={title}` | Create quiz with random questions           |
| GET    | `/quiz/get/{id}`                                            | Get quiz questions by quiz ID               |
| POST   | `/quiz/submit/{id}`                                         | Submit quiz answers and calculate the score |

---

## 🧪 Testing with Postman

### ➕ Add a Question

```http
POST /Question/add
```

**Body:**

```json
{
  "questionTitle": "What beverage is Java not related to?",
  "option1": "coffee",
  "option2": "tea",
  "option3": "coke",
  "option4": "water",
  "rightAnswer": "coke",
  "difficultyLevel": "Easy",
  "category": "java"
}
```

---

### 🧠 Create a Quiz

```http
POST /quiz/create?category=java&numQ=5&title=JQuiz
```

---

### ✅ Submit Quiz Answers

```http
POST /quiz/submit/1
```

**Body:**

```json
[
  {
    "id": 1,
    "givenAnswer": "coke"
  },
  {
    "id": 2,
    "givenAnswer": "Object-Oriented Programming"
  }
]
```

---

## 📤 Packaging & Running JAR

1. Build project:

```bash
mvn clean package
```

2. Run the JAR:

```bash
java -jar target/quizapp-0.0.1-SNAPSHOT.jar
```

The app will start at:
👉 `http://localhost:8080`

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a Pull Request

---

## 📄 License

This project is licensed under the **MIT License**.
See the [LICENSE](LICENSE) file for details.

---

> 🔧 Maintained with ❤️ by \[your-name]

```
