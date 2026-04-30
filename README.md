# 🎤 AI Voice Assistant (Java)

AI-powered Java desktop voice assistant with speech-to-text, OpenAI integration, and chat-style UI.

![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Status](https://img.shields.io/badge/Status-Working-success)
![License](https://img.shields.io/badge/License-MIT-green)

---

## 📸 Demo

![AI Voice Assistant Screenshot](docs/screenshot.png)

---

## 🚀 Features

- 🎤 Voice recording from microphone
- 🧠 Speech-to-text processing
- 🤖 AI-generated responses using OpenAI API
- 💬 Chat-style response display
- ⚡ Lightweight Java desktop application
- 🌐 HTTP communication with external APIs
- ✅ Unit testing for service-layer logic using JUnit and Mockito
---
## 🧪 Testing

This project includes unit tests for the service layer using JUnit and Mockito.

### Tested Features

- Validated successful AI response handling in the service layer
- Mocked the OpenAI client to avoid real API calls during testing
- Verified exception handling when the OpenAI service fails
- Tested service-layer interaction with the chat repository

### Testing Tools

- JUnit 5
- Mockito
- Spring Boot Starter Test

### Run Tests

```bash
mvn test
```

---
## 🧱 Architecture

```text
User Voice
   ↓
Audio Recording (Java)
   ↓
Speech → Text
   ↓
AI Request (OpenAI API)
   ↓
AI Response
   ↓
Display in Chat Window

AI-Assistant-Java-Prototype
│
├── src/main/java
│   ├── ai
│   ├── audio
│   ├── com.aiassistant
│   │   ├── service
│   │   ├── ChatWindow.java
│   │   ├── MainApplication.java
│   │   ├── OpenAIClient.java
│   │   └── WhisperClient.java
│   ├── speech
│   └── ui
│
├── docs
│   └── screenshot.png
│
├── README.md
├── LICENSE
└── pom.xml