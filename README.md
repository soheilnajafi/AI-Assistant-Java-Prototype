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