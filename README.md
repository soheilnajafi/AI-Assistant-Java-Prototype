# AI Voice Assistant (Java)

A lightweight AI-powered voice assistant built with Java.

The application captures audio from the microphone, converts speech to text, sends the request to an AI model, and displays the response in a chat-style interface.

This project demonstrates how to integrate voice input, HTTP APIs, and AI services into a Java desktop application.

---

## 🚀 Features

- 🎤 Voice recording from microphone
- 🧠 AI-generated responses using OpenAI API
- 💬 Chat-style response display
- ⚡ Lightweight Java desktop application
- 🌐 HTTP communication with external APIs

---

## 🧱 Project Architecture

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

---

## 🛠 Tech Stack

- **Java 17**
- **Maven**
- **Java Swing** (UI)
- **OpenAI API**
- **HTTP Client (Java)**

---

## 📂 Project Structure

```
AI-Assistant-Java-Prototype
│
├── src
│   └── main
│       └── java
│           └── assistant
│               ├── MainApp.java
│               ├── AudioRecorder.java
│               ├── SpeechToText.java
│               ├── OpenAIClient.java
│               └── ChatWindow.java
│
├── README.md
├── LICENSE
└── pom.xml
```
