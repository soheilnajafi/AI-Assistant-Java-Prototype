# 🚀 AI Assistant Backend (Java Spring Boot)

A Java Spring Boot backend application that integrates with OpenAI API to provide real-time AI chat responses.

The system supports conversation-based context, pagination for chat history, and interactive API testing using Swagger.

---

## 🧠 Features

- 🤖 Real-time AI chat responses (OpenAI integration)
- 🔗 REST API (`/api/assistant/chat`)
- 🧱 Layered architecture (Controller → Service → Repository)
- 📘 Swagger UI for API testing
- 💾 MySQL database with JPA/Hibernate
- 📄 Pagination support for chat history
- 🔄 Conversation-based context handling
- 🛡️ Global exception handling

---

## 🏗️ Architecture

Backend (Spring Boot REST API)  
↓  
Controller Layer  
↓  
Service Layer  
↓  
Repository Layer (JPA)  
↓  
Database (MySQL)  
↓  
OpenAI API

---

## 📡 API Endpoints

### POST `/api/assistant/chat`

#### Request
```json
{
  "conversationId": 1,
  "message": "Hello AI"
}
