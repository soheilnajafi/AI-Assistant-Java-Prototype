# 🚀 AI Assistant Backend (Java Spring Boot)

A Java Spring Boot backend application that integrates with the OpenAI API to provide AI chat responses through REST APIs.

The system supports chat requests, OpenAI API integration, MySQL database persistence, Swagger/OpenAPI testing, environment-based configuration, and Docker Compose setup for running the Spring Boot application with a MySQL container.

---

## Features

- Real-time AI chat responses using OpenAI API integration
- REST API endpoint: `/api/assistant/chat`
- Health check endpoint: `/api/assistant/health`
- Chat history endpoint: `/api/assistant/history`
- Layered backend structure using Controller, Client, Repository, DTO, Entity, and Exception packages
- MySQL database integration with Spring Data JPA / Hibernate
- Persistent storage for user messages and AI responses
- Swagger UI for interactive API testing
- Global exception handling
- Environment variable configuration for database password and OpenAI API key
- Dockerized backend application using Dockerfile
- Docker Compose setup for Spring Boot + MySQL containers
- Verified end-to-end flow: Swagger → Spring Boot → OpenAI API → MySQL

---

## 🏗️ Architecture

```text
User Request
↓
Spring Boot REST Controller
↓
OpenAI Client / Backend Logic
↓
Repository Layer (Spring Data JPA)
↓
MySQL Database
↓
API Response
```

---

## 📡 API Endpoints

### POST `/api/assistant/chat`

Sends a user message to the backend, receives an AI-generated response from OpenAI, and stores the conversation data in MySQL.

#### Request

```json
{
  "conversationId": 1,
  "message": "Hello AI"
}
```

#### Response

```json
{
  "success": true,
  "reply": "Hello! How can I assist you today?",
  "errors": null
}
```

---

### GET `/api/assistant/history?page=0&size=5`

Returns paginated chat history from the database.

---

### GET `/api/assistant/health`

Checks whether the API is running.

Example response:

```text
Assistant API is running
```

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- OpenAI API
- Swagger / OpenAPI
- Docker
- Docker Compose
- Environment Variables
- Git / GitHub

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/soheilnajafi/AI-Assistant-Java-Prototype.git
cd AI-Assistant-Java-Prototype
```

---

### 2. Configure environment variables

This project uses environment variables for sensitive configuration values. Do not commit real passwords or API keys to GitHub.

For local IntelliJ run configuration, set:

```text
DB_PASSWORD=your_mysql_password;OPENAI_API_KEY=your_openai_api_key
```

Example `.env` file for Docker Compose:

```env
MYSQL_ROOT_PASSWORD=your_mysql_password
OPENAI_API_KEY=your_openai_api_key
```

The application reads these values through `application.properties`:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/ai_assistant_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
openai.api.key=${OPENAI_API_KEY}
```

---

### 3. Run the application locally

```bash
mvn clean install
mvn spring-boot:run
```

Runs on:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

Alternative Swagger URL:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🐳 Docker Support

This project includes Docker support for running the Spring Boot backend and MySQL database in containers.

### 1. Build the application JAR

```bash
mvn clean package
```

If Maven is not available from the terminal, use the Maven tool window in IntelliJ:

```text
Maven → Lifecycle → package
```

---

### 2. Build the Docker image

```bash
docker build -t ai-assistant:latest .
```

---

### 3. Run with Docker Compose

```bash
docker compose up
```

Docker Compose starts:

```text
ai-assistant-app
ai-assistant-mysql
```

---

### 4. Access the application

Application URL:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

Alternative Swagger URL:

```text
http://localhost:8080/swagger-ui/index.html
```

---

### 5. MySQL container port mapping

```text
localhost:3307 → mysql:3306
```

---

### 6. Verified Docker flow

```text
Swagger → Spring Boot Docker Container → OpenAI API → MySQL Docker Container
```

---

## 📂 Project Structure

```text
src/main/java/com/aiassistant
│
├── AssistantApiApplication.java     # Main Spring Boot application
├── client                           # OpenAI integration logic
├── controller                       # REST API layer
├── dto                              # Request / response models
├── entity                           # Database entities
├── exception                        # Global exception handling
└── repository                       # Database access layer
```

---

## 🧪 Testing and Validation

The application was tested using Swagger UI and Docker Compose.

### Verified Features

- Verified `/api/assistant/health` endpoint returns a successful response
- Verified `/api/assistant/chat` endpoint returns an AI-generated response
- Verified OpenAI API integration through environment variables
- Verified MySQL persistence for user messages and AI responses
- Verified Docker Compose startup for Spring Boot and MySQL containers
- Verified stored chat records inside the Docker MySQL container

### Testing Tools

- Swagger UI
- MySQL Workbench
- Docker CLI
- Docker Compose
- Spring Boot Starter Test

---

## 📌 Future Improvements

- GitHub Actions CI/CD pipeline
- AWS deployment using EC2/RDS
- Authentication and authorization
- Unit and integration test coverage
- Memory optimization for long conversations
- Logging and monitoring improvements

---

## 👨‍💻 Author

Suhill Najafi  
Java Backend Developer | AI Integration | Spring Boot

---

## 📄 License

MIT License
